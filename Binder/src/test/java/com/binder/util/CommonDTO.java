package com.binder.util;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 * Created by  on 16/12/6.
 */
public class CommonDTO<T> implements Serializable {
    private static final long serialVersionUID = -4505655308965878999L;

    //请求成功返回码为：200
    private static final int successCode = 200;
    public static final int CMDB_SUCCESS_CODE = 201;
    //返回数据
    private T retObj;
    //返回码
    private int code;
    //返回描述
    private String resultInfo;
    private String error;
    private String tradeId;

    private String msg;
    /**
     * 区分哪个方法
     */
    private String method;
    public CommonDTO(){
        this.code = successCode;
        this.msg = "请求成功";
    }
    public CommonDTO(int code){
        this();
        this.code = code;
    }
    public CommonDTO(int code, String resultInfo){
        this();
        this.code = code;
        this.resultInfo = resultInfo;
    }
    public CommonDTO(T retObj, JSONObject adapterApiInputParam) {
        this();
        this.code = successCode;
        this.retObj = retObj;
        this.method = adapterApiInputParam.getString("method");
        this.tradeId = adapterApiInputParam.getString("requestId");
    }
    public CommonDTO(int code, String resultInfo, String error){
        this();
        this.code = code;
        this.resultInfo = resultInfo;
        this.error=error;
    }
    public CommonDTO(int code, String resultInfo, T retObj){
        this();
        this.code = code;
        this.resultInfo = resultInfo;
        this.retObj = retObj;
    }
    public CommonDTO(int code, String resultInfo, String error, T retObj){
        this();
        this.code = code;
        this.resultInfo = resultInfo;
        this.error=error;
        this.retObj = retObj;
    }
    public CommonDTO(T retObj){
        this();
        this.retObj = retObj;
    }

    public CommonDTO(T retObj, String method, String tradeId){
        this();
        this.retObj = retObj;
        this.method = method;
        this.tradeId = tradeId;
    }
    public CommonDTO(T obj, String msg, int code){
        this();
        this.retObj = obj;
        this.msg = msg;
        this.code = code;
    }
    public CommonDTO(T retObj, String tradeId){
        this();
        this.retObj = retObj;
        this.tradeId = tradeId;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getRetObj() {
        return retObj;
    }

    public void setRetObj(T retObj) {
        this.retObj = retObj;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getResultInfo() {
        return resultInfo;
    }

    public void setResultInfo(String resultInfo) {
        this.resultInfo = resultInfo;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public static int getSuccessCode() {
        return successCode;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }
}
