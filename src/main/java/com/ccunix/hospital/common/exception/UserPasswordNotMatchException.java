package com.ccunix.hospital.common.exception;

/**
 * 用户名或密码不匹配异常
 */
public class UserPasswordNotMatchException extends UserException {
    private static final long serialVersionUID = 1L;
    public UserPasswordNotMatchException(){
        super("user.password.not.match", null);
    }
}
