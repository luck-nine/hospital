package com.ccunix.hospital.web.doctor.service.impl;

import com.ccunix.hospital.common.utils.SecurityUtils;
import com.ccunix.hospital.web.doctor.domain.LocalDoctor;
import com.ccunix.hospital.web.doctor.mapper.LocalDoctorMapper;
import com.ccunix.hospital.web.doctor.service.ILocalDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocalDoctorService implements ILocalDoctorService {
    @Autowired
    LocalDoctorMapper localDoctorMapper;

    @Override
    public int register(LocalDoctor localDoctor) {
        String password = localDoctor.getPassword();
        // 加密处理
        String encPass = SecurityUtils.encryptPassword(password);
        localDoctor.setPassword(encPass);
        return localDoctorMapper.insert(localDoctor);
    }
}
