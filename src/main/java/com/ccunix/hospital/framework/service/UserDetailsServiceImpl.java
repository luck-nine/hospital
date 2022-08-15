package com.ccunix.hospital.framework.service;

import com.ccunix.hospital.common.enums.UserStatus;
import com.ccunix.hospital.common.exception.ServiceException;
import com.ccunix.hospital.common.utils.StringUtils;
import com.ccunix.hospital.web.doctor.domain.LocalDoctor;
import com.ccunix.hospital.web.doctor.domain.LoginLocalDoctor;
import com.ccunix.hospital.web.doctor.service.ILocalDoctorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 接口实现 负责从数据库获得用户信息    封装成UserDetails对象    返回给
 *       authentication = authenticationManager
 *            .authenticate(new UsernamePasswordAuthenticationToken(username,password));
 */
@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService{
    private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private ILocalDoctorService localDoctorService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //从数据库中取出来
        LocalDoctor user = localDoctorService.selectLocalDoctorByUsername(username);
        //验证密码
        if(StringUtils.isNull(user)){
            log.info("登录用户：{} 不存在，",username);
            throw new ServiceException("登录用户：" + username + "不存在");
        }else if (UserStatus.DELETED.getCode().equals(user.getDelFlag())){
            log.info("登录用户：{} 已被删除，",username);
            throw new ServiceException("对不起，您的账号：" + username + "已被删除");
        }else if (UserStatus.DISABLE.getCode().equals(user.getStatus())){
            log.info("登录用户：{} 已被停用，",username);
            throw new ServiceException("对不起，您的账号：" + username + "已停用");
        }

        return createLoginUser(user);
    }

    public UserDetails createLoginUser(LocalDoctor user){
        return new LoginLocalDoctor(user);
    }
}
