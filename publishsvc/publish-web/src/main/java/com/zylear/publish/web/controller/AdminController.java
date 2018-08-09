package com.zylear.publish.web.controller;

import com.zylear.publish.web.bean.BasePageResult;
import com.zylear.publish.web.controller.bean.PostBean;
import com.zylear.publish.web.manager.SubmitManager;
import com.zylear.publish.web.service.pubilsh.OwnBlogService;
import com.zylear.publish.web.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
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
    private SubmitManager submitManager;

    @RequestMapping("/create-blog")
    public ModelAndView createBlog() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/create-blog");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping("/sure-create-blog")
    public BasePageResult createBlog(@RequestParam("content") String content,
                                     @RequestParam("title") String title) {

        ownBlogService.insert(title, content);
        return BasePageResult.SUCCESS_RESPONSE;
    }

    @ResponseBody
    @RequestMapping(value = "/submit-article", produces = "application/json;charset=utf-8;")
    public BasePageResult submit(@RequestBody PostBean postBean) {


//        PostBean postBean = JsonUtil.getObjectFromJson(string, PostBean.class);
        submitManager.submitArticle(postBean);
        return BasePageResult.SUCCESS_RESPONSE;
    }

    @Autowired
    public void setOwnBlogService(OwnBlogService ownBlogService) {
        this.ownBlogService = ownBlogService;
    }

    @Autowired
    public void setSubmitManager(SubmitManager submitManager) {
        this.submitManager = submitManager;
    }
}
