package com.wei.interview.io.netty.c;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public final class HelloClient {

    private final String host;
    private final int port;
    private final String message;

    public HelloClient(String host, int port, String message) {
        this.host = host;
        this.port = port;
        this.message = message;
    }

    private void start() throws InterruptedException {
        //1.创建一个 NioEventLoopGroup 对象实例
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            //2.创建客户端启动引导/辅助类：Bootstrap
            Bootstrap b = new Bootstrap();
            //3.指定线程组
            b.group(group)
                    //4.指定 IO 模型
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline p = ch.pipeline();
                            // 5.这里可以自定义消息的业务处理逻辑
                            p.addLast(new HelloClientHandler(message));
                        }
                    });
            // 6.尝试建立连接
            //ChannelFuture f = b.connect(host, port).sync();
            ChannelFuture f = b.connect(host,port).addListener(future -> {
                if (future.isSuccess()){
                    System.out.println("连接成功!");
                }else {
                    System.out.println("连接失败!");
                }
            }).sync();
            // 7.等待连接关闭（阻塞，直到Channel关闭）
            f.channel().closeFuture().sync();
        } finally {
            // 优雅关闭相关线程组资源
            group.shutdownGracefully();
        }
    }
    public static void main(String[] args) throws Exception {
        new HelloClient("127.0.0.1",9000, "你好,你真帅啊！哥哥！").start();
    }
}