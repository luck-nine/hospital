package com.ccunix.hospital.common.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Util {
    // 固定干扰
    private static final String salt = "1a2b3c4d5e";
    // md5方法加密封装
    public static String md5(String src) {
        return DigestUtils.md5Hex(src);
    }

    // 第一次加密    密码+salt干扰元素
    public static String inputPassToFormPass(String inputPass) {
        // 把密码加入salt密文 撒盐
        String str = "" + salt.charAt(0) + salt.charAt(2) + inputPass + salt.charAt(5) + salt.charAt(4);
        return md5(str);
    }
    // 第二次加密    加密后的md5密文+随机干扰
    public static String formPassToDBPass(String formPass, String salt) {
        // 把form密码加入salt密文 撒盐
        String str = "" + salt.charAt(0) + salt.charAt(2) + formPass + salt.charAt(5) + salt.charAt(4);
        return md5(str);
    }

    // 获得加密后的结果 123456  数据库最终存的是被该方法进行处理的那个密文   saltDB随机干扰，存入到数据库
    public static String inputPassToDBPass(String inputPass,String saltDB) {
        String formPass = inputPassToFormPass(inputPass);
        String dbPass = formPassToDBPass(formPass, saltDB);
        return dbPass;
    }

    public static void main(String[] args) {
        System.out.println(inputPassToFormPass("123456"));

        System.out.println(salt.charAt(0)+salt.charAt(2));
        System.out.println((int)salt.charAt(0));
        System.out.println((int)salt.charAt(2));
        System.out.println(""+salt.charAt(0)+salt.charAt(2));
    }
}
