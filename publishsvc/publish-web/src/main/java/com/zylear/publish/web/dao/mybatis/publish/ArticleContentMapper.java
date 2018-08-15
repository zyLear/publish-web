package com.zylear.publish.web.dao.mybatis.publish;

import com.zylear.publish.web.domain.publish.ArticleContent;
import com.zylear.publish.web.domain.publish.ArticleContentWithBLOBs;

public interface ArticleContentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ArticleContentWithBLOBs record);

    int insertSelective(ArticleContentWithBLOBs record);

    ArticleContentWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ArticleContentWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ArticleContentWithBLOBs record);

    int updateByPrimaryKey(ArticleContent record);
}