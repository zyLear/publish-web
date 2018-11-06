package com.zylear.publish.web.service.passcheck;

import com.zylear.publish.web.domain.passcheck.UserAccount;

import java.util.Date;

/**
 * Created by xiezongyu on 2018/10/12.
 */
public interface UserAccountService {

    UserAccount findByAccount(String account);

    UserAccount findByDeviceId(String deviceId);

    void insert(UserAccount userAccount);

    UserAccount findByAccountAndPassword(String account, String password);

    void updateVipExpireTimeByAccount(String account, Date vipExpireTime);
}
