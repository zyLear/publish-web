package com.zylear.publish.web.domain.publish;

import com.zylear.publish.web.bean.viewbean.video.VideoInterface;

import java.util.Date;

public class PubgVideo implements VideoInterface {
    private Integer id;

    private Byte sourceType;

    private String title;

    private String coverImgUrl;

    private Byte videoCategory;

    private Date postTime;

    private String sourceUrl;

    private String flashvars;

    private Integer contentId;

    private String videoSource;

    private Byte videoType;

    private Integer pageView;

    private Byte isDeleted;

    private Date createTime;

    private Date lastUpdateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Byte getSourceType() {
        return sourceType;
    }

    public void setSourceType(Byte sourceType) {
        this.sourceType = sourceType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCoverImgUrl() {
        return coverImgUrl;
    }

    public void setCoverImgUrl(String coverImgUrl) {
        this.coverImgUrl = coverImgUrl;
    }

    public Byte getVideoCategory() {
        return videoCategory;
    }

    public void setVideoCategory(Byte videoCategory) {
        this.videoCategory = videoCategory;
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

    public String getFlashvars() {
        return flashvars;
    }

    public void setFlashvars(String flashvars) {
        this.flashvars = flashvars;
    }

    public Integer getContentId() {
        return contentId;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    public String getVideoSource() {
        return videoSource;
    }

    public void setVideoSource(String videoSource) {
        this.videoSource = videoSource;
    }

    public Byte getVideoType() {
        return videoType;
    }

    public void setVideoType(Byte videoType) {
        this.videoType = videoType;
    }

    public Integer getPageView() {
        return pageView;
    }

    public void setPageView(Integer pageView) {
        this.pageView = pageView;
    }

    public Byte getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
}