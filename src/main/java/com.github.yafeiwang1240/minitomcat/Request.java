package com.github.yafeiwang1240.minitomcat;

import java.io.InputStream;

/**
 * 请求体
 * @author wangyafei
 * @date 2021-03-25
 */
public class Request {

    private String method;
    private String url;

    public Request(InputStream in) {
        try {
            String context = "";
            byte[] buf = new byte[1024];
            int len = 0;
            if ((len = in.read(buf)) > 0) {
                context = new String(buf, 0, len);
            }
            String line = context.split("\\n")[0];
            String[] arr = line.split("\\s");
            this.method = arr[0];
            this.url = arr[1].split("\\?")[0];
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getMethod() {
        return method;
    }

    public String getUrl() {
        return url;
    }
}
