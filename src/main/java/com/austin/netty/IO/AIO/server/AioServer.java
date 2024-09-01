package com.austin.netty.IO.AIO.server;

import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;

/**
 * @ClassName: AioServer
 * @description: 服务端
 * @author: zqz
 * @date: 2024/9/1 16:28
 */

public class AioServer extends Thread {

    private AsynchronousServerSocketChannel serverSocketChannel;

    @Override
    public void run() {

        try {
            //线程池开始监听
            serverSocketChannel = AsynchronousServerSocketChannel
                    .open(AsynchronousChannelGroup.withCachedThreadPool(Executors.newCachedThreadPool(), 10));
            //绑定端口号
            serverSocketChannel.bind(new InetSocketAddress(7397));
            System.out.println("netty-demo server start done");
            // 等待
            CountDownLatch latch = new CountDownLatch(1);
            serverSocketChannel.accept(this, new AioServerChannelInitializer());
            latch.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public AsynchronousServerSocketChannel serverSocketChannel() {
        return serverSocketChannel;
    }

    public static void main(String[] args) {
        new AioServer().start();
    }
}
