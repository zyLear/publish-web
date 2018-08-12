package com.zylear.publish.web.dao.mybatis.publish;

import com.zylear.publish.web.domain.PubgVideo;

public interface PubgVideoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PubgVideo record);

    int insertSelective(PubgVideo record);

    PubgVideo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PubgVideo record);

    int updateByPrimaryKey(PubgVideo record);
}