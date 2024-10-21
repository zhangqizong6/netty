package com.austin.netty.nettyByProtobuf.util;

import com.austin.netty.nettyByProtobuf.domain.MsgBody;

/**
 * @ClassName: MsgUtil
 * @description: 构建protobuf消息体
 * @author: zqz
 * @date: 2024/10/21 23:57
 */

public class MsgUtil {

    /**
     * 构建protobuf消息体
     */
    public static MsgBody buildMsg(String channelId, String msgInfo) {
        MsgBody.Builder msg = MsgBody.newBuilder();
        msg.setChannelId(channelId);
        msg.setMsgInfo(msgInfo);
        return msg.build();
    }
}
