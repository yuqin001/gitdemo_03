package com.example.springbootredisdemo03.interceptor;

import com.example.springbootredisdemo03.entity.dto.ResultData;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author: qyq
 * @Date: 2022/09/19/23:02
 * @Description:
 */
public abstract class BaseInterceptor {

    /**
     * 在前端返回一个错误提示
     * @param response rep
     * @param message 错误信息
     * @return 是否通过过滤器
     * @throws IOException
     */
    protected Boolean error(HttpServletResponse response, String message) throws IOException {
        //响应一个错误
        PrintWriter writer = response.getWriter();
        ResultData resultData = ResultData.error(1000, message);
        //将对象转换成json
        ObjectMapper objectMapper = new ObjectMapper();
        String value = objectMapper.writeValueAsString(resultData);
        writer.print(value);

        writer.flush();
        writer.close();
        return false;
    }
}
