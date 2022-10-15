package com.example.springbootredisdemo03.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springbootredisdemo03.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: qyq
 * @Date: 2022/09/12/13:23
 * @Description:
 */
//
@Mapper
public interface UserMapper extends BaseMapper<User> {
}

