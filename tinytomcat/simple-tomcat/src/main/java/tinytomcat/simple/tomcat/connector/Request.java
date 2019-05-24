package tinytomcat.simple.tomcat.connector;

import java.io.IOException;
import java.io.InputStream;

public class Request {

    private InputStream input;

    private String url;

    public Request(InputStream input) {
        this.input = input;
        parse();
    }

    private void parse() {
        StringBuilder request = new StringBuilder(1024 * 8);
        int i;
        byte[] buffer = new byte[1024 * 8];
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
        parseUrl(request.toString());
    }

    public void parseUrl(String requestStr) {
        if (requestStr == null || requestStr.equals("")) {
            return;
        }
        int index1 = requestStr.indexOf(" ");
        int index2 = requestStr.indexOf(" ", index1 + 1);
        url = requestStr.substring(index1 + 1, index2);
    }

    public String getUrl() {
        return url;
    }
}
