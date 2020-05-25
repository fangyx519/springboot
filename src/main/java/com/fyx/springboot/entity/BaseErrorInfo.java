package com.fyx.springboot.entity;

/**
 * @Author fyx
 * @Time in 22:52 2020/5/25
 * @Despriction
 */
public interface BaseErrorInfo {

    /*错误码*/
    String getResultCode();

    /*错误描述*/
    String getResultMsg();

}
