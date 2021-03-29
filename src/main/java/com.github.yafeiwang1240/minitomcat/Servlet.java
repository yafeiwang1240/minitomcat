package com.github.yafeiwang1240.minitomcat;

/**
 * servlet
 * @author wangyafei
 * @date 2021-03-25
 */
public abstract class Servlet {

    public void service(Request request, Response response) throws Exception {
        if ("GET".equalsIgnoreCase(request.getMethod())) {
            doGet(request, response);
        } else {
            doPost(request, response);
        }
    }

    protected abstract void doPost(Request request, Response response) throws Exception;

    protected abstract void doGet(Request request, Response response) throws Exception;

}
