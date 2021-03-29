package com.github.yafeiwang1240.minitomcat.base;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.QueryStringDecoder;

import java.util.List;
import java.util.Map;

/**
 * 请求体
 * @author wangyafei
 * @date 2021-03-25
 */
public class NettyRequest extends Request {
    private ChannelHandlerContext ctx;
    private HttpRequest request;

    public NettyRequest(ChannelHandlerContext ctx, HttpRequest request) {
        this.ctx = ctx;
        this.request = request;
    }

    public String getMethod() {
        return request.method().name();
    }

    public String getUrl() {
        return request.uri();
    }

    public Map<String, List<String>> getParameters() {
        QueryStringDecoder decoder = new QueryStringDecoder(request.uri());
        return decoder.parameters();
    }
}
