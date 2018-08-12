package com.zylear.publish.web.dao.mybatis.publish;

import com.zylear.publish.web.bean.PageParam;
import com.zylear.publish.web.domain.PubgArticle;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PubgArticleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PubgArticle record);

    int insertSelective(PubgArticle record);

    PubgArticle selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PubgArticle record);

    int updateByPrimaryKey(PubgArticle record);


    PubgArticle findBySourceTypeAndTitle(@Param("sourceType") Integer sourceType,
                                         @Param("title") String title);

    Integer count();

    List<PubgArticle> findPubgArticlesByPageParam(@Param("pageParam") PageParam pageParam);
}