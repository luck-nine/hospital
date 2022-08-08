package com.ccunix.hospital.common.utils;

import com.ccunix.hospital.common.utils.spring.SpringUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

public class MessageUtils {
    /**
     * 根据消息键和参数 获取消息 委托给spring messageSource
     */
    public static String message(String code,Object... args)
    {
        MessageSource messageSource = SpringUtils.getBean(MessageSource.class);
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }
}
