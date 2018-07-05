package com.example.data.request;

/**
 * @author xianglei
 * @created 2018/7/5 12:21
 */
public class ResultException extends RuntimeException {

    private int errCode = 0;

    public ResultException(int errCode, String msg) {
        super(msg);
        this.errCode = errCode;
    }

    public int getErrCode() {
        return errCode;
    }
}
