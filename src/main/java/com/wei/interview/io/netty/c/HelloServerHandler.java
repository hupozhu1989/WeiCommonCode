package com.wei.interview.io.netty.c;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;

@ChannelHandler.Sharable
public class HelloServerHandler extends ChannelInboundHandlerAdapter {

    //服务端接收客户端发送数据调用的方法
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try {
            ByteBuf in = (ByteBuf) msg;
            System.out.println("message from client:" + in.toString(CharsetUtil.UTF_8));
            // 发送消息给客户端
            ctx.writeAndFlush(Unpooled.copiedBuffer("你也好！", CharsetUtil.UTF_8));
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    //处理客户端消息发生异常的时候被调用
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }
}