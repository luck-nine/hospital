package com.ccunix.hospital.framework.service;

import com.ccunix.hospital.admin.service.ISysUserService;
import com.ccunix.hospital.common.exception.ServiceException;
import com.ccunix.hospital.security.domain.entity.SysUser;
import com.ccunix.hospital.security.domain.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 接口实现 负责从数据库获得用户信息    封装成UserDetails对象    返回给
 *       authentication = authenticationManager
 *            .authenticate(new UsernamePasswordAuthenticationToken(username,password));
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService{
    @Autowired
    ISysUserService sysUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //从数据库中取出来
        SysUser user = sysUserService.selectUserByUsername(username);
        //验证密码
        if(user==null){
            System.out.println("没有该用户名");
            throw new ServiceException("登录用户：" + username + "不存在");
        }
        // 组装一个UserDetails对象
        LoginUser loginUser = new LoginUser();
        loginUser.setUser(user);
        System.out.println("UserDetailsService获得的信息");
        System.out.println(loginUser);
        return loginUser;
    }
}
