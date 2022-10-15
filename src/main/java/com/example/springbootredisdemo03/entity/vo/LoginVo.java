package com.example.springbootredisdemo03.entity.vo;

/**
 * @Author: qyq
 * @Date: 2022/09/12/11:24
 * @Description:
 */
public class LoginVo {

    /**
     * 登入用户名
     */
    private String loginName;

    /**
     * 登入密码
     */
    private String password;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginVo{" +
                "loginName='" + loginName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
