package com.github.yafeiwang1240.minitomcat.base;

public class SecondServlet extends Servlet {
    @Override
    protected void doPost(Request request, Response response) throws Exception {
        response.write("this is second servlet");
    }

    @Override
    protected void doGet(Request request, Response response) throws Exception {
        doPost(request, response);
    }
}
