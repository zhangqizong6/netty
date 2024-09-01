package com.austin.netty.IO.BIO.server;

import com.austin.netty.IO.BIO.BioChannelAdapter;
import com.austin.netty.IO.BIO.BioChannelHandler;

import java.net.Socket;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName: BioServerHandler
 * @description:
 * @author: zqz
 * @date: 2024/9/1 23:09
 */

public class BioServerHandler extends BioChannelAdapter {

    public BioServerHandler(Socket socket, Charset charset) {
        super(socket, charset);
    }

    @Override
    public void channelActive(BioChannelHandler ctx) {
        System.out.println("链接报告LocalAddress:" + ctx.socket().getLocalAddress());
        ctx.writeAndFlush("hi! BioServer to msg for you \r\n");
    }

    @Override
    public void channelRead(BioChannelHandler ctx, Object msg) {
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " 接收到消息：" + msg);
        ctx.writeAndFlush("hi 我已经收到你的消息Success！\r\n");
    }
}
