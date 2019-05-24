package tinytomcat.simple.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

public class Response {

	private OutputStream output;
	private final int BUFFER_SIZE = 1024;
	private Request request;

	public Response(OutputStream output, Request request) {
		this.output = output;
		this.request = request;
	}

	public void sendStaticResource() {
		File file = new File(HttpServer.ROOT, request.getUri());
		if (file.exists()) {
			StringBuilder builder = new StringBuilder();
			builder.append("HTTP/1.1 200 OK\r\n");
			sendFile(builder, file);
		} else {
			send404NotFound();
		}
	}

	private void send404NotFound() {
		// 响应头
		StringBuilder builder = new StringBuilder();
		builder.append("HTTP/1.1 404 Not Found\r\n");
		File f = new File("error.html");
		sendFile(builder, f);
	}

	// ps:如果不存在404文件，则出现死循环了
	private void sendFile(StringBuilder builder, File file) {
		byte[] buffer = new byte[BUFFER_SIZE];

		try (FileInputStream fIn = new FileInputStream(file)) {
			StringBuilder body = new StringBuilder();
			int i;
			while ((i = fIn.read(buffer)) != -1) {
				body.append(new String(buffer, 0, i));
			}
			builder.append("Content-Type: text/html\r\n");
			builder.append("Content-Length: " + file.length() + "\r\n");
			builder.append("\r\n");
			output.write(builder.toString().getBytes());
			output.write(body.toString().getBytes());
		} catch (FileNotFoundException e) {
			// todo:增加对丢失错误文件的处理
			send404NotFound();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
