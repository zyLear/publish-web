package com.zylear.publish.web.bean.blokus;

/**
 * Created by xiezongyu on 2018/8/15.
 */
public class BlokusRankInfo {

    private String account;
    private Integer rankScore;
    private Integer winCount;
    private Integer loseCount;
    private Integer escapeCount;
    private String rank;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Integer getRankScore() {
        return rankScore;
    }

    public void setRankScore(Integer rankScore) {
        this.rankScore = rankScore;
    }

    public Integer getWinCount() {
        return winCount;
    }

    public void setWinCount(Integer winCount) {
        this.winCount = winCount;
    }

    public Integer getLoseCount() {
        return loseCount;
    }

    public void setLoseCount(Integer loseCount) {
        this.loseCount = loseCount;
    }

    public Integer getEscapeCount() {
        return escapeCount;
    }

    public void setEscapeCount(Integer escapeCount) {
        this.escapeCount = escapeCount;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }
}
