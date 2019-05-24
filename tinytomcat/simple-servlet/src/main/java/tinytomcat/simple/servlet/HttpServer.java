package tinytomcat.simple.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class HttpServer {

    private ExecutorService pool = new ThreadPoolExecutor(2, 4, 3,
            TimeUnit.SECONDS, new LinkedBlockingQueue<>(10));

    public void start() {
        System.out.println("===服务器开始启动===");
        try (ServerSocket serverSocket = new ServerSocket(Constants.PORT)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                pool.submit(() -> serverProcess(clientSocket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            pool.shutdown();
        }
    }

    private void serverProcess(Socket clientSocket) {
        try (InputStream input = clientSocket.getInputStream();
             OutputStream output = clientSocket.getOutputStream()) {
            Request request = new Request(input);
            Response response = new Response(output, request);

            if (request.getUri().startsWith("/servlet/")) {
                //如果地址以/servlet开头就作为Servlet处理
                ServletProcessor processor = new ServletProcessor();
                processor.process(request, response);
            } else {
                //否则作为静态资源使用
                StaticResourceProcessor processor = new StaticResourceProcessor();
                processor.process(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new HttpServer().start();
    }
}
