package com.ccunix.hospital.web.doctor.service.impl;

import com.ccunix.hospital.common.config.SystemConfig;
import com.ccunix.hospital.common.constant.UserConstants;
import com.ccunix.hospital.common.exception.file.InvalidExtensionException;
import com.ccunix.hospital.common.utils.SecurityUtils;
import com.ccunix.hospital.common.utils.file.FileUploadUtil;
import com.ccunix.hospital.web.doctor.domain.LocalDoctor;
import com.ccunix.hospital.web.doctor.mapper.LocalDoctorMapper;
import com.ccunix.hospital.web.doctor.service.ILocalDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class LocalDoctorService implements ILocalDoctorService {
    @Autowired
    LocalDoctorMapper localDoctorMapper;

    @Override
    public int register(LocalDoctor localDoctor) throws IOException, InvalidExtensionException {
        String password = localDoctor.getPassword();
        // 加密处理
        String encPass = SecurityUtils.encryptPassword(password);
        localDoctor.setPassword(encPass);

        // 处理文件上传
        /*String businessLicensePath = FileUploadUtil.upload(localDoctor.getBusinessLicense());
        String certificatePath = FileUploadUtil.upload(localDoctor.getCertificate());*/
        // 传递存储路径
        String businessLicensePath = FileUploadUtil.upload(localDoctor.getBusinessLicense(), SystemConfig.getDoctorMaterialsPath());
        String certificatePath = FileUploadUtil.upload(localDoctor.getCertificate(),SystemConfig.getDoctorMaterialsPath());
        // 设置存储模型里
        localDoctor.setBusinessLicensePath(businessLicensePath);
        localDoctor.setCertificatePath(certificatePath);
        return localDoctorMapper.insert(localDoctor);
    }

    @Override
    public LocalDoctor selectLocalDoctorByUsername(String username) {
        return localDoctorMapper.selectLocalDoctorByUsername(username);
    }

    @Override
    public String checkUserNameUnique(String username) {
        int row = localDoctorMapper.checkUserNameUnique(username);
        if(row>0)
            return UserConstants.NOT_UNIQUE;
        return UserConstants.UNIQUE;
    }
}
