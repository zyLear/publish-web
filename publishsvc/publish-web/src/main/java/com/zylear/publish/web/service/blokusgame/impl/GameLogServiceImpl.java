package com.zylear.publish.web.service.blokusgame.impl;

import com.zylear.publish.web.dao.mybatis.blokusgame.GameLogMapper;
import com.zylear.publish.web.domain.blokusgame.GameLog;
import com.zylear.publish.web.service.blokusgame.GameLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by xiezongyu on 2018/8/15.
 */
@Component
public class GameLogServiceImpl implements GameLogService {
    @Autowired
    private GameLogMapper gameLogMapper;

    @Override
    public GameLog findById(Integer gameLogId) {
        return gameLogMapper.selectByPrimaryKey(gameLogId);
    }
}
