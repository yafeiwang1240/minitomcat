package com.github.yafeiwang1240.minitomcat;

import com.github.yafeiwang1240.minitomcat.base.Request;
import com.github.yafeiwang1240.minitomcat.base.Response;
import com.github.yafeiwang1240.minitomcat.base.Servlet;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Tomcat {

    protected int port = 8080;

    private ServerSocket serverSocket;

    protected Properties web = new Properties();

    protected Map<String, Servlet> servletMapping = new HashMap<>();

    protected void init() {
        try {
            InputStream resourceAsStream = this.getClass().getClassLoader()
                    .getResourceAsStream("web.properties");
            web.load(resourceAsStream);
            for (Object k : web.keySet()) {
                String key = k.toString();
                if (key.endsWith(".url")) {
                    String servlet = key.replaceAll("\\.url$", "");
                    String url = web.getProperty(key);
                    String classname = web.getProperty(servlet + ".classname");
                    Servlet instance = (Servlet) Class.forName(classname).newInstance();
                    servletMapping.put(url, instance);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start() {
        init();
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("tomcat已启动，监听的端口是：" + port);
            while (true) {
                Socket client = serverSocket.accept();
                process(client);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void process(Socket client) throws Exception {
        InputStream in = client.getInputStream();
        OutputStream out = client.getOutputStream();
        Request request = new Request(in);
        Response response = new Response(out);
        String url = request.getUrl();
        if (servletMapping.containsKey(url)) {
            servletMapping.get(url).service(request, response);
        } else {
            response.write("404 Not Found");
        }
        out.flush();
        out.close();

        in.close();
        client.close();
    }

    public static void main(String[] args) {
        new Tomcat().start();
    }
}
