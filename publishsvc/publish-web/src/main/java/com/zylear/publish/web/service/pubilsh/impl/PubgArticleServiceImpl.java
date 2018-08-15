package com.zylear.publish.web.service.pubilsh.impl;

import com.zylear.publish.web.bean.PageParam;
import com.zylear.publish.web.dao.mybatis.publish.PubgArticleMapper;
import com.zylear.publish.web.domain.publish.PubgArticle;
import com.zylear.publish.web.service.pubilsh.PubgArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by xiezongyu on 2018/8/5.
 */
@Component
public class PubgArticleServiceImpl implements PubgArticleService {
    @Autowired
    public PubgArticleMapper pubgArticleMapper;

    @Override
    public PubgArticle selectByPrimaryKey(Integer id) {
        return pubgArticleMapper.selectByPrimaryKey(id);
    }

    @Override
    public PubgArticle findBySourceTypeAndTitle(Integer sourceType, String title) {
        return pubgArticleMapper.findBySourceTypeAndTitle(sourceType, title);
    }

    @Override
    public void insert(PubgArticle article) {
        pubgArticleMapper.insert(article);
    }

    @Override
    public Integer count() {
        return pubgArticleMapper.count();
    }

    @Override
    public List<PubgArticle> findPubgArticlesByPageParam(PageParam pageParam) {
        return pubgArticleMapper.findPubgArticlesByPageParam(pageParam);
    }

}
