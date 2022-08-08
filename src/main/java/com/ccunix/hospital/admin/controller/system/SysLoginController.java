package com.ccunix.hospital.admin.controller.system;

import com.ccunix.hospital.common.domain.AjaxResult;
import com.ccunix.hospital.common.utils.Constants;
import com.ccunix.hospital.framework.service.SysLoginService;
import com.ccunix.hospital.security.domain.model.LoginBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SysLoginController {
    @Autowired
    private SysLoginService sysLoginService;

    /**
     * 登录方法
     * @param loginBody 登录信息
     * @return
     */
    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginBody loginBody){
        AjaxResult ajax = AjaxResult.success();
        String token = sysLoginService.login(loginBody.getUsername(),loginBody.getPassword());
        // token
        ajax.put(Constants.TOKEN,token);
        return ajax;
    }
}
