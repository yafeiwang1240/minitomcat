package com.github.yafeiwang1240.minitomcat;

import java.io.IOException;
import java.io.OutputStream;

/**
 * 响应体
 * @author wangyafei
 * @date 2021-03-25
 */
public class Response {

    private OutputStream out;

    public Response(OutputStream out) {
        this.out = out;
    }

    public void write(String context) throws IOException {
        StringBuilder builder = new StringBuilder();
        builder.append("HTTP/1.1 200 OK\n")
                .append("Content-type: text/html;\n")
                .append("\r\n")
                .append(context);
        out.write(builder.toString().getBytes());
    }
}
