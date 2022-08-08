package com.ccunix.hospital.framework.service;

import com.ccunix.hospital.common.exception.ServiceException;
import com.ccunix.hospital.common.exception.UserPasswordNotMatchException;
import com.ccunix.hospital.security.domain.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;

@Component
public class SysLoginService {
    @Autowired
    private TokenService tokenService;
    @Resource
    private AuthenticationManager authenticationManager;

    /**
     * 登录验证
     * @param username 用户输入的用户名
     * @param password 用户输入的密码
     * @return
     */
    public String login(String username, String password) {
        //用户验证
        Authentication authentication = null;
        try
        {
            //该方法会去调用UserDetailsServiceImpl.loadUserByUsername
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(username,password));
        }catch (Exception e)
        {
            if (e instanceof BadCredentialsException)
            {
                throw new UserPasswordNotMatchException();
            }
            else
            {
                throw new ServiceException(e.getMessage());
            }
        }
        System.out.println("AuthenticationManager获得的信息");
        System.out.println(authentication.getPrincipal());
        // 获得loadUserByUsername方法返回的UserDetails
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        // loginUser信息进行加密处理    jwt生成的token
        String token = tokenService.createToken(loginUser);
        System.out.println("jwt生成的令牌 token="+token);
        return token;
    }
}
