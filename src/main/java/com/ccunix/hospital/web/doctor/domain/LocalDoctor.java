package com.ccunix.hospital.web.doctor.domain;

import com.ccunix.hospital.common.utils.validate.IsMobile;
import com.ccunix.hospital.security.domain.entity.BaseEntity;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class LocalDoctor extends BaseEntity {
    private String localDoctorId;
    @IsMobile
    private String username;
    @NotBlank(message = "密码不能为空")
    @Size(min = 0, max = 30, message = "密码长度不能超过30个字符")
    private String password;
    private String phone;
    @NotNull
    private String name;
    /*
        审核状态
     */
    private String audit;
    private String address;
    /*
        等级id
     */
    private String accountLevelId;
    /*
        账户等级    对象  多对一
     */
    private AccountLevel accountLevel;
    /*
        账户状态（0正常 1停用）
     */
    private String status;
    /*
        删除标志（0代表存在 2代表删除）
     */
    private String delFlag;
}
