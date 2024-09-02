package com.austin.netty.IO.NIO;

import com.austin.netty.IO.AIO.ChannelHandler;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * @ClassName: NioChannelAdapter
 * @description:
 * @author: zqz
 * @date: 2024/9/2 21:37
 */

public abstract class NioChannelAdapter extends Thread {

    private Selector selector;

    private NioChannelHandler channelHandler;
    private Charset charset;

    public NioChannelAdapter(Selector selector, Charset charset) {
        this.selector = selector;
        this.charset = charset;
    }

    @Override
    public void run() {
        try {
            //使(selector)在1000毫秒内阻塞等待，直至有注册过的通道变得可读或操作超时。
            selector.select(1000);
            //获取所有已选择的键集合,这些键代表已准备好进行相应操作（如读、写）的通道。
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            SelectionKey key = null;
            while (iterator.hasNext()) {
                key = iterator.next();
                iterator.remove();
                this.handleInput(key);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleInput(SelectionKey key) throws IOException {
        if (!key.isValid()) return;

        // 客户端SocketChannel
        Class<?> superclass = key.channel().getClass().getSuperclass();
        if (superclass == SocketChannel.class) {
            SocketChannel socketChannel = (SocketChannel) key.channel();
            if (key.isConnectable()) {
                if (socketChannel.finishConnect()) {
                    channelHandler = new NioChannelHandler(socketChannel, charset);
                    channelActive(channelHandler);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                }
            } else {
                //使程序立即退出，并返回状态码1,状态码1通常表示程序存在错误或异常情况。
                System.exit(1);
            }
        }

        //服务端ServerSocketChannel
        if (superclass == ServerSocketChannel.class) {
            if (key.isAcceptable()) {
                ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
                SocketChannel socketChannel = serverSocketChannel.accept();
                //服务端 设置为非阻塞模式
                socketChannel.configureBlocking(false);
                socketChannel.register(selector, SelectionKey.OP_READ);

                NioChannelHandler nioChannelHandler = new NioChannelHandler(socketChannel, charset);
                channelActive(channelHandler);
            }
        }

        //检查给定的key是否可读。
        if (key.isReadable()) {
            SocketChannel socketChannel = (SocketChannel) key.channel();
            ByteBuffer readBuffer = ByteBuffer.allocate(1024);
            int readBytes = socketChannel.read(readBuffer);
            if (readBytes > 0) {
                readBuffer.flip();
                byte[] bytes = new byte[readBuffer.remaining()];
                readBuffer.get(bytes);
                channelRead(channelHandler, new String(bytes, charset));
            } else if (readBytes < 0) {
                key.cancel();
                socketChannel.close();
            }
        }
    }

    // 链接通知抽象类
    public abstract void channelActive(NioChannelHandler ctx);

    // 读取消息抽象类
    public abstract void channelRead(NioChannelHandler ctx, Object msg);
}
