package com.zylear.publish.web.service.pubilsh.impl;

import com.zylear.publish.web.dao.mybatis.publish.LolArticleMapper;
import com.zylear.publish.web.domain.LolArticle;
import com.zylear.publish.web.service.pubilsh.LolArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by xiezongyu on 2018/8/8.
 */
@Component
public class LolArticleServiceImpl implements LolArticleService {
    @Autowired
    private LolArticleMapper lolArticleMapper;


    @Override
    public LolArticle selectByPrimaryKey(Integer id) {
        return lolArticleMapper.selectByPrimaryKey(id);
    }

    @Override
    public void insert(LolArticle article) {
        lolArticleMapper.insert(article);
    }

    @Override
    public LolArticle findBySourceTypeAndTitle(Integer sourceType, String title) {
        return lolArticleMapper.findBySourceTypeAndTitle(sourceType,title);
    }
}
