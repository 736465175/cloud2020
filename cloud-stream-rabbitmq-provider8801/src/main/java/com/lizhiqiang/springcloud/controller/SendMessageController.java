package com.lizhiqiang.springcloud.controller;

import com.lizhiqiang.springcloud.service.IMessageProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class SendMessageController {

    @Resource
    private IMessageProvider messageProvider;

    @GetMapping("/sendMessage")
    public boolean sendMessage(){
        return messageProvider.send();
    }

}
