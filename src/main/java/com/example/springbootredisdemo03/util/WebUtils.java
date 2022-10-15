package com.example.springbootredisdemo03.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Author: qyq
 * @Date: 2022/09/12/11:45
 * @Description:
 */
public class WebUtils {

    /**
     * 获取req
     * @return req
     */
    public static HttpServletRequest getReq(){
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * 获取session
     * @return session
     */
    public static HttpSession getSession(){
        HttpServletRequest req = getReq();
        return req.getSession();

    }

}
