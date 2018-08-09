package com.zylear.publish.web.bean;

/**
 * Created by yangzirong on 8/22/2017.
 */
public class PageParam {



    public PageParam(Integer pageSize, Integer offSet) {
        this.pageSize = pageSize;
        this.offSet = offSet;
    }

    private Integer pageSize;
    private Integer offSet;

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getOffSet() {
        return offSet;
    }

    public void setOffSet(Integer offSet) {
        this.offSet = offSet;
    }

    public PageParam() {
    }

    @Override
    public String toString() {
        return "PageParam{" +
                "pageSize=" + pageSize +
                ", offSet=" + offSet +
                '}';
    }
}
