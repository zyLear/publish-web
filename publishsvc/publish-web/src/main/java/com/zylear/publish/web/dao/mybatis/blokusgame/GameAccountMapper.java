package com.zylear.publish.web.dao.mybatis.blokusgame;

import com.zylear.publish.web.domain.blokusgame.GameAccount;
import org.apache.ibatis.annotations.Param;

public interface GameAccountMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GameAccount record);

    int insertSelective(GameAccount record);

    GameAccount selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GameAccount record);

    int updateByPrimaryKey(GameAccount record);


    GameAccount findByAccount(@Param("account") String account);
}