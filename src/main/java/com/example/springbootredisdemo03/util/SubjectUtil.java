package com.example.springbootredisdemo03.util;

import com.example.springbootredisdemo03.service.Realm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @Author: qyq
 * @Date: 2022/09/19/22:16
 * @Description:
 */
@Component
public class SubjectUtil {

    @Resource
    private Realm realm;

    private static Subject subject;

    //    该注解的方法在整个Bean初始化中的执行顺序：Constructor(构造方法) -> @Autowired(依赖注入) -> @PostConstruct(注释的初始化方法)
    //    当依赖注入完成后用于执行初始化的方法，并且只会被执行一次
    @PostConstruct
    public void initR(){
        //校验器
        DefaultSecurityManager securityManager=new DefaultSecurityManager();
        securityManager.setRealm(realm);
        SecurityUtils.setSecurityManager(securityManager);
        subject= SecurityUtils.getSubject();
    }

    public static Subject getSubject() {
        return subject;
    }
}
