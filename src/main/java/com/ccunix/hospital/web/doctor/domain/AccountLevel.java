package com.ccunix.hospital.web.doctor.domain;

import com.ccunix.hospital.security.domain.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "AccountLevel", description = "账号等级")
public class AccountLevel extends BaseEntity {
    @ApiModelProperty("uuid标识")
    private String accountLevelId;
    @ApiModelProperty("等级名称")
    private String name;
}
