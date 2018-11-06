package com.zylear.publish.web.service.passcheck;

import com.zylear.publish.web.domain.passcheck.CardInfo;

/**
 * Created by xiezongyu on 2018/10/30.
 */
public interface CardInfoService {

    CardInfo findByNumberAndPassword(String cardNumber, String cardPassword);

    void updateByCardNumber(String cardNumber, Boolean isUsed, String account);
}
