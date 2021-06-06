package com.fank243.study.netty.server;

import org.junit.Test;

public class WsServerTest {

    @Test
    public void start() {
        new WsServer().start("0.0.0.0", 1900);
    }
}