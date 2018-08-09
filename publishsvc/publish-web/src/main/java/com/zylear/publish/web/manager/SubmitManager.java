package com.zylear.publish.web.manager;

import com.zylear.publish.web.config.DataSourcePublishConfig;
import com.zylear.publish.web.controller.bean.PostBean;
import com.zylear.publish.web.domain.ArticleContentWithBLOBs;
import com.zylear.publish.web.domain.LolArticle;
import com.zylear.publish.web.enums.SourceType;
import com.zylear.publish.web.enums.SpiderCategory;
import com.zylear.publish.web.service.pubilsh.ArticleContentService;
import com.zylear.publish.web.service.pubilsh.LolArticleService;
import com.zylear.publish.web.service.pubilsh.PubgArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by xiezongyu on 2018/8/8.
 */
@Component
public class SubmitManager {


    private static final Logger logger = LoggerFactory.getLogger(SubmitManager.class);

    private PubgArticleService pubgArticleService;
    private LolArticleService lolArticleService;
    private ArticleContentService articleContentService;

    @Transactional(DataSourcePublishConfig.TX_MANAGER)
    public void submitArticle(PostBean postBean) {


        if (SpiderCategory.lol.getValue().equals(postBean.getSpiderCategory())) {
            LolArticle exist = lolArticleService.findBySourceTypeAndTitle(SourceType.duowan.getValue(), postBean.getTitle());
            if (exist != null) {
                logger.info("article exist. sourceType:{} title:{}", SourceType.duowan.getValue(), postBean.getTitle());
                return;
            }

            Date date = new Date();
            ArticleContentWithBLOBs articleContent = new ArticleContentWithBLOBs();
            articleContent.setCss(postBean.getCss());
            articleContent.setContent(postBean.getContent());
            articleContent.setIsDeleted(false);
            articleContent.setCreateTime(date);
            articleContent.setLastUpdateTime(date);
            articleContentService.insert(articleContent);

            LolArticle article = new LolArticle();
            article.setArticleCategory(postBean.getSpiderCategory());
            article.setSourceType(postBean.getSourceType());
            article.setSourceUrl(postBean.getSourceUrl());
            article.setTitle(postBean.getTitle());
            article.setPostTime(null);
            article.setContentId(articleContent.getId());
            article.setPageView(0);
            article.setIsDeleted(false);
            article.setCreateTime(date);
            article.setLastUpdateTime(date);
            lolArticleService.insert(article);
            logger.info("save duowan article:{}", postBean);
        }


    }


    @Autowired
    public void setPubgArticleService(PubgArticleService pubgArticleService) {
        this.pubgArticleService = pubgArticleService;
    }

    @Autowired
    public void setLolArticleService(LolArticleService lolArticleService) {
        this.lolArticleService = lolArticleService;
    }

    @Autowired
    public void setArticleContentService(ArticleContentService articleContentService) {
        this.articleContentService = articleContentService;
    }
}
