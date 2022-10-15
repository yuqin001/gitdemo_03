package com.example.springbootredisdemo03.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springbootredisdemo03.mapper.UserMapper;
import com.example.springbootredisdemo03.entity.User;
import com.example.springbootredisdemo03.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: qyq
 * @Date: 2022/09/12/13:22
 * @Description:
 */

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
//    @Autowired
    @Resource
    private UserMapper userMapper;

    @Override
    public Boolean exits(User user) {
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq("loginname",user.getLoginname());
        return userMapper.exists(qw);
    }

    @Override
    public User findByUserNameAndPassword(String loginName) {
        //查询用户
        return this.query().eq("LOGINNAME", loginName).one();
    }

}

