package com.wei.interview.io.mashibing;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;

public class SocketNIO {
    //  what   why  how
    public static void main(String[] args) throws Exception {
        LinkedList<SocketChannel> clients = new LinkedList<>();
        ServerSocketChannel ss = ServerSocketChannel.open();
        ss.bind(new InetSocketAddress(9090));
        ss.configureBlocking(false); //重点  OS  NONBLOCKING!!!

        //ss.setOption(StandardSocketOptions.TCP_NODELAY, false);
        //StandardSocketOptions.TCP_NODELAY
        //StandardSocketOptions.SO_KEEPALIVE
        //StandardSocketOptions.SO_LINGER
        //StandardSocketOptions.SO_RCVBUF
        //StandardSocketOptions.SO_SNDBUF
        //StandardSocketOptions.SO_REUSEADDR
/*
    文件描述符（File descriptor）是计算机科学中的一个术语，是一个用于表述指向文件的引用的抽象化概念。文件描述符在形式上是一个非负整数。
    实际上，它是一个索引值，指向内核为每一个进程所维护的该进程打开文件的记录表。当程序打开一个现有文件或者创建一个新文件时，内核向进程返回一个文件描述符。
    在程序设计中，一些涉及底层的程序编写往往会围绕着文件描述符展开。但是文件描述符这一概念往往只适用于UNIX、Linux这样的操作系统。

    套接字也是文件，当server端监听到有连接时，应用程序会请求内核创建Socket，Socket创建好后会返回一个文件描述符给应用程序，当有数据包过来网卡时，
    内核会通过数据包的源端口、源ip、目的端口等在内核维护的一个ipcb双向链表中找到对应的Socket，并将数据包赋值到该Socket的缓冲区，
    应用程序请求读取Socket中的数据时，内核就会将数据拷贝到应用程序的内存空间，从而完成读取Socket数据。
    这里提一下，操作系统针对不同的传输方式（TCP，UDP）会在内核中各自维护一个Socket双向链表，当数据包到达网卡时，会根据数据包的源端口、源ip、目的端口
    从对应的链表中找到其对应的Socket，并会将数据拷贝到Socket的缓冲区，等待应用程序读取
 */

        while (true) {
            //接受客户端的连接
            Thread.sleep(1000);
            SocketChannel client = ss.accept(); //不会阻塞？  -1 NULL
            //accept  调用内核了：1，没有客户端连接进来，返回值？在BIO 的时候一直卡着，但是在NIO ，不卡着，返回-1，NULL
            //如果来客户端的连接，accept 返回的是这个客户端的fd  5，client  object
            //NONBLOCKING 就是代码能往下走了，只不过有不同的情况

            if (client == null) {
                System.out.println("null.....");
            } else {
                client.configureBlocking(false); //重点  socket（服务端的listen socket<连接请求三次握手后，往我这里扔，我去通过accept 得到  连接的socket>，连接socket<连接后的数据读写使用的> ）
                int port = client.socket().getPort();
                System.out.println("client...port: " + port);
                clients.add(client);
            }

            ByteBuffer buffer = ByteBuffer.allocateDirect(4096);  //可以在堆里   堆外
            //遍历已经链接进来的客户端能不能读写数据
            for (SocketChannel c : clients) {   //串行化！！！！  多线程！！
                int num = c.read(buffer);  // >0  -1  0   //不会阻塞
                if (num > 0) {
                    buffer.flip();
                    byte[] aaa = new byte[buffer.limit()];
                    buffer.get(aaa);

                    String b = new String(aaa);
                    System.out.println(c.socket().getPort() + " : " + b);
                    buffer.clear();
                }
            }
        }
    }

}
