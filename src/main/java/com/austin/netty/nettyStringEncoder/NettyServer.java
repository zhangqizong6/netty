package com.austin.netty.nettyStringEncoder;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @ClassName: NettyServer
 * @description: 字符串的编码的使用
 * @author: zqz
 * @date: 2024/9/5 20:53
 */

public class NettyServer {
    public static void main(String[] args) {
        new NettyServer().bind(7379);
    }

    private void bind(int port) {
        //配置服务端NIO线程组
        /**
         * 创建两个NioEventLoopGroup的原因通常如下：
         * 父线程组：负责接收客户端的连接请求，并创建新的Channel来处理这些连接。
         * 子线程组：负责处理已经被接受的连接上的所有I/O操作，如读取、解码、编码和写入数据。
         */
        EventLoopGroup parentGroup = new NioEventLoopGroup();
        EventLoopGroup childGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(parentGroup, childGroup)
                    //非阻塞模式
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childHandler(new MyChannelInitializer());
            ChannelFuture f = b.bind(port).sync();
            System.out.println("demo-netty server start done.");
            f.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            childGroup.shutdownGracefully();
            parentGroup.shutdownGracefully();
        }

    }
}
