package tinytomcat.simple.tomcat.connector;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigInfo {

    public static String WEB_ROOT;
    public static int PORT;
    public static String BASE_PACKAGE;


    static {
        Properties props = new Properties();
        try (InputStream in
                     = Thread.currentThread().getContextClassLoader().getResource("server.properties").openStream()) {
            props.load(in);
            WEB_ROOT = props.getProperty("root");
            PORT = Integer.parseInt(props.getProperty("port"));
            BASE_PACKAGE = props.getProperty("basePackage");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
