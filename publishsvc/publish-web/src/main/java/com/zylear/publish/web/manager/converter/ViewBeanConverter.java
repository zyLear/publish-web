package com.zylear.publish.web.manager.converter;

import com.zylear.publish.web.bean.viewbean.article.ArticleInterface;
import com.zylear.publish.web.bean.viewbean.article.ArticleViewBean;
import com.zylear.publish.web.bean.viewbean.base.PageButtonViewBean;
import com.zylear.publish.web.bean.viewbean.video.VideoInterface;
import com.zylear.publish.web.bean.viewbean.video.VideoViewBean;
import com.zylear.publish.web.domain.publish.ArticleContentWithBLOBs;
import com.zylear.publish.web.domain.publish.OwnBlog;
import com.zylear.publish.web.util.DateUtil;
import com.zylear.publish.web.util.PaginationUtil;
import com.zylear.publish.web.util.PaginationUtil.PaginationResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiezongyu on 2018/8/10.
 */
public class ViewBeanConverter {

//    public static List<PageButtonViewBean> toPageButtonViewBean(Integer pageIndex, PaginationResult paginationResult) {
//
//        List<PageButtonViewBean> pageButtonViewBeans = new ArrayList<>();
//        for (int i = paginationResult.getLowPage(); i <= paginationResult.getTopPage(); i++) {
//            PageButtonViewBean pageButtonViewBean = new PageButtonViewBean();
//            pageButtonViewBean.setPageIndex(i);
//            if (i == pageIndex) {
//                pageButtonViewBean.setActive(true);
//            } else {
//                pageButtonViewBean.setActive(false);
//            }
//            pageButtonViewBeans.add(pageButtonViewBean);
//        }
//        return pageButtonViewBeans;
//    }

    public static List<PageButtonViewBean> toPageButtonViewBean(Integer pageRange, Integer pageIndex, Integer maxPage) {

        PaginationResult paginationResult = PaginationUtil.paging(pageRange, pageIndex, maxPage);

        List<PageButtonViewBean> pageButtonViewBeans = new ArrayList<>();
        for (int i = paginationResult.getLowPage(); i <= paginationResult.getTopPage(); i++) {
            PageButtonViewBean pageButtonViewBean = new PageButtonViewBean();
            pageButtonViewBean.setPageIndex(i);
            if (i == pageIndex) {
                pageButtonViewBean.setActive(true);
            } else {
                pageButtonViewBean.setActive(false);
            }
            pageButtonViewBeans.add(pageButtonViewBean);
        }
        return pageButtonViewBeans;
    }

    public static <T extends ArticleInterface> List<ArticleViewBean> toArticleViewBean(List<T> articles) {
        List<ArticleViewBean> articleViewBeans = new ArrayList<>(articles.size());
        for (ArticleInterface article : articles) {
            ArticleViewBean articleViewBean = new ArticleViewBean();
            articleViewBean.setTitle(article.getTitle());
            if (article.getPostTime() == null) {
                articleViewBean.setPostTime("--");
            } else {
                articleViewBean.setPostTime(DateUtil.formatToYDMHMS(article.getPostTime()));
            }
            articleViewBean.setId(article.getId());
            articleViewBeans.add(articleViewBean);
        }
        return articleViewBeans;
    }


    public static ArticleViewBean toArticleViewBean(ArticleInterface articleInterface, ArticleContentWithBLOBs articleContent) {
        ArticleViewBean articleViewBean = new ArticleViewBean();
        articleViewBean.setTitle(articleInterface.getTitle());
        articleViewBean.setCss(articleContent.getCss());
        articleViewBean.setContent(articleContent.getContent());
        if (articleInterface.getPostTime() == null) {
            articleViewBean.setPostTime("--");
        } else {
            articleViewBean.setPostTime(DateUtil.formatToYDMHMS(articleInterface.getPostTime()));
        }
        return articleViewBean;
    }


    public static ArticleViewBean toArticleViewBean(OwnBlog ownBlog) {
        ArticleViewBean articleViewBean = new ArticleViewBean();
        articleViewBean.setTitle(ownBlog.getTitle());
        articleViewBean.setContent(ownBlog.getContent());
//        if (ownBlog.getPostTime() == null) {
//            articleViewBean.setPostTime("--");
//        } else {
        articleViewBean.setPostTime(DateUtil.formatToYDMHMS(ownBlog.getPostTime()));
//        }
        return articleViewBean;
    }


    public static VideoViewBean toVideoViewBean(VideoInterface videoInterface, ArticleContentWithBLOBs articleContent) {
        VideoViewBean videoViewBean = new VideoViewBean();
        videoViewBean.setTitle(videoInterface.getTitle());
        if (articleContent != null) {
            videoViewBean.setCss(articleContent.getCss());
            videoViewBean.setContent(articleContent.getContent());
        }
        videoViewBean.setFlashvars(videoInterface.getFlashvars());
        videoViewBean.setVideoSource(videoInterface.getVideoSource());
        videoViewBean.setCoverImgUrl(videoInterface.getCoverImgUrl());
        videoViewBean.setPostTime(DateUtil.formatToYDMHMS(videoInterface.getPostTime()));

        return videoViewBean;
    }


    public static <T extends VideoInterface> List<VideoViewBean> toVideoViewBean(List<T> videos) {
        List<VideoViewBean> videoViewBeans = new ArrayList<>(videos.size());
        for (VideoInterface video : videos) {
            VideoViewBean videoViewBean = new VideoViewBean();
            videoViewBean.setTitle(video.getTitle());
            videoViewBean.setPostTime(DateUtil.formatToYDMHMS(video.getPostTime()));
            videoViewBean.setCoverImgUrl(video.getCoverImgUrl());
            videoViewBean.setId(video.getId());
            videoViewBeans.add(videoViewBean);
        }
        return videoViewBeans;
    }
}
