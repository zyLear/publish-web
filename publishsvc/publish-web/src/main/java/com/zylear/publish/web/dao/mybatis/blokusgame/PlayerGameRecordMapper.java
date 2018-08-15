package com.zylear.publish.web.dao.mybatis.blokusgame;

import com.zylear.publish.web.domain.blokusgame.PlayerGameRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PlayerGameRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PlayerGameRecord record);

    int insertSelective(PlayerGameRecord record);

    PlayerGameRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PlayerGameRecord record);

    int updateByPrimaryKey(PlayerGameRecord record);


    List<PlayerGameRecord> findRanks(@Param("gameType") Integer gameType);

    List<PlayerGameRecord> findRanksByAccount(@Param("account") String account);
}