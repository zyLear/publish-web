package com.zylear.publish.web.controller.admin;

import com.zylear.publish.web.bean.BaseResponse;
import com.zylear.publish.web.bean.passcheck.*;
import com.zylear.publish.web.domain.passcheck.PassCheckCode;
import com.zylear.publish.web.domain.passcheck.UserAccount;
import com.zylear.publish.web.domain.passcheck.UserLog;
import com.zylear.publish.web.manager.PassCheckManager;
import com.zylear.publish.web.service.passcheck.PassCheckCodeService;
import com.zylear.publish.web.service.passcheck.UserAccountService;
import com.zylear.publish.web.service.passcheck.UserLogService;
import com.zylear.publish.web.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by xiezongyu on 2018/8/4.
 */
@Controller
@RequestMapping("/passcheck")
public class AdminPassCheckController {


    private PassCheckCodeService passCheckCodeService;
    private UserAccountService userAccountService;
    private UserLogService userLogService;
    private PassCheckManager passCheckManager;
    private static final String PULL_VALIDATE = "pull_validate";
    private static final String PULL_HELPER = "pull_helper";


    @RequestMapping(value = "/pull-pass-check-code", produces = "application/json;charset=utf-8;")
    @ResponseBody
    public PassCheckResponse pullPassCheckCode(HttpServletRequest httpServletRequest,
                                               @RequestBody PassCheckRequest request) {

        UserAccount userAccount = userAccountService.findByAccountAndPassword(request.getAccount(), request.getPassword());
        if (userAccount == null) {
            return new PassCheckResponse(1, "失败，请登录！！！", "");
        }

        PassCheckCode passCheckCode = passCheckCodeService.findByConfigKey(PULL_VALIDATE);
        if (passCheckCode != null && "true".equals(passCheckCode.getConfigValue())) {
            if (userAccount.getVipExpireTime().getTime() < System.currentTimeMillis()) {
                return new PassCheckResponse(1, "失败，请开通vip！！！", "");
            }
        }


        passCheckCode = passCheckCodeService.findByConfigKey(request.getCodeKey());
        if (passCheckCode != null) {
            passCheckCode = passCheckCodeService.findByConfigKey(passCheckCode.getConfigValue());
            if (passCheckCode != null) {
                UserLog userLog = formLog(request.getAccount(), request.getDeviceId(), request.getCodeKey(), passCheckCode.getConfigKey());
                userLogService.insert(userLog);
                return new PassCheckResponse(BaseResponse.SUCCESS_RESPONSE, passCheckCode.getConfigValue());
            }
        }
        return new PassCheckResponse(2, "失败，服务器正在更新！！！", "");
    }

    private UserLog formLog(String account, String deviceId, String actionKey, String actionValue) {
        UserLog userLog = new UserLog();
        userLog.setAccount(account);
        userLog.setDeviceId(deviceId);
        userLog.setActionKey(actionKey);
        userLog.setActionValue(actionValue);
        userLog.setIsDeleted(false);
        userLog.setCreateTime(new Date());
        userLog.setLastUpdateTime(new Date());
        return userLog;
    }


    //stand-alone lock
    @ResponseBody
    @RequestMapping(value = "/sure-register")
    public synchronized BaseResponse sureRegister(@RequestBody RegisterRequest request) {

//        UserAccount userAccount = userAccountService.findByDeviceId(request.getDeviceId());
//        if (userAccount != null) {
//            return new BaseResponse(1, "注册失败，此设备已经注册！");
//        }
        UserAccount userAccount = userAccountService.findByAccount(request.getAccount());
        if (userAccount != null) {
            return new BaseResponse(2, "注册失败，此账号已经注册！");
        }
        passCheckManager.register(request.getAccount(), request.getPassword(), request.getDeviceId());
        return BaseResponse.SUCCESS_RESPONSE;
    }

    @ResponseBody
    @RequestMapping(value = "/sure-login")
    public synchronized LoginResponse sureLogin(@RequestBody LoginRequest request) {

        UserAccount userAccount = userAccountService.findByAccountAndPassword(request.getAccount(), request.getPassword());
        if (userAccount == null) {
            return new LoginResponse(BaseResponse.ERROR_RESPONSE, "", "");
        }
        UserLog userLog = formLog(request.getAccount(), request.getDeviceId(), "login", "login");
        userLogService.insert(userLog);
        String accountInfo = formAccountInfo(userAccount);
        String helper = formHelper();
        return new LoginResponse(BaseResponse.SUCCESS_RESPONSE, accountInfo, helper);
    }

    private String formHelper() {
        PassCheckCode passCheckCode = passCheckCodeService.findByConfigKey(PULL_HELPER);
        if (passCheckCode != null) {
            passCheckCode = passCheckCodeService.findByConfigKey(passCheckCode.getConfigValue());
            if (passCheckCode != null) {
                return passCheckCode.getConfigValue();
            }
        }
        return "获取信息出错！！！";
    }

    private String formAccountInfo(UserAccount userAccount) {

        String string;
        if (userAccount.getVipExpireTime().getTime() < System.currentTimeMillis()) {
            string = "账号：\n"
                    + userAccount.getAccount()
                    + "\n未开通VIP";
        } else {
            string = "账号：\n"
                    + userAccount.getAccount()
                    + "\nVIP到期时间：\n"
                    + DateUtil.formatToYDMHMS(userAccount.getVipExpireTime());
        }
        return string;
    }


    @Autowired
    public void setPassCheckCodeService(PassCheckCodeService passCheckCodeService) {
        this.passCheckCodeService = passCheckCodeService;
    }

    @Autowired
    public void setUserAccountService(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @Autowired
    public void setPassCheckManager(PassCheckManager passCheckManager) {
        this.passCheckManager = passCheckManager;
    }

    @Autowired
    public void setUserLogService(UserLogService userLogService) {
        this.userLogService = userLogService;
    }
}
