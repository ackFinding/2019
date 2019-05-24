package tinytomcat.simple.tomcat.connector;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * process socket connect
 */
public class Connector {


    private ExecutorService pool = new ThreadPoolExecutor(2, 4, 3,
            TimeUnit.SECONDS, new LinkedBlockingQueue<>(10));

    private ServerSocket serverSocket;

    private HttpProcessor httpProcessor = new HttpProcessor();

    public void init() {
        try {
            httpProcessor.init();
            serverSocket = new ServerSocket(ConfigInfo.PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        new Thread(new Acceptor()).start();
    }

    /**
     * listen socket request
     */
    protected class Acceptor implements Runnable {

        @Override
        public void run() {
            try {
                if (serverSocket != null) {
                    while (true) {
                        Socket clientSocket = serverSocket.accept();
                        pool.submit(()->httpProcessor.process(clientSocket));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
