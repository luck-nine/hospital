package com.ccunix.hospital.web.doctor.controller;

import com.ccunix.hospital.common.constant.UserConstants;
import com.ccunix.hospital.common.domain.AjaxResult;
import com.ccunix.hospital.web.doctor.domain.Goods;
import com.ccunix.hospital.web.doctor.domain.LocalDoctor;
import com.ccunix.hospital.web.doctor.service.ILocalDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class LocalDoctorController {
    @Autowired
    ILocalDoctorService localDoctorService;

    @PostMapping("/register")
    public AjaxResult login(@Valid @RequestBody LocalDoctor localDoctor){
        // 先验证  用户名是否唯一
        // 不是唯一 需要返回一个消息
        String isUnique = localDoctorService.checkUserNameUnique(localDoctor.getUsername());
        if(UserConstants.NOT_UNIQUE.equals(isUnique)){
            // 不唯一
            return AjaxResult.error("用户名已经被注册");
        }


        AjaxResult ajax = AjaxResult.success("注册信息获取成功");
        // 注册
        int row = localDoctorService.register(localDoctor);
        ajax.put("data",row);
        return ajax;
    }

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
