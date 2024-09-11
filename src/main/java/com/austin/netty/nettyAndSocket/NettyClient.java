package com.austin.netty.nettyAndSocket;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @ClassName: NettyClient
 * @description:
 * @author: zqz
 * @date: 2024/9/11 22:52
 */

public class NettyClient {
    public static void main(String[] args) {
        new NettyClient().connect("127.0.0.1", 7397);
    }

    private void connect(String inetHost, int inetPort) {
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            //将工作线程组设置为 workerGroup，用于处理 I/O 操作。
            b.group(workerGroup);
            //指定通道类型为 NioSocketChannel，适用于基于 NIO 的 TCP 连接
            b.channel(NioSocketChannel.class);
            //设置自动读取模式为开启，即通道默认从缓冲区读取数据
            b.option(ChannelOption.AUTO_READ, true);
            b.handler(new MyChannelInitializer());
            ChannelFuture f = b.connect(inetHost, inetPort).sync();
            System.out.println("demo-netty client start done.");
            f.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}
