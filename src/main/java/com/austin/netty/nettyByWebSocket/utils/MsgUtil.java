package com.austin.netty.nettyByWebSocket.utils;

import com.alibaba.fastjson.JSON;
import com.austin.netty.nettyByWebSocket.domain.ServerMsgProtocol;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

/**
 * @ClassName: MsgUtil
 * @description:
 * @author: zqz
 * @date: 2024/10/29 20:43
 */

public class MsgUtil {

    public static TextWebSocketFrame buildMsgAll(String channelId, String msgInfo) {
        //模拟头像
        int i = Math.abs(channelId.hashCode()) % 10;

        ServerMsgProtocol msg = new ServerMsgProtocol();
        msg.setType(2); //链接信息;1自发信息、2群发消息
        msg.setChannelId(channelId);
        msg.setUserHeadImg("head" + i + ".jpg");
        msg.setMsgInfo(msgInfo);

        return new TextWebSocketFrame(JSON.toJSONString(msg));
    }

    public static TextWebSocketFrame buildMsgOwner(String channelId) {
        ServerMsgProtocol msg = new ServerMsgProtocol();
        msg.setType(1); //链接信息;1链接信息、2消息信息
        msg.setChannelId(channelId);
        return new TextWebSocketFrame(JSON.toJSONString(msg));
    }
}
