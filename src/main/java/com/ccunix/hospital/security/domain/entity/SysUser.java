package com.ccunix.hospital.security.domain.entity;

import java.util.Date;

/**
 * 用户对象 sys_user
 */
public class SysUser extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /*
     * 用户ID
     */
    private Long userId;
    /*
     * 部门ID
     */
    private Long deptId;
    /*
     * 用户账号
     */
    private String userName;
    /*
     * 用户昵称
     */
    private String nickName;
    /*
     * 用户邮箱
     */
    private String email;
    /*
     * 手机号码
     */
    private String phonenumber;
    /*
     * 用户性别
     */
    private String sex;
    /*
     * 用户头像
     */
    private String avatar;
    /*
     * 密码
     */
    private String password;
    /*
     * 盐加密
     */
    private String salt;
    /*
     * 账号状态（0正常 1停用）
     */
    private String status;
    /*
     * 删除标志（0代表存在 2代表删除）
     */
    private String delFlag;
    /*
     * 最后登录IP
     */
    private String loginIp;
    /*
     * 最后登录时间
     */
    private Date loginDate;

    public SysUser() {}

    public SysUser(Long userId) {
        this.userId = userId;
    }
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
