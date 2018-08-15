package com.zylear.publish.web.service.blokusgame.impl;

import com.zylear.publish.web.dao.mybatis.blokusgame.PlayerGameLogMapper;
import com.zylear.publish.web.domain.blokusgame.PlayerGameLog;
import com.zylear.publish.web.service.blokusgame.PlayerGameLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by xiezongyu on 2018/8/15.
 */
@Component
public class PlayerGameLogServiceImpl implements PlayerGameLogService {
    @Autowired
    private PlayerGameLogMapper playerGameLogMapper;

    @Override
    public List<PlayerGameLog> findByAccount(String account) {
        return playerGameLogMapper.findByAccount(account);
    }

    @Override
    public List<PlayerGameLog> findByGameLogId(Integer gameLogId) {
        return playerGameLogMapper.findByGameLogId(gameLogId);
    }
}
