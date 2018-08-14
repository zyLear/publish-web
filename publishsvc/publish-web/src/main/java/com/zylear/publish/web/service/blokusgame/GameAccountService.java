package com.zylear.publish.web.service.blokusgame;

import com.zylear.publish.web.domain.blokusgame.GameAccount;

/**
 * Created by xiezongyu on 2018/8/14.
 */
public interface GameAccountService {

    GameAccount findByAccount(String account);

    void insert(GameAccount gameAccount);
}
