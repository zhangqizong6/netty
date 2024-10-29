package com.austin.netty.nettyByWebSocket.domain;

/**
 * @ClassName: ClientMsgProtocol
 * @description:
 * @author: zqz
 * @date: 2024/10/29 20:32
 */

public class ClientMsgProtocol {
    private int type;       //1请求个人信息，2发送聊天信息
    private String msgInfo; //消息

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMsgInfo() {
        return msgInfo;
    }

    public void setMsgInfo(String msgInfo) {
        this.msgInfo = msgInfo;
    }
}
