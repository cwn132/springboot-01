package com.cwn.springboot;

import com.cwn.springboot.bean.UserVO;
import com.cwn.springboot.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Springboot01Application.class)
public class Springboot01ApplicationTests {

// Logger logger=   LoggerFactory.getLogger(getClass());

    @Autowired
    DataSource dataSource;

    @Autowired
    private AccountService accountService;

    @Test
    public void contextLoads() throws SQLException {

        System.out.println("测试事务处理");
        //测试事务处理
        System.out.println(dataSource.getClass());
        Connection con=dataSource.getConnection();
        System.out.println(con);
        con.close();

    }

    @Test
    public void testSelecUser(){
      UserVO users= accountService.getUserInfoById(1);

      System.out.println(users.getUserid());
      System.out.println(users.getAge());
      System.out.println(users.getSex());
    }

}

