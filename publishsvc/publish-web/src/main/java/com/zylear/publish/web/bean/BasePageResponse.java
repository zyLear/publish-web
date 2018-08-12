package com.zylear.publish.web.bean;

/**
 * Created by xiezongyu on 2018/4/9.
 */
public class BasePageResponse {

    private Integer errorCode;
    private String errorMessage;


    public static final BasePageResponse SUCCESS_RESPONSE = new BasePageResponse(0, "success");
    public static final BasePageResponse ERROR_RESPONSE = new BasePageResponse(1, "error");

//    public static final BasePageResponse UPLOAD_FAIL_RESPONSE = new BasePageResponse(2, "upload fail");
//    public static final BasePageResponse FILE_EXIST_RESPONSE = new BasePageResponse(3, "file exist");
//    public static final BasePageResponse ID_EXIST_RESPONSE = new BasePageResponse(4, "id exist");
//
//    public static final BasePageResponse OVERSPEND_RESPONSE = new BasePageResponse(5, "overspend");
//
//    public static final BasePageResponse PROJECT_NO_EXIST_RESPONSE = new BasePageResponse(6, "project no exist");
//    public static final BasePageResponse BIDDING_NO_EXIST_RESPONSE = new BasePageResponse(7, "bidding no exist");
//    public static final BasePageResponse BID_NO_EXIST_RESPONSE = new BasePageResponse(8, "bid no exist");
//    public static final BasePageResponse CONTRACT_NO_EXIST_RESPONSE = new BasePageResponse(9, "contract no exist");
//    public static final BasePageResponse BUDGET_NO_EXIST_RESPONSE = new BasePageResponse(10, "budget no exist");
//
//    public static final BasePageResponse CAPTCHA_ERROR = new BasePageResponse(11, "captcha error");


    public BasePageResponse() {
    }

    public BasePageResponse(Integer errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public BasePageResponse(BasePageResponse basePageResponse) {
        this.errorCode = basePageResponse.errorCode;
        this.errorMessage = basePageResponse.errorMessage;
    }


    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }


}
