package com.cn.uuu.controller.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

import org.junit.Test;

public class BufferFillDrain {
	public static void main(String[] args) {

		/**
		 * 新的缓冲区是由分配或包装操作创建的。分配操作创建一个缓冲区对象并分配一个私有的
		 * 空间来储存容量大小的数据元素。包装操作创建一个缓冲区对象但是不分配任何空间来储存数
		 * 据元素。它使用您所提供的数组作为存储空间来储存缓冲区中的数据元素。 要分配一个容量为 100 个 char 变量的 Charbuffer:
		 * CharBuffer charBuffer = CharBuffer.allocate (100); 这段代码隐含地从堆空间中分配了一个
		 * char 型数组作为备份存储器来储存 100 个 char变量。 如果您想提供您自己的数组用做缓冲区的备份存储器，请调用
		 * wrap()函数： char [] myArray = new char [100]; CharBuffer charbuffer =
		 * CharBuffer.wrap (myArray); 这段代码构造了一个新的缓冲区对象，但数据元素会存在于数组中。这意味着通过调用
		 * put()函数造成的对缓冲区的改动会直接影响这个数组，而且对这个数组的任何改动也会对这 个缓冲区对象可见。
		 * 
		 * 通过 allocate()或者 wrap()函数创建的缓冲区通常都是间接的。间接的缓冲区使用备份数组，
		 * 像我们之前讨论的，您可以通过上面列出的API 函数获得对这些数组的存取权。 Boolean 型函数
		 * hasArray()告诉您这个缓冲区是否有一个可存取的备份数组。 如果这个函数的返回
		 * true，array()函数会返回这个缓冲区对象所使用的数组存储空间的引用。
		 * 
		 * 直接 ByteBuffer 是通过调用具有所需容量的 ByteBuffer.allocateDirect()函数产生的
		 * 所有的缓冲区都提供了一个叫做 isDirect()的 boolean 函数，来测试特定缓冲区是否为直接缓冲区。无备份数组
		 * 
		 * 
		 * 通道（Channel）
		 */
		CharBuffer buffer = CharBuffer.allocate(100);
		while (fillBuffer(buffer)) {
			// Flip()函数将一个能够继续添加数据元素的填充状态的缓冲区翻转成一个准备读出元素的释放状态
			buffer.flip();
			drainBuffer(buffer);
			/**
			 * compact(): 将 position 与 limit之间的数据复制到buffer的开始位置， 复制后 position =
			 * limit -position,limit = capacity 但如果position 与limit
			 * 之间没有数据的话发，就不会进行复制
			 */
			// buffer.compact();
			/**
			 * clear()函数将缓冲区重置为空状态。它并不改变缓冲区中的任何数据元素， 而是仅仅将上界设为容量的值，并把位置设回 0
			 */
			buffer.clear();
		}
	}

	private static void drainBuffer(CharBuffer buffer) {
		// 是否已经达到缓冲区的上界
		while (buffer.hasRemaining()) {
			int i = buffer.remaining();
			System.out.print(buffer.get());
		}
		System.out.println("");
	}

	private static boolean fillBuffer(CharBuffer buffer) {
		if (index >= strings.length) {
			return (false);
		}
		String string = strings[index++];
		for (int i = 0; i < string.length(); i++) {
			buffer.put(string.charAt(i));
		}
		return (true);
	}

	private static int index = 0;
	private static String[] strings = {
			"The product of an infinite number of monkeys",
			"Hey hey we're the Monkees", };

	private static void channelCopy1(ReadableByteChannel src,
			WritableByteChannel dest) throws IOException {
		ByteBuffer buffer = ByteBuffer.allocateDirect(16 * 1024);
		while (src.read(buffer) != -1) {
			// Prepare the buffer to be drained
			buffer.flip();
			// Write to the channel; may block
			dest.write(buffer);
			// If partial transfer, shift remainder down
			// If buffer is empty, same as doing clear( )
			buffer.compact();
		}
		// EOF will leave buffer in fill state
		buffer.flip();
		// Make sure that the buffer is fully drained
		while (buffer.hasRemaining()) {
			dest.write(buffer);
		}
	}

	@Test
	public void ChannelCopy() throws IOException {
		ReadableByteChannel source = Channels.newChannel(System.in);
		WritableByteChannel dest = Channels.newChannel(System.out);
		channelCopy1(source, dest);
		// alternatively, call channelCopy2 (source, dest);
		source.close();
		dest.close();
	}

}
