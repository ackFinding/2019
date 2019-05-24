package tinytomcat.simple.tomcat;

import tinytomcat.simple.tomcat.connector.Connector;

public class HttpServer {


    public void start() {
        new Connector().init();
        System.out.println("===服务器开始启动===");
    }

    public static void main(String[] args) {
        new HttpServer().start();
    }
}
