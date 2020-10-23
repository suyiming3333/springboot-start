package com.corn.springboot.start.web;

import javax.servlet.ServletOutputStream;
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
public class CacheResponseBodyResponseWrapper extends HttpServletResponseWrapper {

    private ByteArrayOutputStream out = new ByteArrayOutputStream();
    private PrintWriter writer = new PrintWriter(out);
    /**
     * Constructs a response adaptor wrapping the given response.
     *
     * @param response The response to be wrapped
     * @throws IllegalArgumentException if the response is null
     */
    public CacheResponseBodyResponseWrapper(HttpServletResponse response) throws IOException {
        super(response);
    }

    /**重载父类获取outputstream的方法*/
    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return new ServletOutputStream() {
            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setWriteListener(WriteListener listener) {

            }

            @Override
            public void write(int b) throws IOException {
                out.write(b);
            }
        };
    }
    /**重载父类获取writer的方法*/
    @Override
    public PrintWriter getWriter() throws UnsupportedEncodingException {
        return writer;
    }

    public void flush(){
        try {
            writer.flush();
            writer.close();
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ByteArrayOutputStream getByteArrayOutputStream(){
        return out;
    }


    public String getTextContent() {
        flush();
        return out.toString();
    }

}
