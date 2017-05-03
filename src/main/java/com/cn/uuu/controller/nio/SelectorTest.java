package com.cn.uuu.controller.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class SelectorTest {
public static void main(String[] args) throws IOException {
//	RandomAccessFile rad = new RandomAccessFile("", "rw");
	SocketChannel channel = SocketChannel.open();
	channel.connect(new InetSocketAddress("http://jenkov.com", 80));
	Selector selector = java.nio.channels.Selector.open();
	channel.configureBlocking(false);
	SelectionKey key = channel.register(selector, SelectionKey.OP_READ);
	while(true) {
	  int readyChannels = selector.select();
	  if(readyChannels == 0) continue;
	  Set selectedKeys = selector.selectedKeys();
	  Iterator keyIterator = selectedKeys.iterator();
	  while(keyIterator.hasNext()) {
	    SelectionKey key1 = (SelectionKey) keyIterator.next();
	    if(key.isAcceptable()) {
					// a connection was accepted by a ServerSocketChannel.
	    } else if (key.isConnectable()) {
	        // a connection was established with a remote server.
	    } else if (key.isReadable()) {
	        // a channel is ready for reading
	    } else if (key.isWritable()) {
	        // a channel is ready for writing
	    }
	    keyIterator.remove();
	  }
	}
}
}
