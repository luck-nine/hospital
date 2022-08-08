package com.ccunix.hospital.web.doctor.controller;

import com.ccunix.hospital.common.domain.AjaxResult;
import com.ccunix.hospital.common.utils.Constants;
import com.ccunix.hospital.security.domain.model.LoginBody;
import com.ccunix.hospital.web.doctor.domain.LocalDoctor;
import com.ccunix.hospital.web.doctor.service.ILocalDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LocalDoctorController {
    @Autowired
    ILocalDoctorService localDoctorService;

    @PostMapping("/register")
    public AjaxResult login(@RequestBody LocalDoctor localDoctor){
        AjaxResult ajax = AjaxResult.success("注册信息获取成功");
        // 注册
        int row = localDoctorService.register(localDoctor);
        ajax.put("data",row);
        return ajax;
    }
}
