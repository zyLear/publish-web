package com.zylear.publish.web.manager;

import com.zylear.publish.web.bean.ArticleViewBean;
import com.zylear.publish.web.domain.PubgArticle;
import com.zylear.publish.web.domain.ArticleContentWithBLOBs;
import com.zylear.publish.web.service.pubilsh.ArticleContentService;
import com.zylear.publish.web.service.pubilsh.PubgArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by xiezongyu on 2018/8/5.
 */
@Component
public class ArticleManager {

    private PubgArticleService pubgArticleService;
    private ArticleContentService articleContentService;

    public ArticleViewBean findPubgArticleContent(Integer id) {
        PubgArticle pubgArticle = pubgArticleService.selectByPrimaryKey(id);
        ArticleContentWithBLOBs articleContent;
        if (pubgArticle != null) {
            articleContent = articleContentService.selectByPrimaryKey(pubgArticle.getContentId());
        }else {
            return null;
        }
        ArticleViewBean articleViewBean = new ArticleViewBean();
        articleViewBean.setTitle(pubgArticle.getTitle());
        articleViewBean.setCss(articleContent.getCss());
        articleViewBean.setContent(articleContent.getContent());
        return articleViewBean;
    }

    @Autowired
    public void setPubgArticleService(PubgArticleService pubgArticleService) {
        this.pubgArticleService = pubgArticleService;
    }

    @Autowired
    public void setArticleContentService(ArticleContentService articleContentService) {
        this.articleContentService = articleContentService;
    }
}
