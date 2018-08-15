package com.zylear.publish.web.service.blokusgame.impl;

import com.zylear.publish.web.dao.mybatis.blokusgame.PlayerGameRecordMapper;
import com.zylear.publish.web.domain.blokusgame.PlayerGameRecord;
import com.zylear.publish.web.service.blokusgame.PlayerGameRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by xiezongyu on 2018/8/15.
 */
@Component
public class PlayerGameRecordServiceImpl implements PlayerGameRecordService {
    @Autowired
    private PlayerGameRecordMapper playerGameRecordMapper;


    @Override
    public List<PlayerGameRecord> findRanks(Integer gameType) {
        return playerGameRecordMapper.findRanks(gameType);
    }

    @Override
    public List<PlayerGameRecord> findRanksByAccount(String account) {
        return playerGameRecordMapper.findRanksByAccount(account);
    }

    @Override
    public void insert(PlayerGameRecord gameRecord) {
        playerGameRecordMapper.insert(gameRecord);
    }
}
