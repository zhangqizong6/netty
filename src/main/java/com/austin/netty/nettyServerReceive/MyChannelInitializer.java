package com.austin.netty.nettyServerReceive;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;


/**
 * @ClassName: MyChannelInitializer
 * @description: 用netty编写一个能接收数据的socketServer服务端
 * @author: zqz
 * @date: 2024/9/3 21:30
 */

public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        System.out.println("链接报告开始");
        System.out.println("链接报告信息：有一客户端链接到本服务端");
        System.out.println("链接报告IP:" + socketChannel.localAddress().getHostString());
        System.out.println("链接报告Port:" + socketChannel.localAddress().getPort());
        System.out.println("链接报告完毕");

        //在管道中添加我们自己的接收数据实现方法
        //pipeline管道  addLast管道的末尾 加一个自己的处理器
        socketChannel.pipeline().addLast(new MyServerHandler());

    }
}
