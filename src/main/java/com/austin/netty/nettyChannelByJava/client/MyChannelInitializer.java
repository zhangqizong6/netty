package com.austin.netty.nettyChannelByJava.client;

import com.austin.netty.nettyChannelByJava.codec.ObjDecoder;
import com.austin.netty.nettyChannelByJava.codec.ObjEncoder;
import com.austin.netty.nettyChannelByJava.domain.MsgInfo;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

/**
 * @ClassName: MyChannelInitializer
 * @description:
 * @author: zqz
 * @date: 2024/10/27 20:01
 */

public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        //对象传输处理
        channel.pipeline().addLast(new ObjDecoder(MsgInfo.class));
        channel.pipeline().addLast(new ObjEncoder(MsgInfo.class));
        // 在管道中添加我们自己的接收数据实现方法
        channel.pipeline().addLast(new MyClientHandler());
    }
}
