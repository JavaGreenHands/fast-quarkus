package com.xiaobai.fast.quarkus.system.service;

import com.xiaobai.fast.quarkus.core.util.TokenUtil;
import com.xiaobai.fast.quarkus.system.domain.SysRole;
import com.xiaobai.fast.quarkus.system.domain.SysUser;
import com.xiaobai.fast.quarkus.system.domain.vo.LoginVo;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Arrays;
import java.util.Collections;

/**
 * 登录业务处理
 * @author baijie <a href="mrwhite777@163.com"></a>
 * @since 1.0
 */
@ApplicationScoped
public class LoginService {

    /**
     * 登录
     * @param loginVo 登录参数
     * @return token
     */
    public String login(LoginVo loginVo){
        String userName = loginVo.getUsername();
//        findByUserName;
        String password = loginVo.getPassword();
        // diff password
        SysUser sysUser = new SysUser();
        sysUser.setUserId(1L);
        SysRole sysRole = new SysRole();
        sysRole.setRoleId(1L);
        sysRole.setRoleName("超级管理员");
        sysRole.setRoleKey("admin");
        sysUser.setSysRoleSet(Collections.singleton(sysRole));
        return  TokenUtil.createToken(sysUser);
    }
}
