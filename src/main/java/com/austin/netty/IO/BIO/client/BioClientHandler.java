package com.austin.netty.IO.BIO.client;

import com.austin.netty.IO.BIO.BioChannelAdapter;
import com.austin.netty.IO.BIO.BioChannelHandler;

import java.net.Socket;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName: BioClientHandler
 * @description:
 * @author: zqz
 * @date: 2024/9/1 22:41
 */

public class BioClientHandler extends BioChannelAdapter {


    public BioClientHandler(Socket socket, Charset charset) {
        super(socket, charset);
    }

    @Override
    public void channelActive(BioChannelHandler ctx) {
        System.out.println("链接报告LocalAddress:" + ctx.socket().getLocalAddress());
        ctx.writeAndFlush("BioClient to msg for you \r\n");
    }

    @Override
    public void channelRead(BioChannelHandler ctx, Object msg) {
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(new Date()) + " 接收到消息：" + msg);
        ctx.writeAndFlush("hi im receive your Success！\r\n");
    }
}
