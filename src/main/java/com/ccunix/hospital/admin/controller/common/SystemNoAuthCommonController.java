package com.ccunix.hospital.admin.controller.common;

import com.ccunix.hospital.common.config.SystemConfig;
import com.ccunix.hospital.common.constant.Constants;
import com.ccunix.hospital.common.domain.ResponseResult;
import com.ccunix.hospital.common.utils.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * 不需要验证就可以直接访问 （无需登录）
 */
@RestController
@RequestMapping("/noAuth")
public class SystemNoAuthCommonController {

    @PostMapping("materialsUpload")
    public ResponseResult<Integer> materialsUpload(@RequestParam(value = "file", required = true) MultipartFile multipartFile) throws IOException {

        if(multipartFile!=null && !multipartFile.isEmpty()){
            // 做业务
            // 获得存储路径
            String basePath = SystemConfig.getDoctorMaterialsPath();// E:/ccunix/uploadPath/doctor/materials
            // 格式化时间的日期处理类
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");// 年月日
            String dirPath = simpleDateFormat.format(new Date());
            // 文件存储路径
            String fileSavePath = basePath+"/"+dirPath;// E:/ccunix/uploadPath/doctor/materials/20220816
            // 是否存在该文件夹
            File uploadDir = new File(fileSavePath);
            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }
            // 确定文件名称   hello.jpg
            String uuid = UUID.randomUUID().toString().replace("-","");
            String fileName = multipartFile.getOriginalFilename();
            int suffixIndex = fileName.lastIndexOf(".");
            fileName = uuid + fileName.substring(suffixIndex);
            // 创建存储文件
            File file = new File(uploadDir,fileName);
            // 上传代码
            multipartFile.transferTo(file);// 文件名称
            // 处理   把存储路径保存下来   E:/ccunix/uploadPath/doctor/materials/20220816/~.jpg
            int dirLastIndex = SystemConfig.getProfile().length() + 1;
            String currentDir = StringUtils.substring(fileSavePath, dirLastIndex);
            String dbFilePath = Constants.RESOURCE_PREFIX + "/" + currentDir + "/" + fileName;
            ResponseResult.success(dbFilePath);
        }

        return ResponseResult.error(null);
    }
}
