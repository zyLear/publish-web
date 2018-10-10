package com.zylear.publish.web.controller.admin;

import com.zylear.publish.web.bean.BasePageResponse;
import com.zylear.publish.web.bean.GobangResponse;
import com.zylear.publish.web.bean.SubmitResponse;
import com.zylear.publish.web.controller.bean.ArticlePostBean;
import com.zylear.publish.web.controller.bean.VideoPostBean;
import com.zylear.publish.web.domain.blokusgame.GobangOptimize;
import com.zylear.publish.web.manager.SubmitManager;
import com.zylear.publish.web.service.blokusgame.GobangOptimizeService;
import com.zylear.publish.web.service.pubilsh.OwnBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by xiezongyu on 2018/8/4.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    private OwnBlogService ownBlogService;
    private SubmitManager submitManager;
    private GobangOptimizeService gobangOptimizeService;

    @RequestMapping("/create-blog")
    public ModelAndView createBlog() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/create-blog");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping("/sure-create-blog")
    public BasePageResponse createBlog(@RequestParam("content") String content,
                                       @RequestParam("title") String title) {

        ownBlogService.insert(title, content);
        return BasePageResponse.SUCCESS_RESPONSE;
    }

    @ResponseBody
    @RequestMapping(value = "/submit-article", produces = "application/json;charset=utf-8;")
    public BasePageResponse submit(@RequestBody ArticlePostBean articlePostBean) {

        Integer id = submitManager.submitArticle(articlePostBean);
        if (id == -1) {
            return new SubmitResponse(id, BasePageResponse.ERROR_RESPONSE);
        } else {
            return new SubmitResponse(id, BasePageResponse.SUCCESS_RESPONSE);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/submit-video", produces = "application/json;charset=utf-8;")
    public BasePageResponse submitVideo(@RequestBody VideoPostBean videoPostBean) {


        Integer id = submitManager.submitVideo(videoPostBean);
        if (id == -1) {
            return new SubmitResponse(id, BasePageResponse.ERROR_RESPONSE);
        } else {
            return new SubmitResponse(id, BasePageResponse.SUCCESS_RESPONSE);
        }
    }


    @RequestMapping(value = "/gobang/submit", produces = "application/json;charset=utf-8;")
    @ResponseBody
    public BasePageResponse submit(@RequestBody GobangOptimize gobangOptimize) {
        gobangOptimizeService.insert(gobangOptimize);
        return BasePageResponse.SUCCESS_RESPONSE;
    }


    @RequestMapping(value = "/gobang/all-record", produces = "application/json;charset=utf-8;")
    @ResponseBody
    public GobangResponse sync() {
        GobangResponse gobangResponse = new GobangResponse();
        gobangResponse.setList(gobangOptimizeService.findAll());
        return gobangResponse;
    }





    @Autowired
    public void setOwnBlogService(OwnBlogService ownBlogService) {
        this.ownBlogService = ownBlogService;
    }

    @Autowired
    public void setSubmitManager(SubmitManager submitManager) {
        this.submitManager = submitManager;
    }

    @Autowired
    public void setGobangOptimizeService(GobangOptimizeService gobangOptimizeService) {
        this.gobangOptimizeService = gobangOptimizeService;
    }
}