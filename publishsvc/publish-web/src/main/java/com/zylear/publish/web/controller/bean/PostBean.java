package com.zylear.publish.web.controller.bean;

import java.util.Date;

/**
 * Created by xiezongyu on 2018/8/8.
 */
public class PostBean {


    private Integer sourceType;

    private String title;

    private Integer spiderCategory;

    private Date postTime;

    private String sourceUrl;

    private String css;

    private String content;


    public Integer getSourceType() {
        return sourceType;
    }

    public void setSourceType(Integer sourceType) {
        this.sourceType = sourceType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getSpiderCategory() {
        return spiderCategory;
    }

    public void setSpiderCategory(Integer spiderCategory) {
        this.spiderCategory = spiderCategory;
    }

    public Date getPostTime() {
        return postTime;
    }

    public void setPostTime(Date postTime) {
        this.postTime = postTime;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }


    public String getCss() {
        return css;
    }

    public void setCss(String css) {
        this.css = css;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
