package com.cwn.springboot.controller;

import com.cwn.springboot.service.IndexService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class LoginControllerTest {


    /**
     * web项目上下文
     */
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mvc;
    private MockHttpSession session;

    @Autowired
    private IndexService indexService;

    @Before
    public void setUp() throws Exception {
//获取mockmvc对象实例
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

    }

    @Test
    public void lgoin() throws  Exception{


        String url="/login";
        MvcResult mvcResult = mvc
                .perform(// 1
                        MockMvcRequestBuilders.post(url) // 2
                        .param("username","cwn")        // 3
                        .param("password","123")        // 3
                )
                .andReturn();// 4


        int status = mvcResult.getResponse().getStatus(); // 5
        String responseString = mvcResult.getResponse().getContentAsString(); // 6

        Assert.assertEquals("跳转到另外页面", 302, status); // 7
//        Assert.assertEquals("本页面请求", 200, status); // 7
//        Assert.assertEquals("返回结果不一致", "", responseString); // 8

    }
}