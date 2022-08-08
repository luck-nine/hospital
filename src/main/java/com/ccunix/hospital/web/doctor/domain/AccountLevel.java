package com.ccunix.hospital.web.doctor.domain;

import com.ccunix.hospital.security.domain.entity.BaseEntity;
import lombok.Data;

@Data
public class AccountLevel extends BaseEntity {
    private String accountLevelId;
    private String name;
}
