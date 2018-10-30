package com.zylear.publish.web.service.passcheck.impl;

import com.zylear.publish.web.dao.mybatis.passcheck.UserLogMapper;
import com.zylear.publish.web.domain.passcheck.UserLog;
import com.zylear.publish.web.service.passcheck.UserLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by xiezongyu on 2018/10/13.
 */
@Component
public class UserLogServiceImpl implements UserLogService {

    @Autowired
    private UserLogMapper userLogMapper;


    @Override
    public void insert(UserLog userLog) {
        userLogMapper.insert(userLog);
    }
}
