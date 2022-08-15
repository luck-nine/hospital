package com.ccunix.hospital.common.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "响应数据")
public class ResponseResult<T> {
    @ApiModelProperty(value = "状态码")
    private Integer code;

    @ApiModelProperty(value = "消息")
    private String msg;

    @ApiModelProperty(value = "数据对象")
    private T data;

    /**
     * 构造方法私有化，不允许外部new Response
     * @param data
     */
    private ResponseResult(T data){
        this.code = ResponseEnum.SUCCESS.getCode();
        this.msg = ResponseEnum.SUCCESS.getMessage();
        this.data = data;
    }

    /**
     * 构造方法私有化，不允许外部new Response
     * @param responseEnum
     */
    private ResponseResult(ResponseEnum responseEnum){
        if (null == responseEnum) {
            return;
        }
        this.code = responseEnum.getCode();
        this.msg = responseEnum.getMessage();
    }

    /**
     * 成功时调用
     * @param
     * @param <T>
     * @return
     */
    public static <T> ResponseResult<T> success(){
        return success(null);
    }
    /**
     * 成功时调用
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResponseResult<T> success(T data){
        return new ResponseResult<T>(data);
    }
    /**
     * 失败时调用
     * @param
     * @param <T>
     * @return
     */
    public static <T> ResponseResult error(){
        return error(ResponseEnum.ERROR);
    }
    /**
     * 失败时调用
     * @param responseEnum
     * @param <T>
     * @return
     */
    public static <T> ResponseResult error(ResponseEnum responseEnum){
        return new ResponseResult<T>(responseEnum);
    }



    public Integer getCode() {
        return code;
    }

    public ResponseResult<T> setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public ResponseResult<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public T getData() {
        return data;
    }

    public ResponseResult<T> setData(T data) {
        this.data = data;
        return this;
    }
}
