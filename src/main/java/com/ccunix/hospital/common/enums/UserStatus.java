package com.ccunix.hospital.common.enums;

public enum UserStatus {
    /**
     * 正常
     */
    NORMAL("0"),
    /**
     * 停用
     */
    DISABLE("1"),
    /**
     * 删除
     */
    DELETED("2");

    private String code;

    UserStatus(String code){
        this.code = code;
    }
    public String getCode(){
        return this.code;
    }


}
