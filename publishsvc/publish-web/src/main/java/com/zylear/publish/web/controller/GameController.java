package com.zylear.publish.web.controller;

import com.zylear.publish.web.bean.BaseResponse;
import com.zylear.publish.web.bean.blokus.BlokusGameLog;
import com.zylear.publish.web.bean.blokus.BlokusProfileResponse;
import com.zylear.publish.web.bean.blokus.BlokusRankInfo;
import com.zylear.publish.web.bean.blokus.BlokusRankResponse;
import com.zylear.publish.web.domain.blokusgame.GameAccount;
import com.zylear.publish.web.domain.blokusgame.GameLog;
import com.zylear.publish.web.domain.blokusgame.PlayerGameLog;
import com.zylear.publish.web.domain.blokusgame.PlayerGameRecord;
import com.zylear.publish.web.enums.GameResult;
import com.zylear.publish.web.enums.GameType;
import com.zylear.publish.web.manager.BlokusTxManager;
import com.zylear.publish.web.service.blokusgame.GameAccountService;
import com.zylear.publish.web.service.blokusgame.GameLogService;
import com.zylear.publish.web.service.blokusgame.PlayerGameLogService;
import com.zylear.publish.web.service.blokusgame.PlayerGameRecordService;
import com.zylear.publish.web.util.DateUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by xiezongyu on 2018/8/14.
 */
@Controller
@RequestMapping("/game")
public class GameController {

    @Autowired
    private GameAccountService gameAccountService;
    @Autowired
    private PlayerGameLogService playerGameLogService;
    @Autowired
    private PlayerGameRecordService playerGameRecordService;
    @Autowired
    private GameLogService gameLogService;
    @Autowired
    private BlokusTxManager blokusTxManager;

    @RequestMapping("/game-list")
    public ModelAndView gameList() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("game/game-list");
        return modelAndView;
    }

    @RequestMapping("/blokus")
    public ModelAndView blokus() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("game/blokus/blokus");
        return modelAndView;
    }

    @RequestMapping("/blokus/register")
    public ModelAndView blokusRegister() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("game/blokus/blokus-register");
        return modelAndView;
    }

    @RequestMapping("/blokus/start")
    public ModelAndView blokusStart() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("xxx");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "blokus/sure-register")
    public BaseResponse sureRegister(@Param("account") String account,
                                     @Param("password") String password) {


        GameAccount gameAccount = gameAccountService.findByAccount(account);
        if (gameAccount != null) {
            return BaseResponse.ERROR_RESPONSE;
        }
        blokusTxManager.register(account, password);
        return BaseResponse.SUCCESS_RESPONSE;
    }

    @ResponseBody
    @RequestMapping(value = "blokus/profile")
    public BlokusProfileResponse blokusProfile(@Param("account") String account) {

        List<PlayerGameRecord> playerGameRecords = playerGameRecordService.findRanksByAccount(account);
        List<PlayerGameLog> playerGameLogs = playerGameLogService.findByAccount(account);
        return toBlokusProfileResponse(playerGameRecords, playerGameLogs);
    }

    private BlokusProfileResponse toBlokusProfileResponse(List<PlayerGameRecord> playerGameRecords, List<PlayerGameLog> playerGameLogs) {

        BlokusProfileResponse blokusProfileResponse = new BlokusProfileResponse();

        List<BlokusGameLog> gameLogs = new LinkedList<>();

        for (PlayerGameLog playerGameLog : playerGameLogs) {
            GameLog gameLog = gameLogService.findById(playerGameLog.getGameLogId());
            List<PlayerGameLog> playerGameLogList = playerGameLogService.findByGameLogId(playerGameLog.getGameLogId());
            BlokusGameLog blokusGameLog = new BlokusGameLog();
            blokusGameLog.setGameResult(GameResult.valueOf(playerGameLog.getGameResult()).toString());
            blokusGameLog.setGameType(GameType.valueOf(gameLog.getGameType()).toString());
            blokusGameLog.setStepsCount(playerGameLog.getStepsCount());
            blokusGameLog.setTime(DateUtil.formatToYDMHMS(gameLog.getCreateTime()));
            StringBuilder detail = new StringBuilder();
            int i = 0;
            for (PlayerGameLog log : playerGameLogList) {
                i++;
                detail.append(log.getAccount()).append(":").append(log.getStepsCount()).append("   ");
                if (i == 2) {
                    detail.append("\n");
                }
            }
            blokusGameLog.setChangeScore(playerGameLog.getChangeScore());
            blokusGameLog.setDetail(detail.toString());
            gameLogs.add(blokusGameLog);
        }
        blokusProfileResponse.setGameLogs(gameLogs);


        for (PlayerGameRecord record : playerGameRecords) {
            BlokusRankInfo blokusRankInfo = new BlokusRankInfo();
            blokusRankInfo.setAccount(record.getAccount());
            blokusRankInfo.setWinCount(record.getWinCount());
            blokusRankInfo.setLoseCount(record.getLoseCount());
            blokusRankInfo.setEscapeCount(record.getEscapeCount());
            blokusRankInfo.setRankScore(record.getRankScore());
            blokusRankInfo.setRank("gold");
            if (GameType.blokus_four.getValue().equals(record.getGameType())) {
                blokusProfileResponse.setFourPlayersRankInfo(blokusRankInfo);
            } else {
                blokusProfileResponse.setTwoPlayersRankInfo(blokusRankInfo);
            }
        }
        return blokusProfileResponse;
    }

    @ResponseBody
    @RequestMapping(value = "blokus/rank")
    public BlokusRankResponse blokusRank() {
        List<PlayerGameRecord> fourPlayersRanks = playerGameRecordService.findRanks(1);
        List<PlayerGameRecord> twoPlayersRanks = playerGameRecordService.findRanks(2);
        return formatRankInfoMessage(twoPlayersRanks, fourPlayersRanks);
    }

    private BlokusRankResponse formatRankInfoMessage(List<PlayerGameRecord> twoPlayersRanks, List<PlayerGameRecord> fourPlayersRanks) {
        BlokusRankResponse response = new BlokusRankResponse();
        List<BlokusRankInfo> twoPlayersRankInfos = new ArrayList<>(twoPlayersRanks.size());
        List<BlokusRankInfo> fourPlayersRankInfos = new ArrayList<>(fourPlayersRanks.size());
        for (PlayerGameRecord record : twoPlayersRanks) {
            BlokusRankInfo blokusRankInfo = new BlokusRankInfo();
            blokusRankInfo.setAccount(record.getAccount());
            blokusRankInfo.setWinCount(record.getWinCount());
            blokusRankInfo.setLoseCount(record.getLoseCount());
            blokusRankInfo.setEscapeCount(record.getEscapeCount());
            blokusRankInfo.setRankScore(record.getRankScore());
            blokusRankInfo.setRank("gold");
            twoPlayersRankInfos.add(blokusRankInfo);
        }
        for (PlayerGameRecord record : fourPlayersRanks) {
            BlokusRankInfo blokusRankInfo = new BlokusRankInfo();
            blokusRankInfo.setAccount(record.getAccount());
            blokusRankInfo.setWinCount(record.getWinCount());
            blokusRankInfo.setLoseCount(record.getLoseCount());
            blokusRankInfo.setEscapeCount(record.getEscapeCount());
            blokusRankInfo.setRankScore(record.getRankScore());
            blokusRankInfo.setRank("gold");
            fourPlayersRankInfos.add(blokusRankInfo);
        }

        response.setFourPlayersRankItems(fourPlayersRankInfos);
        response.setTwoPlayersRankItems(twoPlayersRankInfos);
        return response;
    }


}
