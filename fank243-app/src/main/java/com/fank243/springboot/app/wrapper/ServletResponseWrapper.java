package com.fank243.springboot.app.wrapper;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * 修改返回参数
 * 
 * @author FanWeiJie
 * @date 2019-11-27 14:07:40
 */
public class ServletResponseWrapper extends HttpServletResponseWrapper {

    private ByteArrayOutputStream buffer;
    private ServletOutputStream out;
    private PrintWriter writer;

    public ServletResponseWrapper(HttpServletResponse resp) throws IOException {
        super(resp);
        buffer = new ByteArrayOutputStream();
        out = new WapperedOutputStream(buffer);
        writer = new PrintWriter(new OutputStreamWriter(buffer, this.getCharacterEncoding()));
    }

    @Override
    public ServletOutputStream getOutputStream() {
        return out;
    }

    @Override
    public PrintWriter getWriter() {
        return writer;
    }

    @Override
    public void flushBuffer() throws IOException {
        if (out != null) {
            out.flush();
        }
        if (writer != null) {
            writer.flush();
        }
    }

    @Override
    public void reset() {
        buffer.reset();
    }

    public byte[] getResponseData() throws IOException {
        flushBuffer();
        return buffer.toByteArray();
    }

    private class WapperedOutputStream extends ServletOutputStream {
        private ByteArrayOutputStream bos = null;

        public WapperedOutputStream(ByteArrayOutputStream stream) {
            bos = stream;
        }

        @Override
        public void write(int b) {
            bos.write(b);
        }

        @Override
        public void write(byte[] b) {
            bos.write(b, 0, b.length);
        }

        @Override
        public void setWriteListener(WriteListener writeListener) {

        }

        @Override
        public boolean isReady() {
            return false;
        }
    }

}