package com.fank243.springboot.admin;

import com.fank243.springboot.admin.AdminApplication;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.FileNotFoundException;
import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AdminApplication.class)
public class BaseServiceTest {

    @Before
    public void init() throws IOException {
        System.out.println("**************************开始启动单元测试*********************************");
    }

    @After
    public void after() {
        System.out.println("**************************结束启动单元测试*********************************");
    }
}