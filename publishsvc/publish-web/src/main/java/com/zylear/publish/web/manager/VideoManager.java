package com.zylear.publish.web.manager;

import com.zylear.publish.web.bean.PageParam;
import com.zylear.publish.web.bean.viewbean.article.ArticleListViewBean;
import com.zylear.publish.web.bean.viewbean.video.VideoListViewBean;
import com.zylear.publish.web.bean.viewbean.video.VideoViewBean;
import com.zylear.publish.web.domain.ArticleContentWithBLOBs;
import com.zylear.publish.web.domain.LolArticle;
import com.zylear.publish.web.domain.LolVideo;
import com.zylear.publish.web.domain.PubgVideo;
import com.zylear.publish.web.enums.VideoType;
import com.zylear.publish.web.manager.converter.ViewBeanConverter;
import com.zylear.publish.web.service.pubilsh.ArticleContentService;
import com.zylear.publish.web.service.pubilsh.LolVideoService;
import com.zylear.publish.web.service.pubilsh.PubgVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by xiezongyu on 2018/8/12.
 */
@Component
public class VideoManager {

    private PubgVideoService pubgVideoService;
    private LolVideoService lolVideoService;
    private ArticleContentService articleContentService;


    public VideoViewBean findLolVideoContent(Integer id) {
        LolVideo video = lolVideoService.selectByPrimaryKey(id);
        ArticleContentWithBLOBs articleContent = null;
        if (video == null) {
            return null;
        }
        if (VideoType.comtent_html.getValue().equals(video.getVideoType())) {
            articleContent = articleContentService.selectByPrimaryKey(video.getContentId());
            if (articleContent == null) {
                return null;
            }
        }
        return ViewBeanConverter.toVideoViewBean(video, articleContent);
    }


    public VideoListViewBean findLolVideoListViewBean(Integer pageRange, Integer pageIndex, PageParam pageParam) {
        Integer count = lolVideoService.count();
        Integer maxPage = (int) Math.ceil(count / (double) pageParam.getPageSize());
        List<LolVideo> videos = lolVideoService.findLolVideosByPageParam(pageParam);
        VideoListViewBean videoListViewBean = new VideoListViewBean();
        videoListViewBean.setTailPage(maxPage);
        videoListViewBean.setVideos(ViewBeanConverter.toVideoViewBean(videos));
        videoListViewBean.setPageButtons(ViewBeanConverter.toPageButtonViewBean(pageRange, pageIndex, maxPage));
        return videoListViewBean;
    }


    @Autowired
    public void setPubgVideoService(PubgVideoService pubgVideoService) {
        this.pubgVideoService = pubgVideoService;
    }

    @Autowired
    public void setLolVideoService(LolVideoService lolVideoService) {
        this.lolVideoService = lolVideoService;
    }

    @Autowired
    public void setArticleContentService(ArticleContentService articleContentService) {
        this.articleContentService = articleContentService;
    }


}
