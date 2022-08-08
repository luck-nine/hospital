package com.ccunix.hospital.security.domain.model;


import com.alibaba.fastjson.annotation.JSONField;
import com.ccunix.hospital.security.domain.entity.SysUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * 登录用户身份权限
 */
public class LoginUser implements UserDetails {
    private static final long serialVersionUID = 1L;
    /*
        用户ID
     */
    private  Long userId;
    /*
        部门ID
     */
    private  Long deptId;
    /*
        用户唯一标识
     */
    private String token;
    /*
        登录时间
     */
    private Long loginTime;
    /*
        过期时间
     */
    private Long expireTime;
    /*
        用户信息
     */
    private SysUser user;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Long loginTime) {
        this.loginTime = loginTime;
    }

    public Long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
    }

    public SysUser getUser() {
        return user;
    }

    public void setUser(SysUser user) {
        this.user = user;
    }

    //认证权限集合
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
    //从数据库返回的密码
    @Override
    public String getPassword() {
        return user.getPassword();
    }
    //存用户名
    @Override
    public String getUsername() {
        return user.getUserName();
    }

    @JSONField(serialize = false)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JSONField(serialize = false)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JSONField(serialize = false)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JSONField(serialize = false)
    @Override
    public boolean isEnabled() {
        return true;
    }


}
