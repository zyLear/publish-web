package com.zylear.publish.web.bean.viewbean.video;

import com.zylear.publish.web.bean.viewbean.base.PageButtonViewBean;

import java.util.List;

/**
 * Created by xiezongyu on 2018/8/9.
 */
public class VideoListViewBean {

    private List<PageButtonViewBean> pageButtons;

    private List<VideoViewBean> videos;


    private Integer tailPage;

    public List<PageButtonViewBean> getPageButtons() {
        return pageButtons;
    }

    public void setPageButtons(List<PageButtonViewBean> pageButtons) {
        this.pageButtons = pageButtons;
    }


    public Integer getTailPage() {
        return tailPage;
    }

    public void setTailPage(Integer tailPage) {
        this.tailPage = tailPage;
    }

    public List<VideoViewBean> getVideos() {
        return videos;
    }

    public void setVideos(List<VideoViewBean> videos) {
        this.videos = videos;
    }
}
