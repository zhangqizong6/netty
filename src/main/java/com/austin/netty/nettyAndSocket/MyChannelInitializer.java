package com.austin.netty.nettyAndSocket;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

/**
 * @ClassName: MyChannelInitializer
 * @description:
 * @author: zqz
 * @date: 2024/9/11 22:51
 */

public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) {
        System.out.println("链接报告开始");
        System.out.println("链接报告信息：本客户端链接到服务端。channelId：" + socketChannel.id());
        System.out.println("链接报告完毕");
    }
}
