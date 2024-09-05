package com.austin.netty.nettyChannelGroup;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.SocketChannel;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName: MyServerHandler
 * @description:
 * @author: zqz
 * @date: 2024/9/5 21:04
 */

public class MyServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //当有客户端链接后，添加到channelGroup通信组
        ChannelHandler.channelGroup.add(ctx.channel());
        //日志信息
        SocketChannel channel = (SocketChannel) ctx.channel();
        System.out.println("链接报告开始");
        System.out.println("链接报告信息：有一客户端链接到本服务端");
        System.out.println("链接报告IP:" + channel.localAddress().getHostString());
        System.out.println("链接报告Port:" + channel.localAddress().getPort());
        System.out.println("链接报告完毕");
        //通知客户端链接建立成功
        String str = "通知客户端链接建立成功" + " " + new Date() + " " + channel.localAddress().getHostString() + "\r\n";
        ctx.writeAndFlush(str);
    }

    /**
     * 当客户端主动断开服务的时候，通道就是不活跃的了，
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端断开链接" + ctx.channel().localAddress().toString());
        // 当有客户端退出后，从channelGroup中移除
        ChannelHandler.channelGroup.remove(ctx.channel());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //接收msg消息{此处已经不需要自己进行解码}
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " 接收到消息：" + msg);
        //收到消息后，群发给客户端
        String str = "服务端收到：" + new Date() + " " + msg + "\r\n";
        ChannelHandler.channelGroup.writeAndFlush(str);
    }

    /**
     * 抓住异常，当发生异常的时候，可以做一些相应的处理，比如打印日志、关闭链接
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        System.out.println("异常信息：\r\n" + cause.getMessage());
    }
}
