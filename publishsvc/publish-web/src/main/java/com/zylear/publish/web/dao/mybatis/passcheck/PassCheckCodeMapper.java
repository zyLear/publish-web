package com.zylear.publish.web.dao.mybatis.passcheck;

import com.zylear.publish.web.domain.passcheck.PassCheckCode;

public interface PassCheckCodeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PassCheckCode record);

    int insertSelective(PassCheckCode record);

    PassCheckCode selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PassCheckCode record);

    int updateByPrimaryKeyWithBLOBs(PassCheckCode record);

    int updateByPrimaryKey(PassCheckCode record);


    PassCheckCode findByConfigKey(String configKey);
}