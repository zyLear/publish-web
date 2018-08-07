package com.zylear.publish.web.service.pubilsh.impl;

import com.zylear.publish.web.dao.mybatis.publish.PubgArticleMapper;
import com.zylear.publish.web.domain.PubgArticle;
import com.zylear.publish.web.service.pubilsh.PubgArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
}
