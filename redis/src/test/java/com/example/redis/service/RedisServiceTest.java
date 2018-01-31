package com.example.redis.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisServiceTest {

    @Autowired
    private RedisService redisService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void lPush() {
        String type = "mitsubishi";
        redisService.lPush("ROUTING", "MachineType:i5");
        redisService.lPush("ROUTING", "MachineType:siemens");
        redisService.lPush("ROUTING", "MachineType:fanuc");
        redisService.lPush("ROUTING", type);
    }

    @Test
    public void lPop() {
    }

    @Test
    public void lRange() {
    }
}