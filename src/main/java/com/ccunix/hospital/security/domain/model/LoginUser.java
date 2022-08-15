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
    private String userId;
    /*
        部门ID
     */
    private Long deptId;
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


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
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

    public LoginUser(){
    }

    public LoginUser(String userId){
        //this.userId = userId;
    }

    @JSONField(serialize = false)
    @Override
    public String getPassword(){
        return null;
    }

    @JSONField(serialize = false)
    @Override
    public String getUsername(){
        return null;
    }

    //认证权限集合
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
}
