package com.zylear.publish.web.controller;

import com.zylear.publish.web.bean.BasePageResult;
import com.zylear.publish.web.service.pubilsh.OwnBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by xiezongyu on 2018/8/4.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    private OwnBlogService ownBlogService;

    @RequestMapping("/create-blog")
    public ModelAndView createBlog() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/create-blog");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping("/sure-create-blog")
    public BasePageResult createBlog(@RequestParam("content") String content,
                                     @RequestParam("title") String title,
                                     HttpServletRequest request) {
//        String content = request.getParameter("content");
//        String title = request.getParameter("title");
        ownBlogService.insert(title, content);
        return BasePageResult.SUCCESS_RESPONSE;
    }

    @Autowired
    public void setOwnBlogService(OwnBlogService ownBlogService) {
        this.ownBlogService = ownBlogService;
    }
}
