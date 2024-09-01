package com.austin.netty.IO.AIO.client;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.Charset;
import java.util.concurrent.Future;

/**
 * @ClassName: AioClient
 * @Description: Aio客户端
 * @author: zqz
 * @date: 2024/9/1 15:56
 */
public class AioClient {
    public static void main(String[] args) throws Exception {
        AsynchronousSocketChannel socketChannel = AsynchronousSocketChannel.open();
        Future<Void> future = socketChannel.connect(new InetSocketAddress("127.0.0.1", 8080));
        System.out.println("netty-demo client start done");
        future.get();
        socketChannel.read(ByteBuffer.allocate(1024), null, new AioClientHandler(socketChannel, Charset.forName("GBK")));
        Thread.sleep(100000);
    }
}
