package com.zylear.publish.web.controller;

import com.zylear.publish.web.bean.ArticleViewBean;
import com.zylear.publish.web.manager.ArticleManager;
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

    @Autowired
    public void setArticleManager(ArticleManager articleManager) {
        this.articleManager = articleManager;
    }
}
