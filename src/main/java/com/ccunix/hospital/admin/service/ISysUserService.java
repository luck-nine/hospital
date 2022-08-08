package com.ccunix.hospital.admin.service;

import com.ccunix.hospital.security.domain.entity.SysUser;

public interface ISysUserService {
    SysUser selectUserByUsername(String username);
}
