package com.austin.netty.IO.BIO.client;

import com.austin.netty.IO.BIO.BioChannelHandler;

import java.io.IOException;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * @ClassName: BioClient 客户端
 * @description:
 * @author: zqz
 * @date: 2024/9/1 22:39
 */

public class BioClient {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("127.0.0.1", 8080);
        System.out.println("netty-bio-demo client start done");
        BioClientHandler bioChannelHandler = new BioClientHandler(socket, Charset.forName("utf-8"));
        bioChannelHandler.start();
    }
}
