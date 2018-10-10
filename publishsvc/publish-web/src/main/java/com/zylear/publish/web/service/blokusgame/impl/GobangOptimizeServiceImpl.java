package com.zylear.publish.web.service.blokusgame.impl;

import com.zylear.publish.web.dao.mybatis.blokusgame.GobangOptimizeMapper;
import com.zylear.publish.web.domain.blokusgame.GobangOptimize;
import com.zylear.publish.web.service.blokusgame.GobangOptimizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by xiezongyu on 2018/9/9.
 */
@Component
public class GobangOptimizeServiceImpl implements GobangOptimizeService {

    @Autowired
    private GobangOptimizeMapper gobangOptimizeMapper;


    @Override
    public List<GobangOptimize> findAll() {
        return gobangOptimizeMapper.findAll();
    }

    @Override
    public void upsert(GobangOptimize gobangOptimize) {
        GobangOptimize oldChess = gobangOptimizeMapper.findByAllChess(gobangOptimize.getAllChess());
        if (oldChess == null) {
            gobangOptimizeMapper.insert(gobangOptimize);
        }else {
            gobangOptimize.setId(oldChess.getId());
            gobangOptimizeMapper.updateByPrimaryKey(gobangOptimize);
        }
    }
}
