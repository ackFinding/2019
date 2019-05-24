package tinytomcat.simple.servlet;

/**
 * 响应静态文件
 */
public class StaticResourceProcessor implements Processor {

	@Override
	public void process(Request request, Response response) {
		response.sendStaticResource();
	}
}
