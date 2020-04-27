package com.wei.common.thread.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @Author: chao.zhu
 * @description:
 * @CreateDate: 2019/01/08
 * @Version: 1.0
 */
public class NioServer02 {
    private Integer port = 9997;
    private ServerSocketChannel server;
    private Selector selector;
    public NioServer02(Integer port){
        this.port = port;
    }

    public void start(){
        try{
            //获得server，类似银行的客服大厅
            server = ServerSocketChannel.open();

            //创建selector。类似创建一个叫号机器
            selector = Selector.open();

            //配置server，为银行的客服大厅设置为非阻塞模式，也就是只要有人来办理业务就进入大厅等待，而不是在门口挤着
            server.configureBlocking(false); //设置为非阻塞模式
            server.bind(new InetSocketAddress(this.port)); //server绑定IP和端口

            //将创建的selector绑定到server上面，把叫号机器搬到客服大厅，这样只有有客户来了就直接在selector上取号
            //首先注册的是接受请求的服务ACCEPT请求。类似
            server.register(selector, SelectionKey.OP_ACCEPT);

            System.out.println("服务器启动完毕。端口号："+this.port);

            //开始处理客户端发送过来的请求
            handle();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void handle()throws Exception{
        //死循环，等待客户端请求
        while(true){
            //获得等待处理的客户端总数
            int clientCount = selector.select();

            //如果等待处理的客户端总数>0,则遍历所有客户端请求挨个处理
            Set<SelectionKey> clientSet = selector.selectedKeys();
            Iterator<SelectionKey> clientIterator = clientSet.iterator();

            //客户端过来的请求都被包装成一个一个SelectionKey。这个selectionKey就是叫号牌
            while (clientIterator.hasNext()){
                //客户进来办事了
                SelectionKey clientTicket = clientIterator.next();

                //请求处理完毕之后需要删除这个叫号单
                clientIterator.remove();
                //处理客户需要办理的业务。ACCEPT业务就像客户叫号一样，叫到你来，你就到柜台准备你的资料
                if(clientTicket.isAcceptable()){
                    //在ACCEPT事件中，注册的是ServiceSocketChannel，所以拿到的是ServiceSocketChannel
                    ServerSocketChannel server = (ServerSocketChannel)clientTicket.channel();
                    //从server里面拿到客户端请求
                    SocketChannel client = server.accept();

                    //将客户端设置为非阻塞
                    client.configureBlocking(false);

                    //客户端注册read事件，那么再从selectionKey里面拿到的就是SocketChannel
                    client.register(selector,SelectionKey.OP_READ);
                }
                if(clientTicket.isReadable()){
                    //上面是SocketChannel在叫号机上面注册read事件，所以这里拿到的就是SocketChannel
                    SocketChannel client = (SocketChannel)clientTicket.channel();

                    //初始化bytebuffer准备读取从客户端发送过来的数据
                    ByteBuffer buffer = ByteBuffer.allocate(3*100);

                    //循环读取直到没有可读内容为止
                    boolean readFlag = true;
                    System.out.println("客户端消息：");
                    while(readFlag){
                        client.read(buffer);
                        if(buffer.hasRemaining()){
                            //固定buffer内容
                            buffer.flip();
                            System.out.print(new String(buffer.array(),"utf-8"));
                        }else{
                            readFlag = false;
                        }
                    }

                    //服务器读取完毕，关闭客户端和叫号单
                    clientTicket.cancel();
                    client.close();
                }
            }
        }
    }

    public static void main(String[] args) {
        new NioServer02(9997).start();
    }

}