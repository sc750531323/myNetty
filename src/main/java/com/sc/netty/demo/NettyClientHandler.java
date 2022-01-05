package com.sc.netty.demo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.concurrent.EventExecutorGroup;

public class NettyClientHandler extends ChannelInboundHandlerAdapter {
       private ByteBuf msgBuffer;

    public NettyClientHandler() {
        byte[] requestBytes = "hello".getBytes();
        //堆内存分配这么大长度的空间
        msgBuffer = Unpooled.buffer(requestBytes.length);
        msgBuffer.writeBytes(requestBytes);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(msgBuffer);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf response = (ByteBuf) msg;
        byte[] responseBytes = new byte[response.readableBytes()];
        //将msg读入responseBytes这个byte数组内
        response.readBytes(responseBytes);
        String res = new String(responseBytes,"UTF-8");
        System.out.println("msg from server:"+res);
    }
}
