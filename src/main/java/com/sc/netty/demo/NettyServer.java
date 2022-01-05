package com.sc.netty.demo;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServer {

    public static void main(String[] args) {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        ServerBootstrap bs = new ServerBootstrap();
        try {
            bs.group(bossGroup,workerGroup).channel(NioServerSocketChannel.class).bind(5000).sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
