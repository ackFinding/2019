package com.seckill.validator;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class ValidationResult {

    private boolean error;

    private Map<String,String> errorMsgMap = new HashMap<>();

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public Map<String, String> getErrorMsgMap() {
        return errorMsgMap;
    }

    public String getErrorMsg() {
        return StringUtils.join(errorMsgMap.values().toArray(),",");
    }

    public void setErrorMsgMap(Map<String, String> errorMsgMap) {
        this.errorMsgMap = errorMsgMap;
    }
}
