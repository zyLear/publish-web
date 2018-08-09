package com.zylear.publish.web.bean.viewbean;

import java.util.List;

/**
 * Created by xiezongyu on 2018/8/9.
 */
public class ArticleListViewBean {

    private List<PageButtonViewBean> pagebuttons;

    private List<ArticleViewBean> articles;

    public List<PageButtonViewBean> getPagebuttons() {
        return pagebuttons;
    }

    public void setPagebuttons(List<PageButtonViewBean> pagebuttons) {
        this.pagebuttons = pagebuttons;
    }

    public List<ArticleViewBean> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticleViewBean> articles) {
        this.articles = articles;
    }
}
