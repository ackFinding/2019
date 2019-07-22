package com.seckill.response;

public class CommonReturnType {

    //"success":成功;"fail"：失败
    private String status;
    //如果是success,返回前端需要的json数据
    //fail则返回通用错误码格式
    private Object data;


    public static CommonReturnType create(Object result) {
        return create(result, "success");
    }

    public static CommonReturnType create(Object result, String status) {
        CommonReturnType commonReturnType = new CommonReturnType();
        commonReturnType.setData(result);
        commonReturnType.setStatus(status);
        return commonReturnType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
