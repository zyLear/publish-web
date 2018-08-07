package com.zylear.publish.web.dao.mybatis.publish;

import com.zylear.publish.web.domain.PubgArticle;

public interface PubgArticleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PubgArticle record);

    int insertSelective(PubgArticle record);

    PubgArticle selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PubgArticle record);

    int updateByPrimaryKey(PubgArticle record);
}