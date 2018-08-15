package com.zylear.publish.web.dao.mybatis.publish;

import com.zylear.publish.web.bean.PageParam;
import com.zylear.publish.web.domain.publish.OwnBlog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OwnBlogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OwnBlog record);

    int insertSelective(OwnBlog record);

    OwnBlog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OwnBlog record);

    int updateByPrimaryKeyWithBLOBs(OwnBlog record);

    int updateByPrimaryKey(OwnBlog record);

    Integer maxId();

    List<OwnBlog> findBlogsByPageParam(@Param("pageParam") PageParam pageParam);

    Integer count();
}