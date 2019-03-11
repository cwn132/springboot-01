package com.cwn.springboot.controller;

import com.cwn.springboot.bean.UserVO;
import com.cwn.springboot.service.IndexService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class IndexControllerTest {


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

//    @Ignore("not ready yet") 忽略测试方法
    @Test
    public void index() throws Exception {

        String url="/index?pp=cwn&l=zh_CH";
        MvcResult mvcResult = mvc
                .perform(// 1
                        MockMvcRequestBuilders.get(url) // 2
                        //.param("name","getList")        // 3
                )
                .andReturn();// 4


        int status = mvcResult.getResponse().getStatus(); // 5
        String responseString = mvcResult.getResponse().getContentAsString(); // 6

//        System.out.println(status);
        //System.out.println(responseString);
        Assert.assertEquals("请求错误", 200, status); // 7
//        Assert.assertEquals("返回结果不一致", "", responseString); // 8


    }
}