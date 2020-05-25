package com.fyx.springboot.entity;

import com.alibaba.fastjson.JSONObject;

/**
 * @Author fyx
 * @Time in 0:11 2020/5/26
 * @Despriction
 */
public class ResultBody {

    private String code;
    private String message;
    private Object result;

    public ResultBody() {
    }

    public ResultBody(BaseErrorInfo baseErrorInfo) {
        this.code = baseErrorInfo.getResultCode();
        this.message = baseErrorInfo.getResultMsg();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    /**
     * 成功
     * @param data
     * @return
     */
    public static ResultBody success(Object data){
        ResultBody resultBody = new ResultBody();
        resultBody.setCode(CommonEnum.SUCCESS.getResultCode());
        resultBody.setMessage(CommonEnum.SUCCESS.getResultMsg());
        resultBody.setResult(data);
        return resultBody;
    }

    /**
     * 失败
     * @param baseErrorInfo
     * @return
     */
    public static ResultBody error(BaseErrorInfo baseErrorInfo) {
        ResultBody resultBody = new ResultBody();
        resultBody.setCode(baseErrorInfo.getResultCode());
        resultBody.setMessage(baseErrorInfo.getResultMsg());
        resultBody.setResult(null);
        return resultBody;
    }

    /**
     * 失败
     * @param code
     * @param message
     * @return
     */
    public static ResultBody error(String code, String message) {
        ResultBody resultBody = new ResultBody();
        resultBody.setCode(code);
        resultBody.setMessage(message);
        resultBody.setResult(null);
        return resultBody;
    }


    public static ResultBody error( String message) {
        ResultBody resultBody = new ResultBody();
        resultBody.setCode("-1");
        resultBody.setMessage(message);
        resultBody.setResult(null);
        return resultBody;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
