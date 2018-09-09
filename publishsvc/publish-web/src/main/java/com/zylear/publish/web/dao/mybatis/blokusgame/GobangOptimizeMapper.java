package com.zylear.publish.web.dao.mybatis.blokusgame;

import com.zylear.publish.web.domain.blokusgame.GobangOptimize;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GobangOptimizeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GobangOptimize record);

    int insertSelective(GobangOptimize record);

    GobangOptimize selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GobangOptimize record);

    int updateByPrimaryKey(GobangOptimize record);


    GobangOptimize findByAllChess(@Param("allChess") String allChess);

    List<GobangOptimize> findAll();
}