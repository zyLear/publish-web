package com.zylear.publish.web.bean.viewbean.video;

/**
 * Created by xiezongyu on 2018/8/5.
 */
public class VideoViewBean {

    private Integer id;
    private String title;
    private String css;
    private String content;
    private String postTime;

    private String videoSource;
    private String flashvars;
    private String coverImgUrl;

    public String getVideoSource() {
        return videoSource;
    }

    public void setVideoSource(String videoSource) {
        this.videoSource = videoSource;
    }

    public String getFlashvars() {
        return flashvars;
    }

    public void setFlashvars(String flashvars) {
        this.flashvars = flashvars;
    }

    public String getCoverImgUrl() {
        return coverImgUrl;
    }

    public void setCoverImgUrl(String coverImgUrl) {
        this.coverImgUrl = coverImgUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getPostTime() {
        return postTime;
    }

    public void setPostTime(String postTime) {
        this.postTime = postTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
