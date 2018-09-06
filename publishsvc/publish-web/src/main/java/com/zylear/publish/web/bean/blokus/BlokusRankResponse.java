package com.zylear.publish.web.bean.blokus;

import java.util.List;

/**
 * Created by xiezongyu on 2018/8/15.
 */
public class BlokusRankResponse {

    private List<BlokusRankInfo> twoPlayersRankItems;
    private List<BlokusRankInfo> fourPlayersRankItems;

    public List<BlokusRankInfo> getTwoPlayersRankItems() {
        return twoPlayersRankItems;
    }

    public void setTwoPlayersRankItems(List<BlokusRankInfo> twoPlayersRankItems) {
        this.twoPlayersRankItems = twoPlayersRankItems;
    }

    public List<BlokusRankInfo> getFourPlayersRankItems() {
        return fourPlayersRankItems;
    }

    public void setFourPlayersRankItems(List<BlokusRankInfo> fourPlayersRankItems) {
        this.fourPlayersRankItems = fourPlayersRankItems;
    }
}
