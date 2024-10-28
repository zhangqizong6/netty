package com.austin.netty.nettyByRandomAccessFile.utils;

import com.austin.netty.nettyByRandomAccessFile.domain.FileBurstInstruct;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName: CacheUtil
 * @description:
 * @author: zqz
 * @date: 2024/10/28 23:37
 */

public class CacheUtil {

    public static Map<String, FileBurstInstruct> burstDataMap = new ConcurrentHashMap<>();
}
