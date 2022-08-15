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

    /**
     * 根据用户名获得乡医信息
     * @param username
     * @return
     */
    LocalDoctor selectLocalDoctorByUsername(String username);

    /**
     * 根据用户名查询是否唯一
     * @param username
     * @return
     */
    int checkUserNameUnique(String username);
}
