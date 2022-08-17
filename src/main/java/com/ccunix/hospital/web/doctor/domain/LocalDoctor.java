package com.ccunix.hospital.web.doctor.domain;

import com.ccunix.hospital.common.utils.validate.IsMobile;
import com.ccunix.hospital.security.domain.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@ApiModel(value = "LocalDoctor", description = "乡医信息实体")
public class LocalDoctor extends BaseEntity {
    private String localDoctorId;
    @IsMobile
    @ApiModelProperty("用户名")
    private String username;
    @NotBlank(message = "密码不能为空")
    @Size(min = 0, max = 30, message = "密码长度不能超过30个字符")
    @ApiModelProperty("密码")
    private String password;
    @ApiModelProperty("联系方式")
    private String phone;
    @NotNull
    @ApiModelProperty("姓名")
    private String name;
    /*
        审核状态
     */
    @ApiModelProperty("审核状态")
    private String audit;
    @ApiModelProperty("地址")
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
    @ApiModelProperty("账号状态")
    private String status;
    /*
        删除标志（0代表存在 2代表删除）
     */
    @ApiModelProperty("删除标志")
    private String delFlag;
    /*
        营业执照存储路径
     */
    private String businessLicensePath;
    /*
        医师证书存储路径
     */
    private String certificatePath;
    // 文件上传类型
    /*
        营业执照
     */
    private MultipartFile businessLicense;
    /*
        医师证书
     */
    private MultipartFile certificate;

}
