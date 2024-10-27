package com.austin.netty.nettyChannelByJava.codec;

import com.austin.netty.nettyChannelByJava.utils.SerializationUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @ClassName: ObjDecoder
 * @description: 解码器 用于对java对象的解码
 * @author: zqz
 * @date: 2024/10/27 20:02
 */

public class ObjDecoder extends ByteToMessageDecoder {

    private Class<?> genericClass;

    public ObjDecoder(Class<?> genericClass) {
        this.genericClass = genericClass;
    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {

        if (byteBuf.readableBytes() < 4) {
            return;
        }
        byteBuf.markReaderIndex();
        int dataLength = byteBuf.readInt();
        if (byteBuf.readableBytes() < dataLength) {
            byteBuf.resetReaderIndex();
            return;
        }
        byte[] data = new byte[dataLength];
        byteBuf.readBytes(byteBuf);
        list.add(SerializationUtil.deserialize(data, genericClass));

    }
}
