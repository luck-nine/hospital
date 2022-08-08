package com.ccunix.hospital.security.handle;

import com.ccunix.hospital.common.utils.ServletUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * 认证失败处理类  返回未授权
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint,Serializable {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        String url = request.getRequestURI();
        String errMsg = "请求访问：{"+url+"}，认证失败，无法访问系统资源";
        ServletUtils.renderString(response,errMsg);
    }
}
