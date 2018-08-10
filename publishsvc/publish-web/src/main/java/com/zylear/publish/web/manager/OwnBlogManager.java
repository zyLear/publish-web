package com.zylear.publish.web.manager;

import com.zylear.publish.web.bean.PageParam;
import com.zylear.publish.web.bean.viewbean.ArticleListViewBean;
import com.zylear.publish.web.domain.OwnBlog;
import com.zylear.publish.web.manager.converter.ViewBeanConverter;
import com.zylear.publish.web.service.pubilsh.OwnBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by xiezongyu on 2018/8/10.
 */
@Component
public class OwnBlogManager {

    @Autowired
    private OwnBlogService ownBlogService;

    public ArticleListViewBean findArticleListViewBean(Integer pageRange, Integer pageIndex, PageParam pageParam) {
        Integer maxId = ownBlogService.maxId();
        Integer maxPage = (int) Math.ceil(maxId / (double) pageParam.getPageSize());
        List<OwnBlog> articles = ownBlogService.findBlogsByPageParam(pageParam);
        ArticleListViewBean articleListViewBean = new ArticleListViewBean();
        articleListViewBean.setTailPage(maxPage);
        articleListViewBean.setArticles(ViewBeanConverter.toArticleViewBean(articles));
        articleListViewBean.setPageButtons(ViewBeanConverter.toPageButtonViewBean(pageRange, pageIndex, maxPage));
        return articleListViewBean;
    }

}
