package com.austin.netty.nettySendAndRec;

import com.austin.netty.nettyStringDecoder.MyChannelInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;


/**
 * @ClassName: NettyServer
 * @description: 通过writeAndFlush发送ByteBuf字节码向客户端传输信息
 * @author: zqz
 * @date: 2024/9/4 23:14
 */

public class NettyServer {
    public static void main(String[] args) {
        new NettyServer().bind(7379);
    }

    private void bind(int port) {
        //配置服务端NIO线程组
        EventLoopGroup parentGroup = new NioEventLoopGroup(); //NioEventLoopGroup extends MultithreadEventLoopGroup Math.max(1, SystemPropertyUtil.getInt("io.netty.eventLoopThreads", NettyRuntime.availableProcessors() * 2));
        EventLoopGroup childGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(parentGroup, childGroup)
                    //非阻塞模式
                    .channel(NioServerSocketChannel.class)
                    //设置服务器套接字的请求队列长度
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childHandler(new MyChannelInitializer());
            //异步绑定port
            ChannelFuture f = b.bind(port).sync();
            System.out.println("netty-demo start done");
            //ChannelFuture是代表channel关闭的状态。这里的意义是等待channel关闭完成
            f.channel().closeFuture().sync();
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            parentGroup.shutdownGracefully();
            childGroup.shutdownGracefully();
        }
    }
}
