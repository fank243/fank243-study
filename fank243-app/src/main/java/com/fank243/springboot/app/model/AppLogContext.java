package com.fank243.springboot.app.model;

import lombok.Getter;

@Getter
public class AppLogContext implements AutoCloseable {
    @Override
    public void close() throws Exception {

    }

    private String sessionId;

    private String requestId;

    public AppLogContext(String sessionId, String requestId) {
        this.sessionId = sessionId;
        this.requestId = requestId;
    }
}
