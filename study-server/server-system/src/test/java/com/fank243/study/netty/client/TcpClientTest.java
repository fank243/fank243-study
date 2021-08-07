package com.fank243.study.netty.client;

import org.junit.jupiter.api.Test;

class TcpClientTest {

    @Test
    public void testStart(){
        new TcpClient().start("127.0.0.1",1988);
    }
}