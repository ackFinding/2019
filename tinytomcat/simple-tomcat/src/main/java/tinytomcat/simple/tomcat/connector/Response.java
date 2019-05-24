package tinytomcat.simple.tomcat.connector;

import java.io.*;

public class Response {

    private OutputStream output;

    public Response(OutputStream output) {
        this.output = output;
    }

    public void write(String content) throws IOException {
        StringBuilder resp = new StringBuilder();
        resp.append("HTTP/1.1 200 OK\r\n")
                .append("Content-Type: text/html\r\n")
                .append("Content-Length: ")
                .append(content.length())
                .append("\r\n")
                .append("\r\n")
                .append(content);
        output.write(resp.toString().getBytes());
    }

    public void send404NotFound() {
        // 响应头
        File f = new File("error.html");
        try {
            System.out.println(f.getCanonicalPath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        sendFile(f, ResponseState.FAIL);
    }

    private void sendFile(File file, ResponseState state) {
        byte[] buffer = new byte[1024];
        StringBuilder builder = new StringBuilder();
        try (FileInputStream fIn = new FileInputStream(file)) {
            StringBuilder body = new StringBuilder();
            int i;
            while ((i = fIn.read(buffer)) != -1) {
                body.append(new String(buffer, 0, i));
            }

            builder.append(startLine(state))
                    .append("Content-Type: text/html\r\n")
                    .append("Content-Length: ")
                    .append(file.length())
                    .append("\r\n")
                    .append("\r\n");
            output.write(builder.toString().getBytes());
            output.write(body.toString().getBytes());
        } catch (FileNotFoundException e) {
            // todo:增加对丢失错误文件的处理
            send404NotFound();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String startLine(ResponseState state) {
        String res;
        if (state == ResponseState.SUCCESS) {
            res = "HTTP/1.1 200 OK\r\n";
        } else {
            res = "HTTP/1.1 404 Not Found\r\n";
        }
        return res;
    }

    public void sendStaticResource(String path) {
        File file = new File(ConfigInfo.WEB_ROOT, path);
        if (file.exists()) {
            sendFile(file, ResponseState.SUCCESS);
        } else {
            send404NotFound();
        }
    }

}
