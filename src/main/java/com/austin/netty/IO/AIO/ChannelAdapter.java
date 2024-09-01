package com.austin.netty.IO.AIO;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: ChannelAdapter
 * @Description: Channel适配器模仿Netty
 * @author: zqz
 * @date: 2024/9/1 16:07
 */
public abstract class ChannelAdapter implements CompletionHandler<Integer, Object> {

    private AsynchronousSocketChannel channel;
    private Charset charset;

    public ChannelAdapter(AsynchronousSocketChannel channel, Charset charset) {
        this.channel = channel;
        this.charset = charset;
        if (channel.isOpen()) {
            channelActive(new ChannelHandler(channel, charset));
        }
    }

    @Override
    public void completed(Integer result, Object attachment) {

        try {
            //定义一个buffer对象，用于数据的存储和传输
            final ByteBuffer buffer = ByteBuffer.allocate(1024);
            //设置操作的超时时间
            final long timeout = 60 * 60L;
            channel.read(buffer, timeout, TimeUnit.SECONDS, null, new CompletionHandler<Integer, Object>() {
                @Override
                public void completed(Integer result, Object attachment) {

                    if (result == -1) {
                        try {
                            channelInactive(new ChannelHandler(channel, charset));
                            channel.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return;
                    }
                    buffer.flip();
                    channelRead(new ChannelHandler(channel, charset), charset.decode(buffer));
                    buffer.clear();
                    channel.read(buffer, timeout, TimeUnit.SECONDS, null, this);
                }

                @Override
                public void failed(Throwable exc, Object attachment) {
                    exc.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void failed(Throwable exc, Object attachment) {
        exc.printStackTrace();
    }

    public abstract void channelActive(ChannelHandler channelAdapter);

    public abstract void channelInactive(ChannelHandler ctx);

    // 读取消息抽象类
    public abstract void channelRead(ChannelHandler ctx, Object msg);
}
