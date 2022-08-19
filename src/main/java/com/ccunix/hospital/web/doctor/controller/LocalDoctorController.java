package com.ccunix.hospital.web.doctor.controller;

import com.ccunix.hospital.common.constant.UserConstants;
import com.ccunix.hospital.common.domain.AjaxResult;
import com.ccunix.hospital.common.domain.ResponseEnum;
import com.ccunix.hospital.common.domain.ResponseResult;
import com.ccunix.hospital.common.exception.file.InvalidExtensionException;
import com.ccunix.hospital.web.doctor.domain.Goods;
import com.ccunix.hospital.web.doctor.domain.LocalDoctor;
import com.ccunix.hospital.web.doctor.service.ILocalDoctorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Api(tags = "乡医用户管理")
@RestController
@RequestMapping("/doctor")
public class LocalDoctorController {

    @ApiOperation("乡医注册")
    @PostMapping("/register")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名称", dataType = "String", dataTypeClass = String.class, required = true),
            @ApiImplicitParam(name = "password", value = "密码", dataType = "String", dataTypeClass = Integer.class, required = true),
            @ApiImplicitParam(name = "name", value = "真实姓名", dataType = "String", dataTypeClass = String.class, required = true),
            @ApiImplicitParam(name = "address", value = "地址", dataType = "String", dataTypeClass = String.class, required = true)

    })
    public ResponseResult<Integer> register(@Valid LocalDoctor localDoctor) throws IOException, InvalidExtensionException {
        // 先验证  用户名是否唯一
        // 不是唯一 需要返回一个消息
        String isUnique = localDoctorService.checkUserNameUnique(localDoctor.getUsername());
        if(UserConstants.NOT_UNIQUE.equals(isUnique)){
            // 不唯一
            return ResponseResult.error(ResponseEnum.USER_ADD_ERROR_PHONE);
        }

        // 注册
        int row = localDoctorService.register(localDoctor);
        return ResponseResult.success(row);
    }

    @Autowired
    ILocalDoctorService localDoctorService;

    @PostMapping("/goodsList")
    public AjaxResult list(){
        AjaxResult ajax = AjaxResult.success();
        List<Goods> goodsList = new ArrayList<>();
        goodsList.add(new Goods("1001","苹果"));
        goodsList.add(new Goods("1002","相蕉"));
        goodsList.add(new Goods("1003","橘子"));
        goodsList.add(new Goods("1004","葡萄"));
        ajax.put("data",goodsList);
        return ajax.success(goodsList);
    }
}
