package com.chatrobot.pojo;

import java.util.Map;

/**
 * 图灵返回结果实体
 */
public class ResultEntity {

    private int groupType;

    private String resultType;

    private Map<String,String> values;

    public int getGroupType() {
        return groupType;
    }

    public void setGroupType(int groupType) {
        this.groupType = groupType;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public Map<String, String> getValues() {
        return values;
    }

    public void setValues(Map<String, String> values) {
        this.values = values;
    }
}
