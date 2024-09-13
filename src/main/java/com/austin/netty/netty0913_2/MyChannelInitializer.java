package com.austin.netty.netty0913_2;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

/**
 * @ClassName: MyChannelInitializer
 * @description:
 * @author: zqz
 * @date: 2024/9/13 22:24
 */

public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        //自定义解码器
        socketChannel.pipeline().addLast(new MyDecoder());
        //自定义编码器
        socketChannel.pipeline().addLast(new MyEncoder());
        //在管道中添加我们自己的接收数据实现方法
        socketChannel.pipeline().addLast(new MyServerHandler());
    }
}
