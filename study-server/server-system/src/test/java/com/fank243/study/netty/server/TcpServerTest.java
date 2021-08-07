package com.fank243.study.netty.server;

import org.junit.jupiter.api.Test;

public class TcpServerTest {

    @Test
    public void testStart(){
        new TcpServer().start("0.0.0.0",1988);
    }
}