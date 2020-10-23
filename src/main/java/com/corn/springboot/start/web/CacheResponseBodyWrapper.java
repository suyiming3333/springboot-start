package com.corn.springboot.start.web;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.*;
import java.nio.charset.Charset;

/**
 * @author suyiming3333@gmail.com
 * @version V1.0
 * @Title: CacheResponseBodyWrapper
 * @Package com.corn.springboot.start.web
 * @Description: TODO
 * @date 2020/10/23 11:08
 */
public class CacheResponseBodyWrapper extends HttpServletResponseWrapper {

    private ByteArrayOutputStream buffer = null;
    private ServletOutputStream out = null;
    private PrintWriter writer = null;
    /**
     * Constructs a response adaptor wrapping the given response.
     *
     * @param response The response to be wrapped
     * @throws IllegalArgumentException if the response is null
     */
    public CacheResponseBodyWrapper(HttpServletResponse response) throws IOException {
        super(response);
        //缓存response的outputstream
        buffer = new ByteArrayOutputStream();
        out = new WapperedOutputStream(buffer);
        writer = new PrintWriter(new OutputStreamWriter(buffer, Charset.forName("UTF-8")));
    }

    /**重载父类获取outputstream的方法*/
    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return out;
    }

    /**重载父类获取writer的方法*/
    @Override
    public PrintWriter getWriter() throws UnsupportedEncodingException {
        return writer;
    }

    /**重载父类获取flushBuffer的方法*/
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

    /**将out、writer中的数据强制输出到WapperedResponse的buffer里面，否则取不到数据*/
    public byte[] getResponseData() throws IOException {
        flushBuffer();
        return buffer.toByteArray();
    }


    /**内部类，对ServletOutputStream进行包装*/
    private class WapperedOutputStream extends ServletOutputStream {
        private ByteArrayOutputStream bos;

        public WapperedOutputStream(ByteArrayOutputStream stream) throws IOException {
            bos = stream;
        }

        @Override
        public void write(int b) throws IOException {
            bos.write(b);
        }
        @Override
        public void write(byte[] b) throws IOException {
            bos.write(b, 0, b.length);
        }

        @Override
        public boolean isReady() {
            return true;
        }

        @Override
        public void setWriteListener(WriteListener listener) {

        }
    }
}
