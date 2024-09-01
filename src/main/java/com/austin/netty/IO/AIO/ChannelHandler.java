package com.austin.netty.IO.AIO;

import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.Charset;

/**
 * @ClassName: ChannelHandler
 * @description:
 * @author: zqz
 * @date: 2024/9/1 16:16
 */

public class ChannelHandler{

    private AsynchronousSocketChannel channel;
    private Charset charset;

    public ChannelHandler(AsynchronousSocketChannel channel, Charset charset)
    {
        this.channel = channel;
        this.charset = charset;
    }

}
