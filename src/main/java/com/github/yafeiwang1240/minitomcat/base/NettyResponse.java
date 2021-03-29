package com.github.yafeiwang1240.minitomcat.base;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;

import java.io.IOException;

/**
 * 响应体
 * @author wangyafei
 * @date 2021-03-25
 */
public class NettyResponse extends Response {

    private ChannelHandlerContext ctx;
    private HttpRequest request;

    public NettyResponse(ChannelHandlerContext ctx, HttpRequest request) {
        this.ctx = ctx;
        this.request = request;
    }

    public void write(String context) throws IOException {
        try {
            if (context == null || context.length() == 0) {
                return;
            }
            FullHttpResponse response = new DefaultFullHttpResponse(
                    HttpVersion.HTTP_1_1,
                    HttpResponseStatus.OK,
                    Unpooled.wrappedBuffer(context.getBytes("UTF-8")));
            response.headers().set("Content-Type", "text/html");
            ctx.write(response);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ctx.flush();
            ctx.close();
        }
    }
}
