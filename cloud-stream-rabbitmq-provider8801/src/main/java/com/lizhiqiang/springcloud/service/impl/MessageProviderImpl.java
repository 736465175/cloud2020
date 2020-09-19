package com.lizhiqiang.springcloud.service.impl;

import cn.hutool.core.lang.UUID;
import com.lizhiqiang.springcloud.service.IMessageProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;

import javax.annotation.Resource;

//@Service 这里的service是和Stream-RabbitMQ关联的，是配置(@Configuration)不是服务了
@EnableBinding(Source.class) //定义消息的推送管道，cloud stream 架构中提到，这里就是这样编码
@Slf4j
public class MessageProviderImpl implements IMessageProvider {

    @Resource
    private MessageChannel output; //消息发送通道

    @Override
    public boolean send() {
        String serial = UUID.randomUUID().toString();
        boolean sendResult = output.send(MessageBuilder.withPayload(serial).build()); //springcloud stream官网告诉我们这样写
        log.info("**************serial:" + serial);
        return sendResult;
    }
}
