package tinytomcat.test;

import tinytomcat.simple.tomcat.connector.Request;
import tinytomcat.simple.tomcat.connector.Response;
import tinytomcat.simple.tomcat.servlet.MyServlet;
import tinytomcat.simple.tomcat.servlet.Servlet;
import tinytomcat.simple.tomcat.util.BodyBuilder;

import java.io.IOException;

@MyServlet("/hello")
public class HelloWorldServlet implements Servlet {

    @Override
    public void service(Request req, Response resp) throws IOException {
        resp.write(BodyBuilder.build("hello world"));
    }
}
