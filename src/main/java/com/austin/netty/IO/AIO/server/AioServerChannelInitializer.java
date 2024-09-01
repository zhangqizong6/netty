package com.austin.netty.IO.AIO.server;

import com.austin.netty.IO.AIO.ChannelInitializer;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: AioServerChannelInitializer
 * @description:
 * @author: zqz
 * @date: 2024/9/1 16:34
 */

public class AioServerChannelInitializer extends ChannelInitializer {

    @Override
    protected void initChannel(AsynchronousSocketChannel channel) throws Exception {
        channel.read(ByteBuffer.allocate(1024),
                10,
                TimeUnit.SECONDS,
                null,
                new AioServerHandler(channel, Charset.forName("GBK")));
    }

}
