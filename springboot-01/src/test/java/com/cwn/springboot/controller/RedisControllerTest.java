package com.cwn.springboot.controller;

import com.cwn.springboot.Springboot01Application;
import com.cwn.springboot.bean.UserVO;
import com.cwn.springboot.config.utils.RedisConstants;
import com.cwn.springboot.config.utils.RedisUtil;
import com.cwn.springboot.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Springboot01Application.class)
public class RedisControllerTest {


    @Autowired
    RedisUtil redisUtil;

    @Autowired
    AccountService accountService;

    @Test
    public void getRedis() {

        List<UserVO> userList=accountService.selsetUserList();
        for(UserVO user:userList){
//            redisUtil.lpush(RedisConstants.datebase1,"userList",user.getUserid()+"");
            redisUtil.hset(RedisConstants.datebase1,"user:"+user.getUserid(),"userId",user.getUserid()+"");
            redisUtil.hset(RedisConstants.datebase1,"user:"+user.getUserid(),"userName",user.getUsername()+"");
            redisUtil.hset(RedisConstants.datebase1,"user:"+user.getUserid(),"usereEail",user.getEmail()+"");

        }

        System.out.println("ddd555");

    }
}