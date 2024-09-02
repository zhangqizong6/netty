package com.austin.netty.IO.NIO.server;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.charset.Charset;

/**
 * @ClassName: NioServer
 * @description:
 * @author: zqz
 * @date: 2024/9/2 22:03
 */

public class NioServer {

    //Selector 是 Java NIO 中用于多路复用 I/O 操作的组件，用于监听多个通道的事件（如可读、可写等）。
    private Selector selector;
    private ServerSocketChannel socketChannel;

    public static void main(String[] args) {
        new NioServer().bind(8080);
    }

    private void bind(int port) {
        try {
            selector = Selector.open();
            socketChannel = ServerSocketChannel.open();
            socketChannel.socket().bind(new InetSocketAddress(port), 1024);
            socketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("netty-demo-nio server start done.");
            new NioServerHandler(selector, Charset.forName("GBK")).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
