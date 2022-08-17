package com.ccunix.hospital.web.doctor.service;

import com.ccunix.hospital.web.doctor.domain.LocalDoctor;

import java.io.IOException;

public interface ILocalDoctorService {
    /**
     * 增加乡医
     * @param localDoctor
     * @return
     */
    int register(LocalDoctor localDoctor) throws IOException;

    /**
     * 通过用户名称（联系方式） 获得乡医信息
     * @param username
     * @return
     */
    LocalDoctor selectLocalDoctorByUsername(String username);

    String checkUserNameUnique(String username);
}
