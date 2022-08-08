package com.ccunix.hospital.admin.service.impl;

import com.ccunix.hospital.admin.service.ISysUserService;
import com.ccunix.hospital.common.utils.SecurityUtils;
import com.ccunix.hospital.security.domain.entity.SysUser;
import org.springframework.stereotype.Service;

@Service
public class SysUserService implements ISysUserService {
    @Override
    public SysUser selectUserByUsername(String username) {
        SysUser user = new SysUser();
        user.setUserName(username);
        String pass = SecurityUtils.encryptPassword("admin123");
        user.setPassword(pass);
        return user;
    }
}
