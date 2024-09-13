package com.austin.netty.netty0913_2;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @ClassName: MyEncoder
 * @description:
 * @author: zqz
 * @date: 2024/9/13 22:21
 */

public class MyEncoder extends MessageToByteEncoder {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object in, ByteBuf out) throws Exception {
        String msg = in.toString();
        byte[] bytes = msg.getBytes();

        byte[] send = new byte[bytes.length + 2];
        //将数组 bytes 中的数据复制到数组 send 中
        //从 bytes 的索引 0 开始读取数据。复制到 send 的索引 1 开始的位置。复制的长度为 bytes 数组的长度。
        System.arraycopy(bytes, 0, send, 1, bytes.length);
        //设置包头尾标志
        send[0] = 0x02;
        send[send.length - 1] = 0x03;
        out.writeInt(send.length);
        out.writeBytes(send);
    }
}
