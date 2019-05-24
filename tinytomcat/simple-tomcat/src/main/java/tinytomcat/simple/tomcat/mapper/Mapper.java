package tinytomcat.simple.tomcat.mapper;

import tinytomcat.simple.tomcat.servlet.MyServlet;
import tinytomcat.simple.tomcat.util.PackageUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mapper {

    private Map<String, MappingData> map = new HashMap<>();

    public Mapper init() {
        initServlet();
        return this;
    }

    private void initServlet() {
        List<String> classNames = PackageUtil.getClassName();
        for (String className : classNames) {
            try {
                Class clazz = Class.forName(className);
                if (clazz.isAnnotationPresent(MyServlet.class)) {
                    MyServlet anno = (MyServlet) clazz.getAnnotation(MyServlet.class);
                    map.put(anno.value(), new MappingData(anno.value(), clazz));
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public MappingData getMapper(String path) {
        return map.get(path);
    }

}
