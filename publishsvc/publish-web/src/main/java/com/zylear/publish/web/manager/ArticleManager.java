package com.zylear.publish.web.manager;

import com.zylear.publish.web.bean.PageParam;
import com.zylear.publish.web.bean.viewbean.article.ArticleListViewBean;
import com.zylear.publish.web.bean.viewbean.article.ArticleViewBean;
import com.zylear.publish.web.domain.publish.LolArticle;
import com.zylear.publish.web.domain.publish.PubgArticle;
import com.zylear.publish.web.domain.publish.ArticleContentWithBLOBs;
import com.zylear.publish.web.controller.converter.ViewBeanConverter;
import com.zylear.publish.web.service.pubilsh.ArticleContentService;
import com.zylear.publish.web.service.pubilsh.LolArticleService;
import com.zylear.publish.web.service.pubilsh.PubgArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by xiezongyu on 2018/8/5.
 */
@Component
public class ArticleManager {

    private PubgArticleService pubgArticleService;
    private LolArticleService lolArticleService;
    private ArticleContentService articleContentService;

    public ArticleViewBean findPubgArticleContent(Integer id) {
        PubgArticle article = pubgArticleService.selectByPrimaryKey(id);
        ArticleContentWithBLOBs articleContent;
        if (article != null) {
            articleContent = articleContentService.selectByPrimaryKey(article.getContentId());
        } else {
            return null;
        }
        return ViewBeanConverter.toArticleViewBean(article,articleContent);
    }

    public ArticleViewBean findLolArticleContent(Integer id) {
        LolArticle article = lolArticleService.selectByPrimaryKey(id);
        ArticleContentWithBLOBs articleContent;
        if (article != null) {
            articleContent = articleContentService.selectByPrimaryKey(article.getContentId());
        } else {
            return null;
        }
        return ViewBeanConverter.toArticleViewBean(article,articleContent);
    }


    public ArticleListViewBean findLolArticleListViewBean(Integer pageRange, Integer pageIndex, PageParam pageParam) {
        Integer count = lolArticleService.count();
        Integer maxPage = (int) Math.ceil(count / (double) pageParam.getPageSize());
        List<LolArticle> articles = lolArticleService.findLolArticlesByPageParam(pageParam);
        ArticleListViewBean articleListViewBean = new ArticleListViewBean();
        articleListViewBean.setTailPage(maxPage);
        articleListViewBean.setArticles(ViewBeanConverter.toArticleViewBean(articles));
        articleListViewBean.setPageButtons(ViewBeanConverter.toPageButtonViewBean(pageRange, pageIndex, maxPage));
        return articleListViewBean;
    }


    public ArticleListViewBean findPubgArticleListViewBean(Integer pageRange, Integer pageIndex, PageParam pageParam) {

        Integer count = pubgArticleService.count();
        Integer maxPage = (int) Math.ceil(count / (double) pageParam.getPageSize());
        List<PubgArticle> articles = pubgArticleService.findPubgArticlesByPageParam(pageParam);
        ArticleListViewBean articleListViewBean = new ArticleListViewBean();
        articleListViewBean.setTailPage(maxPage);
        articleListViewBean.setArticles(ViewBeanConverter.toArticleViewBean(articles));
        articleListViewBean.setPageButtons(ViewBeanConverter.toPageButtonViewBean(pageRange, pageIndex, maxPage));
        return articleListViewBean;
    }


    @Autowired
    public void setPubgArticleService(PubgArticleService pubgArticleService) {
        this.pubgArticleService = pubgArticleService;
    }

    @Autowired
    public void setArticleContentService(ArticleContentService articleContentService) {
        this.articleContentService = articleContentService;
    }

    @Autowired
    public void setLolArticleService(LolArticleService lolArticleService) {
        this.lolArticleService = lolArticleService;
    }


}
