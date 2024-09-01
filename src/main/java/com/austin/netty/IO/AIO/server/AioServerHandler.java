package com.austin.netty.IO.AIO.server;

import com.austin.netty.IO.AIO.ChannelAdapter;
import com.austin.netty.IO.AIO.ChannelHandler;

import java.io.IOException;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.Charset;
import java.util.Date;

/**
 * @ClassName: AioServerHandler
 * @description: 处理消息
 * @author: zqz
 * @date: 2024/9/1 16:35
 */

public class AioServerHandler extends ChannelAdapter {

    public AioServerHandler(AsynchronousSocketChannel channel, Charset charset) {
        super(channel, charset);
    }

    @Override
    public void channelActive(ChannelHandler channelAdapter) {
        try {
            System.out.println(" 链接报告信息:" + channelAdapter.channel().getRemoteAddress());
            //通知客户端链接建立成功
            channelAdapter.writeAndFlush("通知服务端链接建立成功" + " " + new Date() + " " + channelAdapter.channel().getRemoteAddress() + "\r\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void channelInactive(ChannelHandler ctx) {

    }

    @Override
    public void channelRead(ChannelHandler ctx, Object msg) {
        System.out.println("服务端收到：" + new Date() + " " + msg + "\r\n");
        ctx.writeAndFlush("服务端信息处理Success！\r\n");
    }
}
