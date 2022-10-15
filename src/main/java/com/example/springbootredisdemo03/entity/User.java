package com.example.springbootredisdemo03.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Author: qyq
 * @Date: 2022/09/12/11:27
 * @Description:
 */
@ToString
@TableName("user")
public class User implements Serializable {

    private static  final  long serialVersionUID=1L;

   @TableId(type= IdType.AUTO)
    private Long id;
    /**
     * 登入的用户名
     */
    @TableField("LOGINNAME")
    private String loginname;
    /**
     * 密码
     */
    @TableField("PASSWORD")
    private String password;

    /**
     * 登入状态
     */
    @TableField("login_state")
    private Integer loginState;


    /**
     * 登入状态
     */
    @Getter
    @Setter
    @TableField("role_id")
    private Integer roleId;

    public Long getId() {
        return id;
    }



    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

