package com.zylear.publish.web.service.pubilsh.impl;

import com.zylear.publish.web.dao.mybatis.publish.PubgVideoMapper;
import com.zylear.publish.web.service.pubilsh.PubgVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by xiezongyu on 2018/8/12.
 */
@Component
public class PubgVideoServiceImpl implements PubgVideoService {
    @Autowired
    private PubgVideoMapper pubgVideoMapper;


}
