package com.zylear.publish.web.service.blokusgame.impl;

import com.zylear.publish.web.dao.mybatis.blokusgame.GameAccountMapper;
import com.zylear.publish.web.domain.blokusgame.GameAccount;
import com.zylear.publish.web.service.blokusgame.GameAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by xiezongyu on 2018/8/14.
 */
@Component
public class GameAccountServiceImpl implements GameAccountService {
    @Autowired
    private GameAccountMapper accountMapper;

    @Override
    public GameAccount findByAccount(String account) {
        return accountMapper.findByAccount(account);
    }

    @Override
    public void insert(GameAccount gameAccount) {
        accountMapper.insert(gameAccount);
    }
}
