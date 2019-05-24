package tinytomcat.simple.tomcat.util;

public class BodyBuilder {

    public static String build(String content) {
        StringBuilder builder = new StringBuilder();
        builder.append("<html><body>")
                .append(h1Builder(content))
                .append("</body></html>");
        return builder.toString();
    }

    public static String h1Builder(String content) {
        StringBuilder builder = new StringBuilder();
        builder.append("<h1>")
                .append(content)
                .append("</h1>");
        return builder.toString();
    }
}
