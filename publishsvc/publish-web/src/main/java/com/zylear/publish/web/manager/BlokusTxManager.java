package com.zylear.publish.web.manager;

import com.zylear.publish.web.config.DataSourceBlokusGameConfig;
import com.zylear.publish.web.domain.blokusgame.GameAccount;
import com.zylear.publish.web.domain.blokusgame.PlayerGameRecord;
import com.zylear.publish.web.enums.GameType;
import com.zylear.publish.web.service.blokusgame.GameAccountService;
import com.zylear.publish.web.service.blokusgame.PlayerGameRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by xiezongyu on 2018/8/15.
 */
@Component
public class BlokusTxManager {

    @Autowired
    private GameAccountService gameAccountService;
    @Autowired
    private PlayerGameRecordService playerGameRecordService;

    @Transactional(value = DataSourceBlokusGameConfig.TX_MANAGER)
    public void register(String account, String password) {
        Date date = new Date();
        GameAccount gameAccount = new GameAccount();
        gameAccount.setAccount(account);
        gameAccount.setPassword(password);
        gameAccount.setStars(0);
        gameAccount.setCreateTime(date);
        gameAccount.setLastUpdateTime(date);
        gameAccountService.insert(gameAccount);

        PlayerGameRecord gameRecord = new PlayerGameRecord();
        gameRecord.setAccount(account);
        gameRecord.setGameType(GameType.blokus_four.getValue());
        gameRecord.setWinCount(0);
        gameRecord.setLoseCount(0);
        gameRecord.setEscapeCount(0);
        gameRecord.setRankScore(1200);
        gameRecord.setRank(0);
        gameRecord.setCreateTime(new Date());
        gameRecord.setLastUpdateTime(date);
        playerGameRecordService.insert(gameRecord);
        gameRecord.setGameType(GameType.blokus_two.getValue());
        playerGameRecordService.insert(gameRecord);
    }

}
