package com.zylear.publish.web.bean;

/**
 * Created by xiezongyu on 2018/8/12.
 */
public class SubmitResponse extends BaseResponse {

    private Integer refId;

    public SubmitResponse(Integer refId) {
        this.refId = refId;
    }

    public SubmitResponse(Integer refId, BaseResponse baseResponse) {
        super(baseResponse);
        this.refId = refId;
    }


    public SubmitResponse(Integer errorCode, String errorMessage, Integer refId) {
        super(errorCode, errorMessage);
        this.refId = refId;
    }

    public Integer getRefId() {
        return refId;
    }

    public void setRefId(Integer refId) {
        this.refId = refId;
    }
}
