package com.zylear.publish.web.service.passcheck.impl;

import com.zylear.publish.web.dao.mybatis.passcheck.PassCheckCodeMapper;
import com.zylear.publish.web.domain.passcheck.PassCheckCode;
import com.zylear.publish.web.service.passcheck.PassCheckCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by xiezongyu on 2018/10/10.
 */
@Component
public class PassCheckCodeServiceImpl implements PassCheckCodeService {

    @Autowired
    private PassCheckCodeMapper passCheckCodeMapper;


    @Override
    public PassCheckCode findByConfigKey(String configKey) {
        return passCheckCodeMapper.findByConfigKey(configKey);
    }
}
