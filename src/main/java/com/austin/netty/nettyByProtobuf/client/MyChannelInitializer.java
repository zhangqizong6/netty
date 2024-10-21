package com.austin.netty.nettyByProtobuf.client;

/**
 * @ClassName: MyChannelInitializer
 * @description:
 * @author: zqz
 * @date: 2024/10/21 20:31
 */

public class MyChannelInitializer extends ChannelInitializer<SocketChannel>{

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        //protobuf 处理
        channel.pipeline().addLast(new ProtobufVarint32FrameDecoder());
        channel.pipeline().addLast(new ProtobufDecoder(MsgBody.getDefaultInstance()));
        channel.pipeline().addLast(new ProtobufVarint32LengthFieldPrepender());
        channel.pipeline().addLast(new ProtobufEncoder());
        // 在管道中添加我们自己的接收数据实现方法
        channel.pipeline().addLast(new MyClientHandler());
    }

}
