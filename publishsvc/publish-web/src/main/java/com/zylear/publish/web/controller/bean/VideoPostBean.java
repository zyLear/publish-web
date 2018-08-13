package com.zylear.publish.web.controller.bean;

/**
 * Created by xiezongyu on 2018/8/8.
 */
public class VideoPostBean {


    private Integer sourceType;

    private String title;

    private Integer videoCategory;

    private Long postTime;

    private String sourceUrl;

    private String css;

    private String content;

    private String coverImgUrl;

    private String flashvars;

    private Integer videoType;

    private String videoSource;

    public String getCoverImgUrl() {
        return coverImgUrl;
    }

    public void setCoverImgUrl(String coverImgUrl) {
        this.coverImgUrl = coverImgUrl;
    }

    public String getFlashvars() {
        return flashvars;
    }

    public void setFlashvars(String flashvars) {
        this.flashvars = flashvars;
    }

    public Integer getVideoType() {
        return videoType;
    }

    public void setVideoType(Integer videoType) {
        this.videoType = videoType;
    }

    public String getVideoSource() {
        return videoSource;
    }

    public void setVideoSource(String videoSource) {
        this.videoSource = videoSource;
    }

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

    public Integer getVideoCategory() {
        return videoCategory;
    }

    public void setVideoCategory(Integer videoCategory) {
        this.videoCategory = videoCategory;
    }

    public Long getPostTime() {
        return postTime;
    }

    public void setPostTime(Long postTime) {
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
