package com.ccunix.hospital.common.exception;

import com.ccunix.hospital.common.exception.base.BaseException;

/**
 * 用户信息异常类
 */
public class UserException extends BaseException {
    private static final long serialVersionUID = 1L;
    // code user.password.not.match
    public UserException(String code, Object[] args) {
        super("user", code, args, null);
    }
}
