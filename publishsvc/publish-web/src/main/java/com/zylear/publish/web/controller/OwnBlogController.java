package com.zylear.publish.web.controller;

import com.zylear.publish.web.bean.PageParam;
import com.zylear.publish.web.bean.viewbean.article.ArticleListViewBean;
import com.zylear.publish.web.domain.OwnBlog;
import com.zylear.publish.web.manager.OwnBlogManager;
import com.zylear.publish.web.manager.converter.ViewBeanConverter;
import com.zylear.publish.web.service.pubilsh.OwnBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by xiezongyu on 2018/8/4.
 */
@Controller
@RequestMapping("/blog")
public class OwnBlogController {

    private OwnBlogService ownBlogService;

    private OwnBlogManager ownBlogManager;
    private Integer DEFAULT_PAGE_SIZE = 20;
    private Integer DEFAULT_PAGE_RANGE = 3;


    @RequestMapping("/blog-list/{page}.html")
    public ModelAndView showArticleList(@PathVariable("page") String page) {
        Integer pageIndex = Integer.valueOf(page);
        PageParam pageParam = new PageParam(DEFAULT_PAGE_SIZE, (pageIndex - 1) * DEFAULT_PAGE_SIZE);
        ArticleListViewBean articleListViewBean = ownBlogManager.findArticleListViewBean(DEFAULT_PAGE_RANGE, pageIndex, pageParam);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("blog/blog-list");
        if (articleListViewBean != null) {
            modelAndView.addObject("articleListViewBean", articleListViewBean);
        }
        return modelAndView;
    }


    @RequestMapping("/show-blog/{blogId}.html")
    public ModelAndView showBlog(@PathVariable("blogId") String blogId) {
        Integer id = Integer.valueOf(blogId);
        OwnBlog ownBlog = ownBlogService.selectByPrimaryKey(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("blog/show-blog");
        if (ownBlog != null) {
            modelAndView.addObject("blog", ViewBeanConverter.toArticleViewBean(ownBlog));
        }
        return modelAndView;
    }

    @Autowired
    public void setOwnBlogService(OwnBlogService ownBlogService) {
        this.ownBlogService = ownBlogService;
    }

    @Autowired
    public void setOwnBlogManager(OwnBlogManager ownBlogManager) {
        this.ownBlogManager = ownBlogManager;
    }
}
