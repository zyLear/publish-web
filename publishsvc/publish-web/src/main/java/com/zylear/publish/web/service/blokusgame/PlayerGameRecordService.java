package com.zylear.publish.web.service.blokusgame;

import com.zylear.publish.web.domain.blokusgame.PlayerGameRecord;

import java.util.List;

/**
 * Created by xiezongyu on 2018/8/15.
 */
public interface PlayerGameRecordService {

    List<PlayerGameRecord> findRanks(Integer gameType);

    List<PlayerGameRecord> findRanksByAccount(String account);

    void insert(PlayerGameRecord gameRecord);
}
