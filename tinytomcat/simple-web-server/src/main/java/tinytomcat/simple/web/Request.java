package tinytomcat.simple.web;

import java.io.IOException;
import java.io.InputStream;

public class Request {

	private String uri;
	private InputStream input;
	private final int BUFFER_SIZE = 1024;

	public Request(InputStream input) {
		this.input = input;
	}

	public void parseUri(String requestStr) {
		int index1 = requestStr.indexOf(" ");
		int index2 = requestStr.indexOf(" ", index1 + 1);
		uri = requestStr.substring(index1 + 1, index2);
	}

	public String getUri() {
		if (uri == null) {
			parse();
		}
		return uri;
	}

	private void parse() {
		StringBuilder request = new StringBuilder(BUFFER_SIZE * 8);
		int i;
		byte[] buffer = new byte[BUFFER_SIZE * 8];
		try {
			i = input.read(buffer);
		} catch (IOException e) {
			e.printStackTrace();
			i = -1;
		}
		for (int j = 0; j < i; j++) {
			request.append((char) buffer[j]);
		}
		System.out.println(request.toString());
		parseUri(request.toString());
	}
}
