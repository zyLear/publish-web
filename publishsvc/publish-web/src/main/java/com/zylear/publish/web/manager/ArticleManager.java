package com.zylear.publish.web.manager;

import com.zylear.publish.web.bean.ArticleViewBean;
import com.zylear.publish.web.domain.Article;
import com.zylear.publish.web.domain.ArticleContent;
import com.zylear.publish.web.domain.ArticleContentWithBLOBs;
import com.zylear.publish.web.service.pubilsh.ArticleContentService;
import com.zylear.publish.web.service.pubilsh.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by xiezongyu on 2018/8/5.
 */
@Component
public class ArticleManager {

    private ArticleService articleService;
    private ArticleContentService articleContentService;

    public ArticleViewBean findContent(Integer id) {
        Article article = articleService.selectByPrimaryKey(id);
        ArticleContentWithBLOBs articleContent;
        if (article != null) {
            articleContent = articleContentService.selectByPrimaryKey(article.getContentId());
        }else {
            return null;
        }
        ArticleViewBean articleViewBean = new ArticleViewBean();
        articleViewBean.setTitle(article.getTitle());
        articleViewBean.setCss(articleContent.getCss());
        articleViewBean.setContent(articleContent.getContent());
        return articleViewBean;
    }

    @Autowired
    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }

    @Autowired
    public void setArticleContentService(ArticleContentService articleContentService) {
        this.articleContentService = articleContentService;
    }
}
