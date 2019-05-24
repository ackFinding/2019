package tinytomcat.simple.tomcat.servlet;

import tinytomcat.simple.tomcat.connector.Request;
import tinytomcat.simple.tomcat.connector.Response;

import java.io.IOException;

public interface Servlet {

    void service(Request req, Response resp) throws IOException;
}
