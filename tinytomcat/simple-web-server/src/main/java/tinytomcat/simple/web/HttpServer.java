package tinytomcat.simple.web;

import java.io.File;
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

	protected static final File ROOT = new File("G:\\ServerSite");
	private final int PORT = 8080;
	private ExecutorService pool = new ThreadPoolExecutor(2, 4, 3,
			TimeUnit.SECONDS, new LinkedBlockingQueue<>(10));

	public void start() {
		System.out.println("===服务器开始启动===");
		try (ServerSocket serverSocket = new ServerSocket(PORT)) {
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
			response.sendStaticResource();
		} catch (IOException e) {
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
