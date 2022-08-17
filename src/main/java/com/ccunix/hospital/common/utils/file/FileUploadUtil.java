package com.ccunix.hospital.common.utils.file;

import com.ccunix.hospital.common.config.SystemConfig;
import com.ccunix.hospital.common.constant.Constants;
import com.ccunix.hospital.common.domain.ResponseResult;
import com.ccunix.hospital.common.utils.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class FileUploadUtil {
    public static String upload(MultipartFile multipartFile) throws IOException {

        if(multipartFile!=null && !multipartFile.isEmpty()){
            // 格式化时间的日期处理类
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");// 年月日
            // 获得存储路径
            String basePath = SystemConfig.getDoctorMaterialsPath();// E:/ccunix/uploadPath/doctor/materials
            String dirPath = sdf.format(new Date());
            // 文件存储路径
            String fileSavePath = basePath+"/"+dirPath;// E:/ccunix/uploadPath/doctor/materials/20220816
            // 是否存在该文件夹
            File uploadDir = new File(fileSavePath);
            if(!uploadDir.exists()){
                uploadDir.mkdirs();
            }
            // 确定文件名称   hello.jpg
            String uuid = UUID.randomUUID().toString().replace("-","");
            // 获得原来的名字
            String fileName = multipartFile.getOriginalFilename();
            // 获得文件的后缀名
            int suffixIndex = fileName.lastIndexOf(".");
            fileName = uuid + fileName.substring(suffixIndex);
            // 创建存储文件
            File file = new File(uploadDir,fileName);
            // 上传代码
            multipartFile.transferTo(file);// 文件名称
            // 处理   把存储路径保存到数据库   E:/ccunix/uploadPath/doctor/materials/20220816/~.jpg
            int dirLastIndex = SystemConfig.getProfile().length() + 1;
            String currentDir = StringUtils.substring(fileSavePath, dirLastIndex);
            String dbFilePath = Constants.RESOURCE_PREFIX + "/" + currentDir + "/" + fileName;
            return dbFilePath;
        }
        return null;
    }
}
