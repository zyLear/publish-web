package com.zylear.publish.web.manager;

import com.zylear.publish.web.controller.bean.PassCheckBean;
import com.zylear.publish.web.domain.passcheck.PassCheckCode;
import com.zylear.publish.web.domain.passcheck.UserAccount;
import com.zylear.publish.web.service.passcheck.PassCheckCodeService;
import com.zylear.publish.web.service.passcheck.UserAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by xiezongyu on 2018/10/12.
 */
@Component
public class PassCheckManager {

    private static final Logger logger = LoggerFactory.getLogger(PassCheckManager.class);
    private PassCheckCodeService passCheckCodeService;
    private UserAccountService userAccountService;
    private static final String REGISTER_CONFIG = "register_config";
    private static final Integer DEFAULT_VIP_DAY = 0;

    public synchronized void register(String account, String password, String deviceId) {

        int vipDay = DEFAULT_VIP_DAY;
        PassCheckCode config = passCheckCodeService.findByConfigKey(REGISTER_CONFIG);
        if (config != null) {
            vipDay = Integer.parseInt(config.getConfigValue());
        }
        UserAccount userAccount = new UserAccount();
        userAccount.setAccount(account);
        userAccount.setPassword(password);
        userAccount.setDeviceId(deviceId);
        userAccount.setVipExpireTime(formVipExpireTime(vipDay));
        userAccount.setIsDeleted(false);
        userAccount.setCreateTime(new Date());
        userAccount.setLastUpdateTime(new Date());
        userAccountService.insert(userAccount);

    }

    private Date formVipExpireTime(int vipDay) {
        long currentTimeMillis = System.currentTimeMillis();
        return new Date(currentTimeMillis + vipDay * 24 * 60 * 60 * 1000);
    }


    @Autowired
    public void setPassCheckCodeService(PassCheckCodeService passCheckCodeService) {
        this.passCheckCodeService = passCheckCodeService;
    }

    @Autowired
    public void setUserAccountService(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }
}
