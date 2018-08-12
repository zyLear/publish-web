package com.zylear.publish.web.controller;

import com.zylear.publish.web.bean.viewbean.article.ArticleListViewBean;
import com.zylear.publish.web.bean.viewbean.article.ArticleViewBean;
import com.zylear.publish.web.bean.PageParam;
import com.zylear.publish.web.bean.viewbean.video.VideoListViewBean;
import com.zylear.publish.web.bean.viewbean.video.VideoViewBean;
import com.zylear.publish.web.manager.ArticleManager;
import com.zylear.publish.web.manager.VideoManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by xiezongyu on 2018/8/5.
 */
@Controller
@RequestMapping("/lol")
public class LolController {

    private ArticleManager articleManager;
    private VideoManager videoManager;
    private Integer DEFAULT_ARTICLE_PAGE_SIZE = 20;
    private Integer DEFAULT_ARTICLE_PAGE_RANGE = 3;

    private Integer DEFAULT_VIDEO_PAGE_SIZE = 20;
    private Integer DEFAULT_VIDEO_PAGE_RANGE = 3;


    @RequestMapping("/article-list/{page}.html")
    public ModelAndView showArticleList(@PathVariable("page") String page) {
        Integer pageIndex = Integer.valueOf(page);
        PageParam pageParam = new PageParam(DEFAULT_ARTICLE_PAGE_SIZE, (pageIndex - 1) * DEFAULT_ARTICLE_PAGE_SIZE);
        ArticleListViewBean articleListViewBean = articleManager.findLolArticleListViewBean(DEFAULT_ARTICLE_PAGE_RANGE, pageIndex, pageParam);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("lol/article/lol-article-list");
        if (articleListViewBean != null) {
            modelAndView.addObject("articleListViewBean", articleListViewBean);
        }
        return modelAndView;
    }


    @RequestMapping("/article/{articleId}.html")
    public ModelAndView showBlog(@PathVariable("articleId") String articleId) {
        Integer id = Integer.valueOf(articleId);
        ArticleViewBean article = articleManager.findLolArticleContent(id);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("lol/article/show-lol-article");
        if (article != null) {
            modelAndView.addObject("article", article);
        }
        return modelAndView;
    }

    @RequestMapping("/video-list/{page}.html")
    public ModelAndView showVideoList(@PathVariable("page") String page) {
        Integer pageIndex = Integer.valueOf(page);
        PageParam pageParam = new PageParam(DEFAULT_VIDEO_PAGE_SIZE, (pageIndex - 1) * DEFAULT_VIDEO_PAGE_SIZE);
        VideoListViewBean videoListViewBean = videoManager.findLolVideoListViewBean(DEFAULT_VIDEO_PAGE_RANGE, pageIndex, pageParam);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("lol/video/lol-video-list");
        if (videoListViewBean != null) {
            modelAndView.addObject("videoListViewBean", videoListViewBean);
        }
        return modelAndView;
    }

    @RequestMapping("/video/{videoId}.html")
    public ModelAndView showVideo(@PathVariable("videoId") String videoId) {
        Integer id = Integer.valueOf(videoId);
        VideoViewBean video = videoManager.findLolVideoContent(id);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("lol/video/show-lol-video");
        if (video != null) {
            modelAndView.addObject("video", video);
        }
        return modelAndView;
    }



    @Autowired
    public void setArticleManager(ArticleManager articleManager) {
        this.articleManager = articleManager;
    }

    @Autowired
    public void setVideoManager(VideoManager videoManager) {
        this.videoManager = videoManager;
    }
}
