package com.cn.uuu.controller.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NioServer {
	// 通道管理器
	private Selector selector;

	/**
	 * 获得一个ServerSocket通道，并对该通道做一些初始化的工作 ServerSocketChannel 是一个基于通道的 socket 监听器
	 * 
	 * @param port
	 *            绑定的端口号
	 * @throws IOException
	 */
	public void initServer(int port) throws IOException {
		// 获得一个ServerSocket通道
		ServerSocketChannel socketChannel = ServerSocketChannel.open();
		// 设置通道为非阻塞。ServerSocketChannel可以设置成非阻塞模式。在非阻塞模式下，accept()
		// 方法会立刻返回，如果还没有新进来的连接,返回的将是null
		socketChannel.configureBlocking(false);
		// 将该通道对应的ServerSocket绑定到port端口
		socketChannel.bind(new InetSocketAddress(port));
		// 获得一个通道管理器
		this.selector = Selector.open();
		// 将通道管理器和该通道绑定，并为该通道注册SelectionKey.OP_ACCEPT事件,注册该事件后，
		// 当该事件到达时，selector.select()会返回，如果该事件没到达selector.select()会一直阻塞。
		socketChannel.register(selector, SelectionKey.OP_ACCEPT);

		System.out.println("Server已经启动，监听端口: " + port + "， 等待客户端注册。。。");

	}

	/**
	 * 采用轮询的方式监听selector上是否有需要处理的事件，如果有，则进行处理
	 * 
	 * @throws IOException
	 */
	@SuppressWarnings("rawtypes")
	public void listen() throws IOException {
		// 轮询访问selector
		while (true) {
			System.err.println("**");
			// 当注册的事件到达时，方法返回；否则,该方法会一直阻塞
			selector.select();
			// 获得selector中选中的项的迭代器，选中的项为注册的事件
			Iterator iterator = this.selector.selectedKeys().iterator();
			while (iterator.hasNext()) {
				SelectionKey key = (SelectionKey) iterator.next();
				// 删除已选的key,以防重复处理
				iterator.remove();
				// 客户端请求连接事件
				if (key.isAcceptable()) {
					ServerSocketChannel server = (ServerSocketChannel) key
							.channel();
					// 获得和客户端连接的通道
					SocketChannel channel = server.accept();
					// 设置成非阻塞
					channel.configureBlocking(false);

					// //在这里可以给客户端发送信息哦
					// channel.write(ByteBuffer.wrap(new
					// String("向客户端发送了一条信息").getBytes()));
					// 在和客户端连接成功之后，为了可以接收到客户端的信息，需要给通道设置读的权限。
					channel.register(this.selector, SelectionKey.OP_READ);

					System.out.println("有一个客户端注册上来了。。。");
					System.out.println("Client:" + channel.getRemoteAddress());
					System.out.println("Server:" + channel.getLocalAddress());
					channel.getClass();
					System.out.println("\n等待客户端输入。。。");
				} else if (key.isReadable()) { // 获得了可读的事件
				// read(key);
					readDataFromSocket(key);
				}

			}

		}
	}

	/**
	 * 处理读取客户端发来的信息 的事件
	 * 
	 * @param key
	 * @throws IOException
	 */
	public void read(SelectionKey key) throws IOException {
		// 服务器可读取消息:得到事件发生的Socket通道
		SocketChannel channel = (SocketChannel) key.channel();
		// 创建读取的缓冲区
		ByteBuffer buffer = ByteBuffer.allocate(100);
		channel.read(buffer);
		byte[] data = buffer.array();
		String msg = new String(data).trim();
		System.out.println("服务端收到信息：" + msg);
		ByteBuffer outBuffer = ByteBuffer.wrap(("Server已收到刚发送的:" + msg)
				.getBytes());
		channel.write(outBuffer);// 将消息回送给客户端
		System.out.println("\n等待客户端输入。。。");
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
				// socketChannel.write(ByteBuffer.wrap(("Server已收到刚发送的:").getBytes()));
				socketChannel.write(buffer1);

			}
			System.out.println("\n等待客户端输入。。。");
			// WARNING: the above loop is evil. Because
			// it's writing back to the same nonblocking
			// channel it read the data from, this code can
			// potentially spin in a busy loop. In real life
			// you'd do something more useful than this.
			buffer1.clear(); // Empty buffer
		}
		if (count < 0) {
			// Close channel on EOF, invalidates the key
			// socketChannel.close();
		}
	}

	/**
	 * 启动服务端测试
	 * 
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		NioServer server = new NioServer();
		server.initServer(8888);
		server.listen();
	}
}
