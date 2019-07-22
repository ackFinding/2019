package com.seckill.controller;

import com.seckill.error.BusinessErrorTypeEnum;
import com.seckill.error.BusinessException;
import com.seckill.response.CommonReturnType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseController {

    public static final String CONTENT_TYPE = "application/x-www-form-urlencoded";

    //定义exceptionhandler解决未被controller层吸收的exception（为业务逻辑处理上的问题或业务逻辑错误而并非服务端不能处理的错误）
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object handleException(HttpServletRequest request, Exception ex) {
        Map<String, Object> responseData = new HashMap<>();
        if (ex instanceof BusinessException) {
            BusinessException businessException = (BusinessException) ex;
            responseData.put("errorCode", businessException.getErrorCode());
            responseData.put("errorMsg", businessException.getErrorMsg());
        } else {
            responseData.put("errorCode", BusinessErrorTypeEnum.UNKNOWN_ERROR.getErrorCode());
            responseData.put("errorMsg", BusinessErrorTypeEnum.UNKNOWN_ERROR.getErrorMsg());
        }
        return CommonReturnType.create(responseData, "fail");
    }
}
