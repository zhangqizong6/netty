package com.austin.netty.nettyStringDecoder;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @ClassName: NettyServer
 * @description: netty字符串解码器 netty提供了很丰富的解码器，在正确合理的使用下就能解决半包粘包问题。
 * @author: zqz
 * @date: 2024/9/4 22:48
 */

/**
 * 这里描述一下沾包问题
 * 由于 TCP 是面向流的协议，它不会为每个消息提供明确的边界，因此多个消息可能会被合并成一个数据包发送，或者一个消息可能被分成多个数据包发送。
 *
 * 粘包问题
 * 粘包是指发送方发送的两个或多个数据包粘连在一起，接收方一次读取多个数据包，导致无法区分各个独立的数据包。
 *
 * 半包问题
 * 半包是指由于一次读取操作只读取到一个数据包的一部分，导致一次读取操作无法获得一个完整的数据包。
 */
public class NettyServer {

    public static void main(String[] args) {
        new NettyServer().bing(7379);
    }

    private void bing(int port) {
        //配置服务端NIO线程组
        EventLoopGroup parentGroup = new NioEventLoopGroup();
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
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            parentGroup.shutdownGracefully();
            childGroup.shutdownGracefully();
        }
    }
}
