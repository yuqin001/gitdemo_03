package com.example.springbootredisdemo03.controller;

import com.example.springbootredisdemo03.annonation.Authorize;
import com.example.springbootredisdemo03.entity.User;
import com.example.springbootredisdemo03.service.Realm;
import com.example.springbootredisdemo03.entity.dto.ResultData;
import com.example.springbootredisdemo03.entity.vo.LoginVo;
import com.example.springbootredisdemo03.service.UserService;
import com.example.springbootredisdemo03.util.RedisUtil;
import com.example.springbootredisdemo03.util.SubjectUtil;
import com.example.springbootredisdemo03.util.WebUtils;
import net.sf.jsqlparser.util.validation.metadata.NamedObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import java.util.HashMap;

import static net.sf.jsqlparser.util.validation.metadata.NamedObject.user;

/**
 * @Author: qyq
 * @Date: 2022/09/12/11:30
 * @Description:
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private UserService userService;


    @GetMapping("exits")
    public ResultData exits(){
        String loginNumber = (String) WebUtils.getSession().getAttribute("user");
        WebUtils.getSession().removeAttribute("user");
        redisUtil.del(loginNumber);
        return ResultData.ok();
    }

    /**
     * 登录
     */
    @PostMapping("/loginService")
    public ResultData login(@RequestBody LoginVo loginVo){
        //组装校验数据
        UsernamePasswordToken token=new UsernamePasswordToken(loginVo.getLoginName(),loginVo.getPassword());
        try {
            SubjectUtil.getSubject().login(token);
        } catch (AuthenticationException e) {
            return ResultData.error(410,"用户名或密码错误！");
        }

        //查询用户信息
        User user = userService.findByUserNameAndPassword(loginVo.getLoginName());

        //登入成功将user存入Session
        WebUtils.getSession().setAttribute("user",token.getUsername());

        //然后取出session，将它存入redis
        Object session=WebUtils.getSession().getAttribute("user");
        //存进redis
        if (!redisUtil.set(token.getUsername(), user,60*60*3)){
            return ResultData.error(411,"redis缓存失败！");
        }
        return ResultData.ok();
    }


    /**
     * 登入测试接口
     * @return
     */
    @GetMapping("test")
    public ResultData test(){
        return ResultData.ok().add("es","欢迎");
    }
    /**
     * 权限测试接口 hr
     * @Annonation Authorize 表明接口所需的资源或角色
     * @return resul
     */
    @Authorize(permission = "hr")
    @GetMapping("hr")
    public ResultData hr(){
        return ResultData.ok().add("hr","欢迎-hr");
    }
    /**
     * 权限测试接口 seller
     * @Annonation Authorize 表明接口所需的资源或角色
     * @return result
     */
    @Authorize(permission = "list")
    @GetMapping("seller")
    public ResultData seller(){
        return ResultData.ok().add("es","欢迎-seller");
    }

    /**
     * 权限测试接口 ：欢迎-qyq,你是seller,有查看customer的list的权限
     * @Annonation Authorize 表明接口所需的资源或角色
     * @return result
     */
    @Authorize(permission = "customer:save")
    @GetMapping("test1")
    public ResultData test1(){
        User user= userService.findByUserNameAndPassword("qyq");
        System.out.println(user);
        return ResultData.ok().add("es","欢迎-qyq,你是seller,有查看customer的list的权限");
    }

}
