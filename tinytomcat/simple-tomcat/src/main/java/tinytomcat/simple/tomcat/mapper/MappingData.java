package tinytomcat.simple.tomcat.mapper;

public class MappingData {

    private String url;//请求url

    private Class clazz;//对应servlet类

    public MappingData(String url, Class clazz) {
        this.url = url;
        this.clazz = clazz;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }
}
