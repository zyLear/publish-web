package com.zylear.publish.web.service.pubilsh;

import com.zylear.publish.web.bean.PageParam;
import com.zylear.publish.web.domain.OwnBlog;

import java.util.List;

/**
 * Created by xiezongyu on 2018/8/4.
 */
public interface OwnBlogService {

    OwnBlog selectByPrimaryKey(Integer blogId);

    void insert(String title, String content);

    Integer maxId();

    List<OwnBlog> findBlogsByPageParam(PageParam pageParam);

    Integer count();

}
