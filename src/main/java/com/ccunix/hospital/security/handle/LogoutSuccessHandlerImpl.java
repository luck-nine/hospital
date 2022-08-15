package com.ccunix.hospital.security.handle;

import com.alibaba.fastjson.JSON;
import com.ccunix.hospital.common.domain.AjaxResult;
import com.ccunix.hospital.common.constant.HttpStatus;
import com.ccunix.hospital.common.utils.ServletUtils;
import com.ccunix.hospital.common.utils.StringUtils;
import com.ccunix.hospital.framework.service.TokenService;
import com.ccunix.hospital.security.domain.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义退出处理类 返回成功
 */
@Configuration
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {
    @Autowired
    private TokenService tokenService;

    /**
     * 退出处理
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        LoginUser loginUser = tokenService.getLoginUser(request);
        String userName = "";
        if (StringUtils.isNotNull(loginUser)) {
            userName = loginUser.getUsername();
            // 删除用户缓存记录
            tokenService.delLoginUser(loginUser.getToken());
        }
        ServletUtils.renderString(response, JSON.toJSONString(AjaxResult.error(HttpStatus.SUCCESS,userName+"退出成功")));
    }
}
