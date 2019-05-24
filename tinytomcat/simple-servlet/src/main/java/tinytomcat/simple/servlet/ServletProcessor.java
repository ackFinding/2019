package tinytomcat.simple.servlet;

import javax.servlet.Servlet;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLClassLoader;

/***
 * 加载servlet类
 */
public class ServletProcessor implements Processor {

    @SuppressWarnings("resource")
    public void process(Request request, Response response) {

        String uri = request.getUri();
        String servletName = uri.substring(uri.lastIndexOf("/") + 1);
        URLClassLoader loader = null;
        try {
            File classPath = new File(Constants.ROOT);
            // 类加载器加载路径
            String repository = (new URL("file", null,
                    classPath.getCanonicalPath() + File.separator)).toString();
            URL url = new URL(null, repository);
            loader = new URLClassLoader(new URL[]{url});
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        Class<?> clazz = null;
        try {
            // 加载Servlet类
            clazz = loader.loadClass(servletName);
        } catch (ClassNotFoundException e) {
            response.send404NotFound();
            throw new IllegalStateException(e);
        }
        try {
            // 强转为Servlet类
            Servlet servlet = (Servlet) clazz.newInstance();
            // 响应头部
            PrintWriter writer = response.getWriter();
            writer.print("HTTP/1.1 200 OK\r\n");
            writer.print("Content-Type: text/html\r\n");
            //如果autoFlush=true,println会flush
            writer.println("\r\n");
            // 调用Servlet类中service方法。
            servlet.service(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
