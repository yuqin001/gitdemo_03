package com.example.springbootredisdemo03.entity.dto;

import java.util.HashMap;
import java.util.Map;
/**
 * @Author: qyq
 * @Date: 2022/09/12/11:25
 * @Description:
 */
public class ResultData {

    /**
     * 编号
     */
    private Integer code;

    /**
     * 信息
     */
    private String message;

    /**
     * 返回的内容
     */
    private Map<String,Object> data;

    public static ResultData ok(){
        ResultData resultData = new ResultData();
        resultData.setCode(200);
        resultData.setMessage("成功");
        resultData.setData(new HashMap<>());
        return resultData;
    }

    public static ResultData error(String message){
        ResultData resultData = new ResultData();
        resultData.setCode(500);
        resultData.setMessage(message);
        resultData.setData(new HashMap<>());
        return resultData;
    }

    public static ResultData error(Integer code,String message){
        ResultData resultData = new ResultData();
        resultData.setCode(code);
        resultData.setMessage(message);
        resultData.setData(new HashMap<>());
        return resultData;
    }

    /**
     * 添加数据进入map
     * @param key key
     * @param value value
     * @return
     */
    public ResultData add(String key,Object value){
        this.data.put(key, value);
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}

