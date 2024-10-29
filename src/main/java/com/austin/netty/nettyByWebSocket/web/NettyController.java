package com.austin.netty.nettyByWebSocket.web;

import com.austin.netty.nettyByWebSocket.server.NettyServer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * @ClassName: NettyController
 * @description:
 * @author: zqz
 * @date: 2024/10/29 20:48
 */
@Controller
public class NettyController {
    @Resource
    private NettyServer nettyServer;

    // todo 这里就不写前端页面了，知道这是一个聊天的功能界面即可
    @RequestMapping("/index")
    public String index(Model model){
        model.addAttribute("name", "austin");
        return "index";
    }
}
