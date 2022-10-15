package com.example.springbootredisdemo03.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springbootredisdemo03.entity.User;

/**
 * @Author: qyq
 * @Date: 2022/09/12/11:39
 * @Description:
 */
public interface UserService extends IService<User> {
    /**
    * 退出
    * @Param: [user]
    * @return: java.lang.Boolean
    */
//    public Boolean exits(User user);

    /**
     * 登入
     * @param loginName 用户的账户与密码
     * @return 用户信息
     */
    User findByUserNameAndPassword(String  loginName);
}
