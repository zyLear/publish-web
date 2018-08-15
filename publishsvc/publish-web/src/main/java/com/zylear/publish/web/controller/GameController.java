package com.zylear.publish.web.controller;

import com.zylear.publish.web.bean.BasePageResponse;
import com.zylear.publish.web.domain.blokusgame.GameAccount;
import com.zylear.publish.web.service.blokusgame.GameAccountService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

/**
 * Created by xiezongyu on 2018/8/14.
 */
@Controller
@RequestMapping("/game")
public class GameController {

    @Autowired
    private GameAccountService gameAccountService;

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
    public BasePageResponse sureRegister(@Param("account") String account,
                                         @Param("password") String password) {


        GameAccount gameAccount = gameAccountService.findByAccount(account);
        if (gameAccount != null) {
            return BasePageResponse.ERROR_RESPONSE;
        }
        gameAccount = new GameAccount();
        gameAccount.setAccount(account);
        gameAccount.setPassword(password);
        gameAccount.setStars(0);
        gameAccount.setCreateTime(new Date());
        gameAccount.setLastUpdateTime(new Date());
        gameAccountService.insert(gameAccount);
        return BasePageResponse.SUCCESS_RESPONSE;
    }

    @ResponseBody
    @RequestMapping(value = "blokus/profile")
    public BasePageResponse blokusProfile(@Param("account") String account) {
        
        return BasePageResponse.SUCCESS_RESPONSE;
    }

    @ResponseBody
    @RequestMapping(value = "blokus/rank")
    public BasePageResponse blokusRank() {

        return BasePageResponse.SUCCESS_RESPONSE;
    }





}
