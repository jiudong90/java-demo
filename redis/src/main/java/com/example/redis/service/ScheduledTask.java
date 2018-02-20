package com.example.redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ScheduledTask {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    private Integer count0 = 1;
    private Integer count1 = 1;
    private Integer count2 = 1;

    @Autowired
    private RedisService redisService;


//    @Scheduled(fixedRate = 5000)
//    public void reportCurrentTime() throws InterruptedException {
//        System.out.println(String.format("---第%s次执行，当前时间为：%s", count0++, dateFormat.format(new Date())));
//    }
//
//    @Scheduled(fixedDelay = 5000)
//    public void reportCurrentTimeAfterSleep() throws InterruptedException {
//        System.out.println(String.format("===第%s次执行，当前时间为：%s", count1++, dateFormat.format(new Date())));
//    }
//
//    @Scheduled(cron = "0 0 1 * * *")
//    public void reportCurrentTimeCron() throws InterruptedException {
//        System.out.println(String.format("+++第%s次执行，当前时间为：%s", count2++, dateFormat.format(new Date())));
//    }

    @Scheduled(fixedRate = 200)
    public void aProducer() throws InterruptedException {
        redisService.pushWithTrim("last_60_Messages", "A-"+dateFormat.format(new Date()), 60);
    }

    @Scheduled(fixedRate = 200)
    public void bProducer() throws InterruptedException {
        redisService.pushWithTrim("last_60_Messages", "B-"+dateFormat.format(new Date()), 60);
    }

    @Scheduled(fixedRate = 1000)
    public void consumer() {
        String results;
        results = redisService.popWithElementes("last_60_Messages", 10);
        System.out.println(results);
    }
}
