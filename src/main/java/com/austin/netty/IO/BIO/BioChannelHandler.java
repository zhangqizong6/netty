package com.austin.netty.IO.BIO;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * @ClassName: BioChannelHandler
 * @description:
 * @author: zqz
 * @date: 2024/9/1 22:44
 */

public class BioChannelHandler {

    private Socket socket;
    private Charset charset;

    public BioChannelHandler(Socket socket, Charset charset) {
        this.socket = socket;
        this.charset = charset;
    }

    public void writeAndFlush(Object msg) {
        OutputStream out = null;
        try {
            out = socket.getOutputStream();
            out.write(msg.toString().getBytes(charset));
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Socket socket() {
       return socket;
    }
}
