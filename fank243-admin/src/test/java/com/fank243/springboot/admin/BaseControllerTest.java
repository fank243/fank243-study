package com.fank243.springboot.admin;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AdminApplication.class)
public class BaseControllerTest {

    @Before
    public void init() {
        System.out.println("**************************开始启动单元测试*********************************");
    }

    @After
    public void after() {
        System.out.println("**************************结束启动单元测试*********************************");
    }
}