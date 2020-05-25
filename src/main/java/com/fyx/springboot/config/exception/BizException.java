package com.fyx.springboot.config.exception;

import com.fyx.springboot.entity.BaseErrorInfo;

/**
 * @Author fyx
 * @Time in 0:02 2020/5/26
 * @Despriction
 */
public class BizException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    String errorCode;
    String errorMsg;

    public BizException(){
        super();
    }

    public BizException(BaseErrorInfo baseErrorInfo) {
        super(baseErrorInfo.getResultCode());
        this.errorCode = baseErrorInfo.getResultCode();
        this.errorMsg = baseErrorInfo.getResultMsg();
    }

    public BizException(String errorCode, String errorMsg) {
        super(errorCode);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public BizException(BaseErrorInfo baseErrorInfo, Throwable cause) {
        super(baseErrorInfo.getResultCode(), cause);
        this.errorCode = baseErrorInfo.getResultCode();
        this.errorMsg = baseErrorInfo.getResultMsg();
    }

    public BizException(String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
    }

    public BizException(String errorCode, String errorMsg, Throwable cause) {
        super(errorCode, cause);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
