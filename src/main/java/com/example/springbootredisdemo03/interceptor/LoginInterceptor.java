package com.example.springbootredisdemo03.interceptor;

import com.example.springbootredisdemo03.util.RedisUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: qyq
 * @Date: 2022/09/12/11:21
 * @Description:
 */
@Component
public class LoginInterceptor extends BaseInterceptor implements HandlerInterceptor {

    @Resource
    private RedisUtil redisUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setContentType("application/json;charset=utf-8");
        //1.判断用户是否登入
        String userLoginNumber = (String)request.getSession().getAttribute("user");
       //是否携带session
        if (userLoginNumber==null){
           return error(response,"请先登入");
        }
        //查询session是否合法
        if (!redisUtil.hasKey(userLoginNumber)) {
            return error(response,"session不合法");
        }
       return true;

    }
}
