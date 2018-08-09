package com.zylear.publish.web.dao.mybatis.publish;

import com.zylear.publish.web.domain.LolArticle;
import org.apache.ibatis.annotations.Param;

public interface LolArticleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LolArticle record);

    int insertSelective(LolArticle record);

    LolArticle selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LolArticle record);

    int updateByPrimaryKey(LolArticle record);


    LolArticle findBySourceTypeAndTitle(@Param("sourceType") Integer sourceType,
                                        @Param("title") String title);
}