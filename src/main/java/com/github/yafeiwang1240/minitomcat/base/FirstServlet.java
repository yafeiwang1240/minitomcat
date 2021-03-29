package com.github.yafeiwang1240.minitomcat.base;

public class FirstServlet extends Servlet {
    @Override
    protected void doPost(Request request, Response response) throws Exception {
        response.write("this is first servlet");
    }

    @Override
    protected void doGet(Request request, Response response) throws Exception {
        doPost(request, response);
    }
}
