package com.zylear.publish.web.service.blokusgame;

import com.zylear.publish.web.domain.blokusgame.GobangOptimize;

import java.util.List;

/**
 * Created by xiezongyu on 2018/9/9.
 */
public interface GobangOptimizeService {

    List<GobangOptimize> findAll();

    void insert(GobangOptimize gobangOptimize);
}
