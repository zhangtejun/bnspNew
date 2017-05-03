package com.cn.uuu.controller.nio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NioClient {
	// 通道管理器
	private Selector selector;

	private BufferedReader sin = new BufferedReader(new InputStreamReader(
			System.in));

	/**
	 * 获得一个Socket通道，并对该通道做一些初始化的工作
	 * 
	 * @param ip
	 *            连接的服务器的ip
	 * @param port
	 *            连接的服务器的端口号
	 * @throws IOException
	 */
	public void initClient(String ip, int port) throws IOException {
		// 获得一个Socket通道
		SocketChannel channel = SocketChannel.open();
		// 设置通道为非阻塞
		channel.configureBlocking(false);
		// 获得一个通道管理器
		this.selector = Selector.open();

		// 客户端连接服务器,其实方法执行并没有实现连接，需要在listen（）方法中调
		// 用channel.finishConnect();才能完成连接
		channel.connect(new InetSocketAddress(ip, port));
		// 将通道管理器和该通道绑定，并为该通道注册SelectionKey.OP_CONNECT事件。
		// 与Selector一起使用时，Channel必须处于非阻塞模式下。这意味着不能将FileChannel与Selector一起使用，
		// 因为FileChannel不能切换到非阻塞模式。而套接字通道都可以
		// 注意register()方法的第二个参数。这是一个“interest集合”，可以用“位或”操作符将常量连接起来-->SelectionKey.OP_READ
		// | SelectionKey.OP_WRITE
		channel.register(selector, SelectionKey.OP_CONNECT);

		System.out.println("已经与Server建立连接。。。。");
		System.out.println("\n请输入要发送的信息：");
	}

	/**
	 * 采用轮询的方式监听selector上是否有需要处理的事件，如果有，则进行处理
	 * 
	 * @throws IOException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void listen() throws IOException {
		// 轮询访问selector
		while (true) {
			selector.select();
			// 获得selector中选中的项的迭代器
			Iterator ite = this.selector.selectedKeys().iterator();
			while (ite.hasNext()) {
				SelectionKey key = (SelectionKey) ite.next();
				// 删除已选的key,以防重复处理
				ite.remove();
				if (key.isValid()) {
					// 连接事件发生
					if (key.isConnectable()) {// 连接就绪
						SocketChannel channel = (SocketChannel) key.channel();
						// 如果正在连接，则完成连接
						if (channel.isConnectionPending()) {
							/**
							 * 调用 finishConnect(
							 * )方法来完成连接过程，该方法任何时候都可以安全地进行调用。假如在一 个非阻塞模式的
							 * SocketChannel 对象上调用 finishConnect(
							 * )方法，将可能出现下列情形之一： 1.connect( )方法尚未被调用。那么将产生
							 * NoConnectionPendingException 异常。
							 * 2.连接建立过程正在进行，尚未完成。那么什么都不会发生，finishConnect(
							 * )方法会立即返回false 值。 3.在非阻塞模式下调用 connect(
							 * )方法之后，SocketChannel 又被切换回了阻塞模式。那么如果
							 * 有必要的话，调用线程会阻塞直到连接建立完成，finishConnect( )方法接着就会返回
							 * true值。 4.在初次调用 connect( )或最后一次调用 finishConnect(
							 * )之后，连接建立过程已经完成。那么 SocketChannel
							 * 对象的内部状态将被更新到已连接状态，finishConnect( )方法会返回 true 值，然后
							 * SocketChannel 对象就可以被用来传输数据了。
							 * 5.连接已经建立。那么什么都不会发生，finishConnect( )方法会返回 true 值。
							 * 
							 * 当通道处于中间的连接等待（connection-pending）状态时，您只可以调用
							 * finishConnect( )、 isConnectPending( )或
							 * isConnected( )方法。一旦连接建立过程成功完成，isConnected( )将返回
							 * true 值。
							 */
							channel.finishConnect();

						}
						// 设置成非阻塞
						channel.configureBlocking(false);

						// 在这里可以给服务端发送信息哦
						String input = sin.readLine();
						channel.write(ByteBuffer.wrap(input.getBytes()));
						// 在和服务端连接成功之后，为了可以接收到服务端的信息，需要给通道设置读的权限。
						channel.register(this.selector, SelectionKey.OP_READ);

						// 获得了可读的事件
					} else if (key.isReadable()) {// 读就绪
					// read(key);
						readDataFromSocket(key);
					}
				} else {
					System.out.println("******");
				}

			}

		}
	}

	/**
	 * 处理读取服务端发来的信息 的事件
	 * 
	 * @param key
	 * @throws IOException
	 */
	public void read(SelectionKey key) throws IOException {
		// 得到事件发生的Socket通道
		SocketChannel channel = (SocketChannel) key.channel();
		// 创建读取的缓冲区
		ByteBuffer buffer = ByteBuffer.allocate(100);
		channel.read(buffer);
		byte[] data = buffer.array();
		String msg = new String(data).trim();
		System.out.println("接收来着服务端信息：" + msg);
		System.err.println("channel:" + channel.toString());
		System.out.println("\n请输入要发送的信息：");
		String input = sin.readLine();

		ByteBuffer outBuffer = ByteBuffer.wrap(input.getBytes());
		channel.write(outBuffer);
	}

	// 直接缓冲区无备份数组 -->不能buffer.array()
	private ByteBuffer buffer1 = ByteBuffer.allocateDirect(5);

	protected void readDataFromSocket(SelectionKey key) throws IOException {
		SocketChannel socketChannel = (SocketChannel) key.channel();
		int count;
		buffer1.clear(); // Empty buffer
		// Loop while data is available; channel is nonblocking
		while ((count = socketChannel.read(buffer1)) > 0) {
			buffer1.flip(); // Make buffer readable
			// Send the data; don't assume it goes all at once
			while (buffer1.hasRemaining()) {
				System.err.print(buffer1.get());
			}
			buffer1.clear(); // Empty buffer
		}
		/**
		 * 1、read什么时候返回-1 read返回-1说明客户端的数据发送完毕，并且主动的close
		 * socket。所以在这种场景下，（服务器程序）你需要关闭socketChannel并且取消key，
		 * 最好是退出当前函数。注意，这个时候服务端要是继续使用该socketChannel进行读操作的话
		 * ，就会抛出“远程主机强迫关闭一个现有的连接”的IO异常。 2、read什么时候返回0
		 * 其实read返回0有3种情况，一是某一时刻socketChannel中当前
		 * （注意是当前）没有数据可以读，这时会返回0，其次是bytebuffer的position等于limit了，
		 * 即bytebuffer的remaining等于0
		 * ，这个时候也会返回0，最后一种情况就是客户端的数据发送完毕了（注意看后面的程序里有这样子的代码），
		 * 这个时候客户端想获取服务端的反馈调用了recv函数，若服务端继续read，这个时候就会返回0。
		 */
		if (count <= 0 && (buffer1.position() == 0)) {
			// Close channel on EOF, invalidates the key
			// socketChannel.close();
			System.out.println("\n请输入要发送的信息：");
			String input = sin.readLine();

			ByteBuffer outBuffer = ByteBuffer.wrap(input.getBytes());
			socketChannel.write(outBuffer);
		}
	}

	/**
	 * 启动客户端测试
	 * 
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		NioClient client = new NioClient();
		client.initClient("localhost", 8888);
		client.listen();
	}

}
