package com.lizhiqiang.springcloud.entities;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.IdUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class IdGeneratorSnowflake {

    private long workerId = 0;

    private long datacenterId = 1;

    private Snowflake snowflake = IdUtil.createSnowflake(workerId, datacenterId);

    public void init(){
        String ip = NetUtil.getLocalhostStr();
        System.out.println("ip =============" + ip);
        workerId = NetUtil.ipv4ToLong(ip);
        System.out.println("workerId =============" + workerId);
    }

    public synchronized long snowflakeId(){
        return snowflake.nextId();
    }

    public static void main(String[] args) {
        IdGeneratorSnowflake idGeneratorSnowflake = new IdGeneratorSnowflake();
        idGeneratorSnowflake.init();

        ExecutorService threadPool = Executors.newScheduledThreadPool(5); //5个受理窗口

        for(int i=0; i<= 20; i++){ //20个人要摇号拿排队码(雪花算法ID)
            threadPool.submit(new Runnable() {
                @Override
                public void run() {
                    long id = idGeneratorSnowflake.snowflakeId();
                    System.out.println(id + "=======" + String.valueOf(id).length());
                }
            });

        }

        threadPool.shutdown();

    }


}
