package com.zylear.publish.web.dao.mybatis.blokusgame;

import com.zylear.publish.web.domain.blokusgame.GameLog;

public interface GameLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GameLog record);

    int insertSelective(GameLog record);

    GameLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GameLog record);

    int updateByPrimaryKey(GameLog record);
}