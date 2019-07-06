package com.lock;

import redis.clients.jedis.Jedis;

import java.util.TimerTask;

public class TtlEnduranceTask extends TimerTask {

    private Jedis jedis;
    private String key;
    private int ttl;//续航时间
    private String name;


    public TtlEnduranceTask(Jedis jedis, String key, int ttl, String name) {
        this.jedis = jedis;
        this.key = key;
        this.ttl = ttl;
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("[" + name + "]续航前ttl:" + jedis.ttl(key) + "-->" + System.currentTimeMillis());
        jedis.expire(key, ttl);
        System.out.println("[" + name + "]续航后ttl:" + jedis.ttl(key) + "-->" + System.currentTimeMillis());
    }
}
