package com.zylear.publish.web.bean.passcheck;

import com.zylear.publish.web.bean.BaseResponse;

/**
 * Created by xiezongyu on 2018/10/13.
 */
public class ActivateResponse extends BaseResponse {

    public ActivateResponse() {
    }

    public ActivateResponse(BaseResponse baseResponse, String accountInfo) {
        super(baseResponse);
        this.accountInfo = accountInfo;
    }

    public ActivateResponse(Integer errorCode, String errorMessage, String accountInfo) {
        super(errorCode, errorMessage);
        this.accountInfo = accountInfo;
    }

    private String accountInfo;

    public String getAccountInfo() {
        return accountInfo;
    }

    public void setAccountInfo(String accountInfo) {
        this.accountInfo = accountInfo;
    }

}
