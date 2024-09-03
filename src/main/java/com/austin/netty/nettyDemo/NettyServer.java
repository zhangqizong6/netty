package com.austin.netty.nettyDemo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.ServerSocket;

/**
 * @ClassName: nettyServer
 * @description: netty服务端 案例demo
 * @author: zqz
 * @date: 2024/9/3 20:43
 */

public class NettyServer {

    public static void main(String[] args) {
        new NettyServer().bind(7397);
    }

    private void bind(int port) {
        //配置服务端NIO线程组
        NioEventLoopGroup parentGroup = new NioEventLoopGroup();
        NioEventLoopGroup childGroup = new NioEventLoopGroup();
        try {
            //ServerBootstrap是netty的一个辅助类，用于快速配置启动一个服务端应用
            ServerBootstrap b = new ServerBootstrap();
            b.group(parentGroup, childGroup)
                    //非阻塞模式
                    .channel(NioServerSocketChannel.class)
                    //设置服务器套接字的请求队列的长度，值越大，等待处理的连接请求越多
                    .option(ChannelOption.SO_BACKLOG, 128)
                    //设置子通道处理器
                    .childHandler(new MyChannelInitializer());
            ChannelFuture f = b.bind(port).sync();
            System.out.println("demo-netty server start done.");
            //等待直到当前Channel关闭,确保程序再次阻塞直至通道关闭
            f.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //调用封装的函数优雅的关闭线程组 注意会等待当前正在处理的任务完成
            parentGroup.shutdownGracefully();
            childGroup.shutdownGracefully();
        }
    }

}
