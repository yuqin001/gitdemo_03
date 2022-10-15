package com.example.springbootredisdemo03.interceptor;

import com.example.springbootredisdemo03.annonation.Authorize;
import com.example.springbootredisdemo03.entity.User;
import com.example.springbootredisdemo03.util.RedisUtil;
import com.example.springbootredisdemo03.util.SubjectUtil;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 权限控制
 * @Author: qyq
 * @Date: 2022/09/12/11:21
 * @Description:
 */
@Component
public class ResourcesInterceptor extends BaseInterceptor implements HandlerInterceptor {

    @Resource
    private RedisUtil redisUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setContentType("application/json;charset=utf-8");

        if (!(handler instanceof HandlerMethod)){
            return true;
        }
        //获取头部资源
        HandlerMethod handlermethod = (HandlerMethod) handler;
        //判断是否存在对应注解
        Method method = handlermethod.getMethod();
        if (method.isAnnotationPresent(Authorize.class)) {
            Authorize annotation = method.getAnnotation(Authorize.class);
            //获取权限或角色
            String permission = annotation.permission();
            //获取用户名
            String userLoginNumber = (String)request.getSession().getAttribute("user");
            //获取用户信息
            User user = (User) redisUtil.get(userLoginNumber);
            UsernamePasswordToken token=new UsernamePasswordToken(user.getLoginname(),user.getPassword());
            //登入校验
            Subject subject = SubjectUtil.getSubject();
            subject.login(token);
            //权限校验
            boolean isRole = subject.hasRole(permission);
            //资源校验
            boolean isPermit = subject.isPermitted(permission);
            if (isPermit||isRole) {
                return true;
            }
            return error(response,"无权限访问");
        }

        return true;

    }

}
