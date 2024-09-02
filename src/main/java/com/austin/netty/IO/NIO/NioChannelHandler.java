package com.austin.netty.IO.NIO;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 * @ClassName: NioChannelHandler
 * @description:
 * @author: zqz
 * @date: 2024/9/2 21:38
 */

public class NioChannelHandler {

    private SocketChannel channel;
    private Charset charset;

    public NioChannelHandler(SocketChannel channel, Charset charset) {
        this.channel = channel;
        this.charset = charset;
    }

    public void writeAndFlush(Object msg) {
        try {
            byte[] bytes = msg.toString().getBytes(charset);
            ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
            writeBuffer.put(bytes);
            //翻转了缓冲区的读写位置，从写模式切换到读模式。
            writeBuffer.flip();
            channel.write(writeBuffer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public SocketChannel channel() {
        return channel;
    }

}
