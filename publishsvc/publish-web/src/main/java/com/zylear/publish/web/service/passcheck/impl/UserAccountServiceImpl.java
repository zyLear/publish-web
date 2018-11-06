package com.zylear.publish.web.service.passcheck.impl;

import com.zylear.publish.web.dao.mybatis.passcheck.UserAccountMapper;
import com.zylear.publish.web.domain.passcheck.UserAccount;
import com.zylear.publish.web.service.passcheck.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by xiezongyu on 2018/10/12.
 */
@Component
public class UserAccountServiceImpl implements UserAccountService {

    @Autowired
    private UserAccountMapper userAccountMapper;


    @Override
    public UserAccount findByAccount(String account) {
        return userAccountMapper.findByAccount(account);
    }

    @Override
    public UserAccount findByDeviceId(String deviceId) {
        return userAccountMapper.findByDeviceId(deviceId);
    }

    @Override
    public void insert(UserAccount userAccount) {
        userAccountMapper.insert(userAccount);
    }

    @Override
    public UserAccount findByAccountAndPassword(String account, String password) {
        return userAccountMapper.findByAccountAndPassword(account, password);
    }

    @Override
    public void updateVipExpireTimeByAccount(String account, Date vipExpireTime) {
        userAccountMapper.updateVipExpireTimeByAccount(account, vipExpireTime);
    }
}
