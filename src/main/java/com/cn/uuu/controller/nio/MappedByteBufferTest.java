package com.cn.uuu.controller.nio;

/**
 * 内存映射文件
 * @author 10539
 *  新的 FileChannel 类提供了一个名为 map()的方法，该方法可以在一个打开的文件和一个特殊
 	类型的 ByteBuffer 之间建立一个虚拟内存映射（第一章中已经归纳了什么是内存映射文件以及它们
	如何同虚拟内存交互）。在 FileChannel 上调用 map()方法会创建一个由磁盘文件支持的虚拟内存
	映射（virtual memory mapping）并在那块虚拟内存空间外部封装一个 MappedByteBuffer 对象（参见
	图 1-6）。
	2.由 map()方法返回的 MappedByteBuffer 对象的行为在多数方面类似一个基于内存的缓冲区，只
	不过该对象的数据元素存储在磁盘上的一个文件中。调用 get()方法会从磁盘文件中获取数据，此
	数据反映该文件的当前内容，即使在映射建立之后文件已经被一个外部进程做了修改。通过文件映
	射看到的数据同您用常规方法读取文件看到的内容是完全一样的。相似地，对映射的缓冲区实现一
	个 put()会更新磁盘上的那个文件（假设对该文件您有写的权限），并且您做的修改对于该文件的
	其他阅读者也是可见的。
	3.FileChannel中public abstract MappedByteBuffer map (MapMode mode, long position,long size)
	
	
	
	
 */
public class MappedByteBufferTest {

}
