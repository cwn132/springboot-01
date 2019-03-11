package com.cwn.springboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Springboot01Application.class)
public class Springboot01ApplicationTests {

// Logger logger=   LoggerFactory.getLogger(getClass());

    @Autowired
    DataSource dataSource;

    @Test
    public void contextLoads() throws SQLException {

        System.out.println("测试事务处理");
        //测试事务处理
        System.out.println(dataSource.getClass());
        Connection con=dataSource.getConnection();
        System.out.println(con);
        con.close();

    }

}

