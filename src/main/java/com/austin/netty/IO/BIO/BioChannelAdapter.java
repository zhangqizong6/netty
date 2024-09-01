package com.austin.netty.IO.BIO;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * @ClassName: ChannelAdapter bio适配器
 * @description:
 * @author: zqz
 * @date: 2024/9/1 22:42
 */

public abstract class BioChannelAdapter extends Thread {

    private Socket socket;
    private BioChannelHandler channelHandler;
    private Charset charset;

    //实例的初始化
    public BioChannelAdapter(Socket socket, Charset charset) {
        this.socket = socket;
        this.charset = charset;
        while (!socket.isConnected()) {
            break;
        }
        channelHandler = new BioChannelHandler(this.socket, charset);
        channelActive(channelHandler);
    }

    @Override
    public void run() {
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(this.socket.getInputStream(), charset));
            String str = null;
            while ((str = input.readLine()) != null) {
                channelRead(channelHandler, str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 链接通知抽象类
    public abstract void channelActive(BioChannelHandler ctx);

    // 读取消息抽象类
    public abstract void channelRead(BioChannelHandler ctx, Object msg);
}
