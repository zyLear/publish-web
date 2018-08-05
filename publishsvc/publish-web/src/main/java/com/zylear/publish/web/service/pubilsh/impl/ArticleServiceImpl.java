package com.zylear.publish.web.service.pubilsh.impl;

import com.zylear.publish.web.dao.mybatis.publish.ArticleMapper;
import com.zylear.publish.web.domain.Article;
import com.zylear.publish.web.manager.ArticleManager;
import com.zylear.publish.web.service.pubilsh.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by xiezongyu on 2018/8/5.
 */
@Component
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    public ArticleMapper articleMapper;

    @Override
    public Article selectByPrimaryKey(Integer id) {
        return articleMapper.selectByPrimaryKey(id);
    }
}
