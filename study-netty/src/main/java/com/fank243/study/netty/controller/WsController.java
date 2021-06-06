package com.fank243.study.netty.controller;

import com.fank243.study.netty.model.NettyModel;
import com.fank243.study.netty.server.sender.WsSender;
import org.springframework.web.bind.annotation.*;

/**
 * WebSocket
 * 
 * @author FanWeiJie
 * @date 2021-05-04 15:18:42
 */
@RestController
@RequestMapping("/ws")
public class WsController {

    @PostMapping("/sendMsg")
    public String sendMsg(@RequestBody NettyModel nettyModel) {
        WsSender.sendMessage(nettyModel);
        return "OK";
    }
}
