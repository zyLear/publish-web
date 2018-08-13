package com.zylear.publish.web.bean.viewbean.article;

import com.zylear.publish.web.bean.viewbean.base.PageButtonViewBean;

import java.util.List;

/**
 * Created by xiezongyu on 2018/8/9.
 */
public class ArticleListViewBean {

    private List<PageButtonViewBean> pageButtons;

    private List<ArticleViewBean> articles;


    private Integer tailPage;

    public List<PageButtonViewBean> getPageButtons() {
        return pageButtons;
    }

    public void setPageButtons(List<PageButtonViewBean> pageButtons) {
        this.pageButtons = pageButtons;
    }

    public List<ArticleViewBean> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticleViewBean> articles) {
        this.articles = articles;
    }

    public Integer getTailPage() {
        return tailPage;
    }

    public void setTailPage(Integer tailPage) {
        this.tailPage = tailPage;
    }
}
