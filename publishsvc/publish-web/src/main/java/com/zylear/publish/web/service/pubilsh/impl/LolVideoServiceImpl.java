package com.zylear.publish.web.service.pubilsh.impl;

import com.zylear.publish.web.bean.PageParam;
import com.zylear.publish.web.dao.mybatis.publish.LolVideoMapper;
import com.zylear.publish.web.domain.publish.LolVideo;
import com.zylear.publish.web.service.pubilsh.LolVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by xiezongyu on 2018/8/12.
 */
@Component
public class LolVideoServiceImpl implements LolVideoService {

    @Autowired
    private LolVideoMapper lolVideoMapper;

    @Override
    public void insert(LolVideo video) {
        lolVideoMapper.insert(video);
    }

    @Override
    public LolVideo findBySourceTypeAndTitle(Integer sourceType, String title) {
        return lolVideoMapper.findBySourceTypeAndTitle(sourceType, title);
    }

    @Override
    public LolVideo selectByPrimaryKey(Integer id) {
        return lolVideoMapper.selectByPrimaryKey(id);
    }

    @Override
    public Integer count() {
        return lolVideoMapper.count();
    }

    @Override
    public List<LolVideo> findLolVideosByPageParam(PageParam pageParam) {
        return lolVideoMapper.findLolVideosByPageParam(pageParam);
    }
}
