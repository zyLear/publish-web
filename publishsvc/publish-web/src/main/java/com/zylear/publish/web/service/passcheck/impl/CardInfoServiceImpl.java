package com.zylear.publish.web.service.passcheck.impl;

import com.zylear.publish.web.dao.mybatis.passcheck.CardInfoMapper;
import com.zylear.publish.web.domain.passcheck.CardInfo;
import com.zylear.publish.web.service.passcheck.CardInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by xiezongyu on 2018/10/30.
 */
@Component
public class CardInfoServiceImpl implements CardInfoService {

    @Autowired
    private CardInfoMapper cardInfoMapper;


    @Override
    public CardInfo findByNumberAndPassword(String cardNumber, String cardPassword) {
        return cardInfoMapper.findByNumberAndPassword(cardNumber, cardPassword);
    }

    @Override
    public void updateByCardNumber(String cardNumber, Boolean isUsed, String account) {
        cardInfoMapper.updateByCardNumber(cardNumber, isUsed, account);
    }
}
