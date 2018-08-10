package com.zylear.publish.web.service.pubilsh.impl;

import com.zylear.publish.web.bean.PageParam;
import com.zylear.publish.web.dao.mybatis.publish.OwnBlogMapper;
import com.zylear.publish.web.domain.OwnBlog;
import com.zylear.publish.web.service.pubilsh.OwnBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * Created by xiezongyu on 2018/8/4.
 */
@Component
public class OwnBlogServiceImpl implements OwnBlogService {

    private OwnBlogMapper ownBlogMapper;

    @Override
    public OwnBlog selectByPrimaryKey(Integer blogId) {
        return ownBlogMapper.selectByPrimaryKey(blogId);
    }

    @Override
    public void insert(String title, String content) {
        OwnBlog ownBlog = new OwnBlog();
        ownBlog.setTitle(title);
        ownBlog.setContent(content);
        ownBlog.setCategory(1);
        ownBlog.setPageView(0);
        ownBlog.setBlogStatus(1);
        ownBlog.setIsDeleted(false);
        ownBlog.setPostTime(new Date());
        ownBlog.setCreateTime(new Date());
        ownBlog.setLastUpdateTime(new Date());
        ownBlogMapper.insert(ownBlog);
    }

    @Override
    public Integer maxId() {
        Integer maxId = ownBlogMapper.maxId();
        return maxId == null ? 0 : maxId;
    }

    @Override
    public List<OwnBlog> findBlogsByPageParam(PageParam pageParam) {
        return ownBlogMapper.findBlogsByPageParam(pageParam);
    }

    @Autowired
    public void setOwnBlogMapper(OwnBlogMapper ownBlogMapper) {
        this.ownBlogMapper = ownBlogMapper;
    }
}
