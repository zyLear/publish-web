package com.zylear.publish.web.dao.mybatis.publish;

import com.zylear.publish.web.bean.PageParam;
import com.zylear.publish.web.domain.LolArticle;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LolArticleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LolArticle record);

    int insertSelective(LolArticle record);

    LolArticle selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LolArticle record);

    int updateByPrimaryKey(LolArticle record);


    LolArticle findBySourceTypeAndTitle(@Param("sourceType") Integer sourceType,
                                        @Param("title") String title);

    List<LolArticle> findLolArticlesByPageParam(@Param("pageParam") PageParam pageParam);

    Integer maxId();

    Integer count();
}