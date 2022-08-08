package com.ccunix.hospital.web.doctor.service;

import com.ccunix.hospital.web.doctor.domain.LocalDoctor;

public interface ILocalDoctorService {
    /**
     * 增加乡医
     * @param localDoctor
     * @return
     */
    int register(LocalDoctor localDoctor);
}
