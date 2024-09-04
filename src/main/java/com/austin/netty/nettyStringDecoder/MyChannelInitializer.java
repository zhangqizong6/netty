package com.austin.netty.nettyStringDecoder;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

import java.nio.charset.Charset;

/**
 * @ClassName: MyChannelInitializer
 * @description: 启动初始化类
 * @author: zqz
 * @date: 2024/9/4 22:42
 */

public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        /*解码器*/

        //基于换行符号的解码器
        socketChannel.pipeline().addLast(new LineBasedFrameDecoder(1024));

        //转码转string
        socketChannel.pipeline().addLast(new StringDecoder(Charset.forName("GBK")));

        //最后在管道中加入我们自己实现的的接受数据的方式
        socketChannel.pipeline().addLast(new MyServerHandler());

    }
}
