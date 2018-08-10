package com.zylear.publish.web.manager;

import com.zylear.publish.web.bean.PageParam;
import com.zylear.publish.web.bean.viewbean.ArticleListViewBean;
import com.zylear.publish.web.bean.viewbean.ArticleViewBean;
import com.zylear.publish.web.bean.viewbean.PageButtonViewBean;
import com.zylear.publish.web.domain.LolArticle;
import com.zylear.publish.web.domain.PubgArticle;
import com.zylear.publish.web.domain.ArticleContentWithBLOBs;
import com.zylear.publish.web.service.pubilsh.ArticleContentService;
import com.zylear.publish.web.service.pubilsh.LolArticleService;
import com.zylear.publish.web.service.pubilsh.PubgArticleService;
import com.zylear.publish.web.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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
        PubgArticle pubgArticle = pubgArticleService.selectByPrimaryKey(id);
        ArticleContentWithBLOBs articleContent;
        if (pubgArticle != null) {
            articleContent = articleContentService.selectByPrimaryKey(pubgArticle.getContentId());
        } else {
            return null;
        }
        ArticleViewBean articleViewBean = new ArticleViewBean();
        articleViewBean.setTitle(pubgArticle.getTitle());
        articleViewBean.setCss(articleContent.getCss());
        articleViewBean.setContent(articleContent.getContent());
        return articleViewBean;
    }

    public ArticleViewBean findLolArticleContent(Integer id) {
        LolArticle lolArticle = lolArticleService.selectByPrimaryKey(id);
        ArticleContentWithBLOBs articleContent;
        if (lolArticle != null) {
            articleContent = articleContentService.selectByPrimaryKey(lolArticle.getContentId());
        } else {
            return null;
        }
        ArticleViewBean articleViewBean = new ArticleViewBean();
        articleViewBean.setTitle(lolArticle.getTitle());
        articleViewBean.setCss(articleContent.getCss());
        articleViewBean.setContent(articleContent.getContent());
        return articleViewBean;
    }


    public ArticleListViewBean findArticleListViewBean(Integer pageRange, Integer pageIndex, PageParam pageParam) {
        Integer maxId = lolArticleService.maxId();
        Integer maxPage = (int) Math.ceil(maxId / (double) pageParam.getPageSize());
        List<LolArticle> articles = lolArticleService.findLolArticlesByPageParam(pageParam);
        ArticleListViewBean articleListViewBean = new ArticleListViewBean();
        articleListViewBean.setTailPage(maxPage);
        articleListViewBean.setArticles(toArticleViewBean(articles));
        articleListViewBean.setPageButtons(toPageButtonViewBean(pageRange, pageIndex, maxPage));
        return articleListViewBean;
    }

    private List<PageButtonViewBean> toPageButtonViewBean(Integer pageRange, Integer pageIndex, Integer maxPage) {


        Integer lowPage;
        Integer topPage;

        if (pageIndex <= pageRange) {
            lowPage = 1;
            if (2 * pageRange + 1 > maxPage) {
                topPage = maxPage;
            } else {
                topPage = 2 * pageRange + 1;
            }
        } else if (pageIndex + pageRange > maxPage) {

            if (maxPage - 2 * pageRange - 1 > 0) {
                lowPage = maxPage - 2 * pageRange;
            } else {
                lowPage = 1;
            }
            topPage = maxPage;
        } else {
            if (pageIndex - pageRange > 0) {
                lowPage = pageIndex - pageRange;
            } else {
                lowPage = 1;
            }
            if (pageIndex + pageRange > maxPage) {
                topPage = maxPage;
            } else {
                topPage = pageIndex + pageRange;
            }
        }
        List<PageButtonViewBean> pageButtonViewBeans = new ArrayList<>();
        for (int i = lowPage; i <= topPage; i++) {
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

    private List<ArticleViewBean> toArticleViewBean(List<LolArticle> articles) {
        List<ArticleViewBean> articleViewBeans = new ArrayList<>(articles.size());
        for (LolArticle article : articles) {
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
