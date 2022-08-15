package com.ccunix.hospital.common.utils.validate;

import com.ccunix.hospital.common.utils.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 验证手机号    年龄  性别  身份证号
 */
public class ValidateUtil {
    // 电话号
    private static final Pattern pattern_mobile= Pattern.compile("^1[3456789]\\d{9}$");

    public static boolean isMobile(String mobile) {
        if(StringUtils.isEmpty(mobile)) {
            return false;
        }
        Matcher m = pattern_mobile.matcher(mobile);
        return m.matches();
    }

    // 身份证号
}
