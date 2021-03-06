package com.zylear.publish.web.dao.mybatis.blokusgame;

import com.zylear.publish.web.domain.blokusgame.PlayerGameLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PlayerGameLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PlayerGameLog record);

    int insertSelective(PlayerGameLog record);

    PlayerGameLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PlayerGameLog record);

    int updateByPrimaryKey(PlayerGameLog record);


    List<PlayerGameLog> findByAccount(@Param("account") String account);

    List<PlayerGameLog> findByGameLogId(@Param("gameLogId") Integer gameLogId);
}