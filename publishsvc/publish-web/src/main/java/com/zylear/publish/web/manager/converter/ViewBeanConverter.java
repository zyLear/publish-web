package com.zylear.publish.web.manager.converter;

import com.zylear.publish.web.bean.viewbean.ArticleInterface;
import com.zylear.publish.web.bean.viewbean.ArticleViewBean;
import com.zylear.publish.web.bean.viewbean.PageButtonViewBean;
import com.zylear.publish.web.domain.ArticleContent;
import com.zylear.publish.web.domain.ArticleContentWithBLOBs;
import com.zylear.publish.web.domain.LolArticle;
import com.zylear.publish.web.util.DateUtil;
import com.zylear.publish.web.util.PaginationUtil;
import com.zylear.publish.web.util.PaginationUtil.PaginationResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiezongyu on 2018/8/10.
 */
public class ViewBeanConverter {

//    public static List<PageButtonViewBean> toPageButtonViewBean(Integer pageIndex, PaginationResult paginationResult) {
//
//        List<PageButtonViewBean> pageButtonViewBeans = new ArrayList<>();
//        for (int i = paginationResult.getLowPage(); i <= paginationResult.getTopPage(); i++) {
//            PageButtonViewBean pageButtonViewBean = new PageButtonViewBean();
//            pageButtonViewBean.setPageIndex(i);
//            if (i == pageIndex) {
//                pageButtonViewBean.setActive(true);
//            } else {
//                pageButtonViewBean.setActive(false);
//            }
//            pageButtonViewBeans.add(pageButtonViewBean);
//        }
//        return pageButtonViewBeans;
//    }

    public static List<PageButtonViewBean> toPageButtonViewBean(Integer pageRange, Integer pageIndex, Integer maxPage) {

        PaginationResult paginationResult = PaginationUtil.paging(pageRange, pageIndex, maxPage);

        List<PageButtonViewBean> pageButtonViewBeans = new ArrayList<>();
        for (int i = paginationResult.getLowPage(); i <= paginationResult.getTopPage(); i++) {
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

    public static <T extends ArticleInterface> List<ArticleViewBean> toArticleViewBean(List<T> articles) {
        List<ArticleViewBean> articleViewBeans = new ArrayList<>(articles.size());
        for (ArticleInterface article : articles) {
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




    public static ArticleViewBean toArticleViewBean(ArticleInterface articleInterface, ArticleContentWithBLOBs articleContent) {
        ArticleViewBean articleViewBean = new ArticleViewBean();
        articleViewBean.setTitle(articleInterface.getTitle());
        articleViewBean.setCss(articleContent.getCss());
        articleViewBean.setContent(articleContent.getContent());
        return articleViewBean;
    }




}
