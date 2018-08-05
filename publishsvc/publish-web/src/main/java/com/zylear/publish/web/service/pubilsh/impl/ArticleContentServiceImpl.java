package com.zylear.publish.web.service.pubilsh.impl;

import com.zylear.publish.web.dao.mybatis.publish.ArticleContentMapper;
import com.zylear.publish.web.domain.ArticleContentWithBLOBs;
import com.zylear.publish.web.service.pubilsh.ArticleContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by xiezongyu on 2018/8/5.
 */
@Component
public class ArticleContentServiceImpl implements ArticleContentService {

    @Autowired
    private ArticleContentMapper articleContentMapper;

    @Override
    public ArticleContentWithBLOBs selectByPrimaryKey(Integer contentId) {
        return articleContentMapper.selectByPrimaryKey(contentId);
    }
}
