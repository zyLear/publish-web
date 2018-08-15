package com.zylear.publish.web.service.blokusgame;

import com.zylear.publish.web.domain.blokusgame.PlayerGameLog;

import java.util.List;

/**
 * Created by xiezongyu on 2018/8/15.
 */
public interface PlayerGameLogService {

    List<PlayerGameLog> findByAccount(String account);

    List<PlayerGameLog> findByGameLogId(Integer gameLogId);
}
