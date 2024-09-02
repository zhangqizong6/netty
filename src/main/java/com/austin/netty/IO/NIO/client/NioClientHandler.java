package com.austin.netty.IO.NIO.client;

import com.austin.netty.IO.NIO.NioChannelAdapter;
import com.austin.netty.IO.NIO.NioChannelHandler;

import java.io.IOException;
import java.nio.channels.Selector;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName: NioClientHandler
 * @description:
 * @author: zqz
 * @date: 2024/9/2 21:35
 */

public class NioClientHandler extends NioChannelAdapter {


    public NioClientHandler(Selector selector, Charset charset) {
        super(selector, charset);
    }

    @Override
    public void channelActive(NioChannelHandler ctx) {
        try {
            System.out.println("链接报告LocalAddress:" + ctx.channel().getLocalAddress());
            ctx.writeAndFlush("NioClient to msg for you \r\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void channelRead(NioChannelHandler ctx, Object msg) {
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " 接收到消息：" + msg);
        ctx.writeAndFlush("hi 我已经收到你的消息Success！\r\n");
    }
}
