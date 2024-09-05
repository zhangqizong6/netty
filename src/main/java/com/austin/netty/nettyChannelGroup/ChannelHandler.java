package com.austin.netty.nettyChannelGroup;

import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @ClassName: ChannelHandler
 * @description:
 * @author: zqz
 * @date: 2024/9/5 21:02
 */

public class ChannelHandler {

    //用于存放用户的channel信息 也可以建立map结构模拟不同的消息群
    public static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
}
