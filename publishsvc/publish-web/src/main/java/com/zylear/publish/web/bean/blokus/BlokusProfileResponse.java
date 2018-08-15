package com.zylear.publish.web.bean.blokus;

import java.util.List;

/**
 * Created by xiezongyu on 2018/8/15.
 */
public class BlokusProfileResponse {
    private BlokusRankInfo twoPlayersRankInfo;
    private BlokusRankInfo fourPlayersRankInfo;
    private List<BlokusGameLog> gameLogs;

    public BlokusRankInfo getTwoPlayersRankInfo() {
        return twoPlayersRankInfo;
    }

    public void setTwoPlayersRankInfo(BlokusRankInfo twoPlayersRankInfo) {
        this.twoPlayersRankInfo = twoPlayersRankInfo;
    }

    public BlokusRankInfo getFourPlayersRankInfo() {
        return fourPlayersRankInfo;
    }

    public void setFourPlayersRankInfo(BlokusRankInfo fourPlayersRankInfo) {
        this.fourPlayersRankInfo = fourPlayersRankInfo;
    }

    public List<BlokusGameLog> getGameLogs() {
        return gameLogs;
    }

    public void setGameLogs(List<BlokusGameLog> gameLogs) {
        this.gameLogs = gameLogs;
    }
}
