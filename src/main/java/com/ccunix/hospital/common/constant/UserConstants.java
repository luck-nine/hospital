package com.ccunix.hospital.common.constant;

/**
 * 用户常量信息
 */
public class UserConstants {
    /**
     * 平台内系统用户的唯一标志
     */
    public static final String SYS_USER = "SYS_USER";

    /**
     * 正常状态
     */
    public final static String NORMAL = "0";

    /**
     * 异常状态
     */
    public final static String EXCEPTION = "1";

    /**
     * 用户封禁状态
     */
    public final static String USER_DISABLE = "1";
    /**
     * 角色封禁状态
     */
    public final static String ROLE_DISABLE = "1";

    /**
     * 部门正常状态
     */
    public final static String DEPT_NORMAL = "0";

    /**
     * 部门停用状态
     */
    public final static String DEPT_DISABLE = "1";

    /**
     * 字典正常状态
     */
    public final static String DICT_NORMAL = "0";

    /**
     * 是否为系统默认（是）
     */
    public final static String YES = "Y";

    /**
     * 是否为菜单外链（是）
     */
    public final static String YES_FRAME = "0";

    /**
     * 是否为菜单外链（否）
     */
    public final static String NO_FRAME = "1";

    /**
     * 菜单类型（目录）
     */
    public final static String TYPE_DIR = "M";

    /**
     * 菜单类型（菜单）
     */
    public final static String TYPE_MENU = "C";

    /**
     * 菜单类型（按钮）
     */
    public final static String TYPE_BUTTON = "F";

    /**
     * InnerLink组件标识
     */
    public final static String INNER_LINK = "InnerLink";

    /**
     * 校验返回结果码
     */
    public final static String UNIQUE = "0";
    public final static String NOT_UNIQUE = "1";

    /**
     * 用户名长度限制
     */
    public static final int USERNAME_MIN_LENGTH = 2;
    public static final int USERNAME_MAX_LENGTH = 20;

    /**
     * 密码长度限制
     */
    public static final int PASSWORD_MIN_LENGTH = 5;
    public static final int PASSWORD_MAX_LENGTH = 20;

}
