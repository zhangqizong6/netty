package com.austin.netty.netty0913_2;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.nio.charset.Charset;
import java.util.List;

/**
 * @ClassName: MyDecoder
 * @description: 自定义编码器
 * @author: zqz
 * @date: 2024/9/13 22:10
 */

public class MyDecoder extends ByteToMessageDecoder {

    //数据包基础长度
    private final int BASE_LENGTH = 4;

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf in, List<Object> out) throws Exception {

        //如果基础长度不足 直接返回
        if (in.readableBytes() < BASE_LENGTH) {
            return;
        }

        //记录包头位置
        int beginIdx;
        while (true) {
            //获取包头开始的位置
            beginIdx = in.readerIndex();
            //标记包头开始的index
            in.markReaderIndex();
            //读到协议的开始的标志，结束循环
            if (in.readByte() == 0x02) {
                break;
            }
            //未读到包头 略过一个字节 ,每次略过;一个字节，去读取，包头信息的开始标记
            in.resetReaderIndex();
            in.readByte();
            // 当略过，一个字节之后;数据包的长度，又变得不满足;此时，应该结束。等待后面的数据到达
            if (in.readableBytes() < BASE_LENGTH) {
                return;
            }
        }

        //剩余长度不足可读取数量
        int readableCount = in.readableBytes();
        if (readableCount < 1) {
            in.readerIndex(beginIdx);
            return;
        }

        //长度域占四个字节 读取int
        ByteBuf byteBuf = in.readBytes(1);
        String msgLengthStr = byteBuf.toString(Charset.forName("GBK"));
        int msgLength = Integer.parseInt(msgLengthStr);

        //剩余长度不足可读取数量[没有结尾标识]
        readableCount = in.readableBytes();
        if (readableCount < msgLength + 1) {
            in.readerIndex(beginIdx);
            return;
        }

        ByteBuf msgContent = in.readBytes(msgLength);
        //如果没有结尾标识，还原指针位置[其他标识结尾]
        byte end = in.readByte();
        if (end != 0x03) {
            in.readerIndex(beginIdx);
            return;
        }

        out.add(msgContent.toString(Charset.forName("GBK")));

    }
}
