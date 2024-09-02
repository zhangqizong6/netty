package com.austin.netty.IO.NIO.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;


/**
 * @ClassName: NioClient
 * @description: NIO客户端
 * @author: zqz
 * @date: 2024/9/2 21:31
 */

public class NioClient {
    public static void main(String[] args) throws IOException {
        // Selector 是 Java NIO 中用于多路复用 I/O 操作的组件，用于监听多个通道的事件（如可读、可写等）。
        Selector selector = Selector.open();
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);

        //连接通道，如果true，则注册到通道监听读事件，否则注册并监听连接事件
        boolean connect = socketChannel.connect(new InetSocketAddress("127.0.0.1", 8080));
        if (connect) {
            socketChannel.register(selector, SelectionKey.OP_READ);
        } else {
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
        }
        System.out.println("netty-demo-nio client start done.");
        new NioClientHandler(selector, Charset.forName("GBK")).start();
    }
}
