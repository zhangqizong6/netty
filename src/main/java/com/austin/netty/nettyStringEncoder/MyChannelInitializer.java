package com.austin.netty.nettyStringEncoder;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.nio.charset.Charset;

/**
 * @ClassName: MyChannelInitializer
 * @description: 字符串编码
 * @author: zqz
 * @date: 2024/9/5 20:49
 */

public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        //基于换行符号
        socketChannel.pipeline().addLast(new LineBasedFrameDecoder(1024));
        //解码转string
        socketChannel.pipeline().addLast(new StringDecoder(Charset.forName("GBK")));
        //编码转string
        socketChannel.pipeline().addLast(new StringEncoder(Charset.forName("GBK")));
        //在管道加入自己的接收数据的实现方法
        socketChannel.pipeline().addLast(new MyServerHandler());
    }
}
