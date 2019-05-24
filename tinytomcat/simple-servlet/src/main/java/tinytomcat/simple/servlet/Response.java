package tinytomcat.simple.servlet;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import java.io.*;
import java.util.Locale;

public class Response implements ServletResponse {

    private OutputStream output;
    private Request request;

    public Response(OutputStream output, Request request) {
        this.output = output;
        this.request = request;
    }

    public void sendStaticResource() {
        File file = new File(Constants.ROOT, request.getUri());
        if (file.exists()) {
            StringBuilder builder = new StringBuilder();
            builder.append("HTTP/1.1 200 OK\r\n");
            sendFile(builder, file);
        } else {
            send404NotFound();
        }
    }

    protected void send404NotFound() {
        // 响应头
        StringBuilder builder = new StringBuilder();
        builder.append("HTTP/1.1 404 Not Found\r\n");
        File f = new File("error.html");
        sendFile(builder, f);
    }

    // ps:如果不存在404文件，则出现死循环了
    private void sendFile(StringBuilder builder, File file) {
        byte[] buffer = new byte[Constants.BUFFER_SIZE];

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

    @Override
    public PrintWriter getWriter() throws IOException {
        return new PrintWriter(output, true);
    }

    @Override
    public void flushBuffer() throws IOException {
        // TODO Auto-generated method stub

    }

    @Override
    public int getBufferSize() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public String getCharacterEncoding() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getContentType() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Locale getLocale() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public boolean isCommitted() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void reset() {
        // TODO Auto-generated method stub

    }

    @Override
    public void resetBuffer() {
        // TODO Auto-generated method stub

    }

    @Override
    public void setBufferSize(int arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setCharacterEncoding(String arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setContentLength(int arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setContentLengthLong(long arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setContentType(String arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setLocale(Locale arg0) {
        // TODO Auto-generated method stub

    }
}
