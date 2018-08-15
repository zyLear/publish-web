package com.zylear.publish.web.manager;

import com.zylear.publish.web.config.DataSourcePublishConfig;
import com.zylear.publish.web.controller.bean.ArticlePostBean;
import com.zylear.publish.web.controller.bean.VideoPostBean;
import com.zylear.publish.web.domain.publish.ArticleContentWithBLOBs;
import com.zylear.publish.web.domain.publish.LolArticle;
import com.zylear.publish.web.domain.publish.LolVideo;
import com.zylear.publish.web.domain.publish.PubgArticle;
import com.zylear.publish.web.enums.SourceType;
import com.zylear.publish.web.enums.SpiderCategory;
import com.zylear.publish.web.enums.VideoType;
import com.zylear.publish.web.service.pubilsh.*;
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
    private LolVideoService lolVideoService;
    private PubgVideoService pubgVideoService;


    public Integer submitArticle(ArticlePostBean articlePostBean) {
        if (SpiderCategory.lol.getValue().equals(articlePostBean.getSpiderCategory())) {
            return submitLolArticle(articlePostBean);
        } else if (SpiderCategory.pubg.getValue().equals(articlePostBean.getSpiderCategory())) {
            return submitPubgArticle(articlePostBean);
        }
        return -1;
    }


    public Integer submitVideo(VideoPostBean videoPostBean) {
        if (SpiderCategory.lol.getValue().equals(videoPostBean.getVideoCategory())) {
            return submitLolVideo(videoPostBean);
        }
        return -1;
    }

    @Transactional(DataSourcePublishConfig.TX_MANAGER)
    private Integer submitLolVideo(VideoPostBean videoPostBean) {
        LolVideo exist = lolVideoService.findBySourceTypeAndTitle(videoPostBean.getSourceType(), videoPostBean.getTitle());
        if (exist != null) {
            logger.info("video exist. sourceType:{} title:{}", SourceType.valueOf(videoPostBean.getSourceType()), videoPostBean.getTitle());
            return -1;
        }
        Date date = new Date();
        ArticleContentWithBLOBs articleContent = null;
        if (VideoType.comtent_html.getValue().equals(videoPostBean.getVideoType())) {
            articleContent = new ArticleContentWithBLOBs();
            articleContent.setCss(videoPostBean.getCss());
            articleContent.setContent(videoPostBean.getContent());
            articleContent.setIsDeleted(false);
            articleContent.setCreateTime(date);
            articleContent.setLastUpdateTime(date);
            articleContentService.insert(articleContent);
        }


        LolVideo video = new LolVideo();
        video.setSourceType(videoPostBean.getSourceType());
        video.setTitle(videoPostBean.getTitle());
        video.setVideoCategory(videoPostBean.getVideoCategory());
        video.setPostTime(new Date(videoPostBean.getPostTime()));
        video.setSourceUrl(videoPostBean.getSourceUrl());
        video.setCoverImgUrl(videoPostBean.getCoverImgUrl());
        video.setFlashvars(videoPostBean.getFlashvars());
        video.setVideoType(videoPostBean.getVideoType());
        video.setVideoSource(videoPostBean.getVideoSource());
        video.setPageView(0);
        video.setIsDeleted(false);
        video.setCreateTime(date);
        video.setLastUpdateTime(date);

        if (articleContent != null) {
            video.setContentId(articleContent.getId());
        }

        lolVideoService.insert(video);
        logger.info("save duowan lol video:{}", videoPostBean);
        return video.getId();
    }

    @Transactional(DataSourcePublishConfig.TX_MANAGER)
    private Integer submitLolArticle(ArticlePostBean articlePostBean) {
        LolArticle exist = lolArticleService.findBySourceTypeAndTitle(articlePostBean.getSourceType(), articlePostBean.getTitle());
        if (exist != null) {
            logger.info("article exist. sourceType:{} title:{}", SourceType.valueOf(articlePostBean.getSourceType()), articlePostBean.getTitle());
            return -1;
        }

        Date date = new Date();
        ArticleContentWithBLOBs articleContent = new ArticleContentWithBLOBs();
        articleContent.setCss(articlePostBean.getCss());
        articleContent.setContent(articlePostBean.getContent());
        articleContent.setIsDeleted(false);
        articleContent.setCreateTime(date);
        articleContent.setLastUpdateTime(date);
        articleContentService.insert(articleContent);

        LolArticle article = new LolArticle();
        article.setArticleCategory(articlePostBean.getSpiderCategory());
        article.setSourceType(articlePostBean.getSourceType());
        article.setSourceUrl(articlePostBean.getSourceUrl());
        article.setTitle(articlePostBean.getTitle());
        article.setPostTime(new Date(articlePostBean.getPostTime()));
        article.setContentId(articleContent.getId());
        article.setPageView(0);
        article.setIsDeleted(false);
        article.setCreateTime(date);
        article.setLastUpdateTime(date);
        lolArticleService.insert(article);
        logger.info("save lol article:{}", articlePostBean);
        return article.getId();
    }

    @Transactional(DataSourcePublishConfig.TX_MANAGER)
    private Integer submitPubgArticle(ArticlePostBean articlePostBean) {
        PubgArticle exist = pubgArticleService.findBySourceTypeAndTitle(articlePostBean.getSourceType(), articlePostBean.getTitle());
        if (exist != null) {
            logger.info("article exist. sourceType:{} title:{}", SourceType.valueOf(articlePostBean.getSourceType()), articlePostBean.getTitle());
            return -1;
        }

        Date date = new Date();
        ArticleContentWithBLOBs articleContent = new ArticleContentWithBLOBs();
        articleContent.setCss(articlePostBean.getCss());
        articleContent.setContent(articlePostBean.getContent());
        articleContent.setIsDeleted(false);
        articleContent.setCreateTime(date);
        articleContent.setLastUpdateTime(date);
        articleContentService.insert(articleContent);

        PubgArticle article = new PubgArticle();
        article.setArticleCategory(articlePostBean.getSpiderCategory());
        article.setSourceType(articlePostBean.getSourceType());
        article.setSourceUrl(articlePostBean.getSourceUrl());
        article.setTitle(articlePostBean.getTitle());
        article.setPostTime(new Date(articlePostBean.getPostTime()));
        article.setContentId(articleContent.getId());
        article.setPageView(0);
        article.setIsDeleted(false);
        article.setCreateTime(date);
        article.setLastUpdateTime(date);
        pubgArticleService.insert(article);
        logger.info("save pubg article:{}", articlePostBean);
        return article.getId();
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

    @Autowired
    public void setLolVideoService(LolVideoService lolVideoService) {
        this.lolVideoService = lolVideoService;
    }

    @Autowired
    public void setPubgVideoService(PubgVideoService pubgVideoService) {
        this.pubgVideoService = pubgVideoService;
    }
}
