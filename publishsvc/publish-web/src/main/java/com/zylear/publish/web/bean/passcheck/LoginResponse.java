package com.zylear.publish.web.bean.passcheck;

import com.zylear.publish.web.bean.BaseResponse;

/**
 * Created by xiezongyu on 2018/10/13.
 */
public class LoginResponse extends BaseResponse {

    public LoginResponse() {
    }

    public LoginResponse(BaseResponse baseResponse, String accountInfo, String helper, String vipHelper) {
        super(baseResponse);
        this.accountInfo = accountInfo;
        this.helper = helper;
        this.vipHelper = vipHelper;
    }

    private String accountInfo;
    private String helper;
    private String vipHelper;

    public String getVipHelper() {
        return vipHelper;
    }

    public void setVipHelper(String vipHelper) {
        this.vipHelper = vipHelper;
    }

    public String getAccountInfo() {
        return accountInfo;
    }

    public void setAccountInfo(String accountInfo) {
        this.accountInfo = accountInfo;
    }

    public String getHelper() {
        return helper;
    }

    public void setHelper(String helper) {
        this.helper = helper;
    }
}
