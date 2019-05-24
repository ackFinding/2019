package tinytomcat.simple.tomcat.connector;

import tinytomcat.simple.tomcat.mapper.Mapper;
import tinytomcat.simple.tomcat.servlet.Servlet;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * parse http request
 */
public class HttpProcessor {

    private Mapper mapper;

    public void init() {
        mapper = new Mapper().init();
    }


    public void process(Socket socket) {
        try (InputStream input = socket.getInputStream();
             OutputStream output = socket.getOutputStream()) {
            Request request = new Request(input);
            Response response = new Response(output);
            if (request.getUrl() == null) {
                //todo 日志
                return;
            }
            if (mapper.getMapper(request.getUrl()) == null) {
                response.send404NotFound();
                //todo 日志
                return;
            }
            Servlet servlet = (Servlet) mapper.getMapper(request.getUrl()).getClazz().newInstance();
            servlet.service(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
