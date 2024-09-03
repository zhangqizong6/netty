package com.austin.netty.nettyDemo;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;


/**
 * @ClassName: MyChannelInitializer
 * @description:
 * @author: zqz
 * @date: 2024/9/3 20:56
 */

public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) {
        System.out.println("链接报告开始");
        System.out.println("链接报告信息：有一客户端链接到本服务端");
        System.out.println("链接报告IP:" + socketChannel.localAddress().getHostString());
        System.out.println("链接报告Port:" + socketChannel.localAddress().getPort());
        System.out.println("链接报告完毕");
    }
}
