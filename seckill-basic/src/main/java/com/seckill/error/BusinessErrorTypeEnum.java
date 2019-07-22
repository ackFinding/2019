package com.seckill.error;

public enum BusinessErrorTypeEnum implements CommonError {

    //10001~19999 通用
    PARAM_VALIDATION_ERROR(10001,"参数不合法"),
    UNKNOWN_ERROR(10002,"未知错误"),
    //20001~29999 用户相关
    USER_NOT_EXIST(20001,"用户不存在"),
    USER_LOGIN_FAIL(20002,"用户手机号或密码有误"),
    USER_NOT_LOGIN(20003,"用户尚未登录"),
    //30001~49999 下单相关
    STOCK_NOT_ENOUGH(30001,"库存不足"),

    ;

    private int errorCode;

    private String errorMsg;

    BusinessErrorTypeEnum(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    @Override
    public int getErrorCode() {
        return this.errorCode;
    }

    @Override
    public String getErrorMsg() {
        return this.errorMsg;
    }

    @Override
    public CommonError setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
        return this;
    }

    @Override
    public String toString() {
        return "BusinessErrorTypeEnum{" +
                "errorCode=" + errorCode +
                ", errorMsg='" + errorMsg + '\'' +
                '}';
    }
}
