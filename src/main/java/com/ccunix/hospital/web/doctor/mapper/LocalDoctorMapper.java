package com.ccunix.hospital.web.doctor.mapper;

import com.ccunix.hospital.web.doctor.domain.LocalDoctor;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LocalDoctorMapper {
    /**
     * 插入乡医信息
     * @param localDoctor
     * @return
     */
    int insert(LocalDoctor localDoctor);
}
