package com.zylear.publish.web.controller;

import com.zylear.publish.web.domain.OwnBlog;
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

    @RequestMapping("/{blogId}.html")
    public ModelAndView showBlog(@PathVariable("blogId") String blogId) {
        Integer id = Integer.valueOf(blogId);
        OwnBlog ownBlog = ownBlogService.selectByPrimaryKey(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("blog/show-blog");
        if (ownBlog != null) {
            modelAndView.addObject("blog", ownBlog);
        }
        return modelAndView;
    }

    @Autowired
    public void setOwnBlogService(OwnBlogService ownBlogService) {
        this.ownBlogService = ownBlogService;
    }
}
