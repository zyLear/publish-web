package com.zylear.publish.web.service.passcheck;

import com.zylear.publish.web.domain.passcheck.PassCheckCode;

/**
 * Created by xiezongyu on 2018/10/10.
 */
public interface PassCheckCodeService {

    PassCheckCode findByConfigKey(String configKey);

}
