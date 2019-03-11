package com.cwn.springboot;


import com.cwn.springboot.controller.IndexControllerTest;
import com.cwn.springboot.controller.LoginControllerTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/*
*打包测试
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({IndexControllerTest.class, LoginControllerTest.class})
public class TestSuits {

}
