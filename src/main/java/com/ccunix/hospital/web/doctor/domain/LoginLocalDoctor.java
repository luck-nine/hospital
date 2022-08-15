package com.ccunix.hospital.web.doctor.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.ccunix.hospital.security.domain.model.LoginUser;
import lombok.Data;

@Data
public class LoginLocalDoctor extends LoginUser {
    /*
        用户信息
     */
    private LocalDoctor user;
    public LoginLocalDoctor(LocalDoctor localDoctor){
        // 把乡医的id传给LoginUser
        super(localDoctor.getLocalDoctorId());
        user = localDoctor;
    }
    @JSONField(serialize = false)
    @Override
    public String getPassword(){
        return user.getPassword();
    }
    @Override
    public String getUsername(){
        return user.getUsername();
    }
}
