package com.ccunix.hospital.common.utils.file;

import com.ccunix.hospital.common.config.SystemConfig;
import com.ccunix.hospital.common.constant.Constants;
import com.ccunix.hospital.common.exception.file.FileNameLengthLimitExceededException;
import com.ccunix.hospital.common.exception.file.FileSizeLimitExceededException;
import com.ccunix.hospital.common.exception.file.InvalidExtensionException;
import com.ccunix.hospital.common.utils.StringUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
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

    /**
     * 默认大小 50M
     */
    public static final long DEFAULT_MAX_SIZE = 50 * 1024 * 1024;
    /**
     * 默认文件名最大长度 100
     */
    public static final int DEFAULT_FILE_NAME_LENGTH = 100;

    // 扩展文件上传   把文件验证加上去    目录名称自己传进去

    /**
     * 默认上传 只需传入上传文件    存储路径
     * @param multipartFile
     * @param basePath
     * @return
     * @throws IOException
     * @throws InvalidExtensionException
     * @throws FileSizeLimitExceededException
     * @throws FileNameLengthLimitExceededException
     */
    public static String upload(MultipartFile multipartFile,String basePath) throws
            IOException, InvalidExtensionException, FileSizeLimitExceededException, FileNameLengthLimitExceededException {
        if (multipartFile!=null && !multipartFile.isEmpty()){
            // 上传进行验证
            // 文件大小验证 KB
            long size = multipartFile.getSize();
            if (size > DEFAULT_MAX_SIZE){
                throw new FileSizeLimitExceededException(DEFAULT_MAX_SIZE / 1024 / 1024);
            }
            // 验证文件上传名字长度
            int fileNameLength = Objects.requireNonNull(multipartFile.getOriginalFilename().length());
            if (fileNameLength > DEFAULT_FILE_NAME_LENGTH){
                throw new FileNameLengthLimitExceededException(DEFAULT_FILE_NAME_LENGTH);
            }
            // 验证文件后缀名是否允许上传    默认格式 未来可以扩展其他的格式
            // 找异常
            assertAllowed(multipartFile, MimeTypeUtils.DEFAULT_ALLOWED_EXTENSION);

            // 开始上传
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");// 年月日
            String dirPath = sdf.format(new Date());
            // 文件存储路径
            String fileSavePath = basePath+"/"+dirPath;// E:/ccunix/uploadPath/doctor/materials/20220816
            File uploadDir = new File(fileSavePath);
            if(!uploadDir.exists()){
                // 创建文件
                uploadDir.mkdirs();
            }
            // 确定文件名称   hello.jpg
            // 获得原来的名字
            String fileName = multipartFile.getOriginalFilename();
            // 获得文件的后缀名
            int suffixIndex = fileName.lastIndexOf(".");
            String uuid = UUID.randomUUID().toString().replace("-","");
            fileName = uuid + fileName.substring(suffixIndex);
            // 创建上传文件的位置
            File file = new File(uploadDir,fileName);
            // 上传代码 保存图片
            multipartFile.transferTo(file);// 文件名称
            // 处理   把存储路径保存到数据库   E:/ccunix/uploadPath/doctor/materials/20220816/~.jpg
            int dirLastIndex = SystemConfig.getProfile().length() + 1;
            String currentDir = StringUtils.substring(fileSavePath, dirLastIndex);
            String dbFilePath = Constants.RESOURCE_PREFIX + "/" + currentDir + "/" + fileName;
            return dbFilePath;
        }
        return null;
    }

    public static final void assertAllowed(MultipartFile file, String[] allowedExtension)
            throws InvalidExtensionException{
        String fileName = file.getOriginalFilename();
        String extension = getExtension(file);
        if (allowedExtension != null && !isAllowedExtension(extension, allowedExtension)){
            if (allowedExtension == MimeTypeUtils.IMAGE_EXTENSION){
                throw new InvalidExtensionException.InvalidImageExtensionException(allowedExtension, extension,
                        fileName);
            }
            else if (allowedExtension == MimeTypeUtils.FLASH_EXTENSION){
                throw new InvalidExtensionException.InvalidFlashExtensionException(allowedExtension, extension,
                        fileName);
            }
            else if (allowedExtension == MimeTypeUtils.MEDIA_EXTENSION){
                throw new InvalidExtensionException.InvalidMediaExtensionException(allowedExtension, extension,
                        fileName);
            }
            else if (allowedExtension == MimeTypeUtils.VIDEO_EXTENSION){
                throw new InvalidExtensionException.InvalidVideoExtensionException(allowedExtension, extension,
                        fileName);
            }
            else {
                throw new InvalidExtensionException(allowedExtension, extension, fileName);
            }
        }
    }

    /**
     * 判断MIME类型是否是允许的MIME类型
     * @param extension
     * @param allowedExtension
     * @return
     */
    public static final boolean isAllowedExtension(String extension, String[] allowedExtension){
        for (String str : allowedExtension){
            if (str.equalsIgnoreCase(extension)){
                return true;
            }
        }
        return false;
    }
    /**
     * 获取文件名的后缀
     * @param file
     * @return
     */
    public static final String getExtension(MultipartFile file) {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        if (StringUtils.isEmpty(extension)){
            extension = MimeTypeUtils.getExtension(Objects.requireNonNull(file.getContentType()));
        }
        return extension;
    }

}
