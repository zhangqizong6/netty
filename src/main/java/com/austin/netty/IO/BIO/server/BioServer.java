package com.austin.netty.IO.BIO.server;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * @ClassName: BioServer
 * @description:
 * @author: zqz
 * @date: 2024/9/1 23:06
 */

public class BioServer extends Thread {

    private ServerSocket serverSocket = null;

    public static void main(String[] args) {
        BioServer bioServer = new BioServer();
        bioServer.start();
    }

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(8080));
            System.out.println("netty-demo-bio server start done.");
            while (true) {
                Socket socket = serverSocket.accept();
                BioServerHandler handler = new BioServerHandler(socket, Charset.forName("GBK"));
                handler.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
