package com.wei.interview.io.netty.a;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * 客户端消费者对象
 * This is an netty Client
 * @author ZX
 * @date   2018年6月7日
 */
public class ClientTest {
    public void connect(String host, int port) throws Exception {
        EventLoopGroup worker = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            /**
             *EventLoop的组
             */
            b.group(worker);
            /**
             * 用于构造socketchannel工厂
             */
            b.channel(NioSocketChannel.class);
            /**设置选项
             * 参数：Socket的标准参数（key，value），可自行百度
               保持呼吸，不要断气！
             * */
            b.option(ChannelOption.SO_KEEPALIVE, true);
            /**
             * 自定义客户端Handle（客户端在这里搞事情）
             */
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new SimpleClientHandler());
                }
            });
            /** 开启客户端监听*/
            ChannelFuture f = b.connect(host, port).sync();
            /**等待数据直到客户端关闭*/
            f.channel().closeFuture().sync();
        } finally {
            worker.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        ClientTest client=new ClientTest();
        client.connect("127.0.0.1", 9999);

    }
}