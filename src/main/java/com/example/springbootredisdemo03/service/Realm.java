package com.example.springbootredisdemo03.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springbootredisdemo03.mapper.PermissionMapper;
import com.example.springbootredisdemo03.mapper.RoleMapper;
import com.example.springbootredisdemo03.entity.Permission;
import com.example.springbootredisdemo03.entity.Role;
import com.example.springbootredisdemo03.entity.User;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Author: qyq
 * @Date: 2022/09/17/22:09
 * @Description:
 */
@Service
public class Realm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private PermissionMapper permissionMapper;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取用户凭证
        Object primaryPrincipal = principalCollection.getPrimaryPrincipal();
        User user = (User) primaryPrincipal;
        if (user!=null){
            //获取角色
            Role roles = roleMapper.selectOne(
                    new QueryWrapper<Role>()
                            .eq("id", user.getRoleId())
            );

            if(roles==null){
                return null;
            }
            //TODO：获取角色对应的资源
            //获取所有角色的id
            Integer rolesId = roles.getId();
            //通过角色id查询对应的资源
            List<Permission> permissions = permissionMapper
                    .selectList(
                            new QueryWrapper<Permission>()
                            .eq("role_id", rolesId)
                    );
            //角色对应的资源为空
            if (permissions.isEmpty()) {
                return null;
            }
            //TODO:将对象集合转为资源对应的string集合
            //(role只需要对象名)
            String roleName = roles.getRolename();
            //(permission只需要action资源名)
            ArrayList<String> collectPermissions = new ArrayList<>();
            for (Permission permission : permissions) {
                collectPermissions.add(permission.getSource()+":"+permission.getAction());
            }
            //构建资源对象
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            info.addRole(roleName);
            info.addStringPermissions(collectPermissions);
            return info;
        }
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
       String username =(String)authenticationToken.getPrincipal();
       //通过mysql查询数据
        User user;
             user= userService.findByUserNameAndPassword(username);
        //判断对象是否为空
        if (user!=null){
            return new SimpleAuthenticationInfo(user, user.getPassword(), user.getLoginname());
        }
        return null;
    }

}
