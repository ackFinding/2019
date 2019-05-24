package tinytomcat.test;

import tinytomcat.simple.tomcat.connector.Request;
import tinytomcat.simple.tomcat.connector.Response;
import tinytomcat.simple.tomcat.servlet.MyServlet;
import tinytomcat.simple.tomcat.servlet.Servlet;

import java.io.IOException;

@MyServlet("/fun")
public class FunnyServlet implements Servlet {

    @Override
    public void service(Request req, Response resp) throws IOException {
        resp.sendStaticResource("fun.html");
    }
}
