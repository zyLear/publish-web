package com.zylear.publish.web.dao.mybatis.publish;

import com.zylear.publish.web.domain.OwnBlog;

public interface OwnBlogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OwnBlog record);

    int insertSelective(OwnBlog record);

    OwnBlog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OwnBlog record);

    int updateByPrimaryKeyWithBLOBs(OwnBlog record);

    int updateByPrimaryKey(OwnBlog record);
}