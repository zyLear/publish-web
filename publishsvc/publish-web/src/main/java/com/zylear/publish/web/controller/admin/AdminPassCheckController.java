package com.zylear.publish.web.controller.admin;

import com.zylear.publish.web.bean.BaseResponse;
import com.zylear.publish.web.bean.passcheck.*;
import com.zylear.publish.web.domain.passcheck.CardInfo;
import com.zylear.publish.web.domain.passcheck.PassCheckCode;
import com.zylear.publish.web.domain.passcheck.UserAccount;
import com.zylear.publish.web.domain.passcheck.UserLog;
import com.zylear.publish.web.manager.PassCheckManager;
import com.zylear.publish.web.service.passcheck.CardInfoService;
import com.zylear.publish.web.service.passcheck.PassCheckCodeService;
import com.zylear.publish.web.service.passcheck.UserAccountService;
import com.zylear.publish.web.service.passcheck.UserLogService;
import com.zylear.publish.web.util.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
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
    private CardInfoService cardInfoService;
    private static final String PULL_VALIDATE = "pull_validate";
    private static final String PULL_HELPER = "pull_helper";
    private static final String PULL_VIP_HELPER = "pull_vip_helper";


    @ResponseBody
    @RequestMapping(value = "/sure-activate")
    public synchronized BaseResponse sureActivate(@RequestBody ActivateRequest request) {

        if (StringUtils.isEmpty(request.getAccount()) ||
                StringUtils.isEmpty(request.getCardNumber()) ||
                StringUtils.isEmpty(request.getCardPassword())) {
            return new BaseResponse(1, "失败，输入信息不能为空！");
        }

        UserAccount userAccount = userAccountService.findByAccount(request.getAccount());
        if (userAccount == null) {
            return new BaseResponse(1, "失败，账号不存在！");
        }

        CardInfo cardInfo = cardInfoService.findByNumberAndPassword(request.getCardNumber(), request.getCardPassword());
        if (cardInfo == null) {
            return new BaseResponse(1, "失败，卡号密码不正确！");
        }

        if (cardInfo.getIsUsed()) {
            return new BaseResponse(1, "失败，此卡号已使用！");
        }

        Date vipExpireTime = formVipExpireTime(userAccount.getVipExpireTime(), cardInfo.getMonths());
        passCheckManager.activate(request, vipExpireTime);

        return BaseResponse.SUCCESS_RESPONSE;
    }

    private Date formVipExpireTime(Date vipExpireTime, Integer months) {
        Calendar calendar = Calendar.getInstance();
        if (vipExpireTime.getTime() < System.currentTimeMillis()) {
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            calendar.add(Calendar.HOUR_OF_DAY, 1);
        } else {
            calendar.setTime(vipExpireTime);
        }
        calendar.add(Calendar.MONTH, months);
        return calendar.getTime();
    }


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
            return new LoginResponse(BaseResponse.ERROR_RESPONSE, "", "", "");
        }
        UserLog userLog = formLog(request.getAccount(), request.getDeviceId(), "login", "login");
        userLogService.insert(userLog);
        String accountInfo = formAccountInfo(userAccount);
        String helper = formMessageHelper(PULL_HELPER);
        String vipHelper = formMessageHelper(PULL_VIP_HELPER);
        return new LoginResponse(BaseResponse.SUCCESS_RESPONSE, accountInfo, helper, vipHelper);
    }

    private String formMessageHelper(String codeKey) {
        PassCheckCode passCheckCode = passCheckCodeService.findByConfigKey(codeKey);
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

    @Autowired
    public void setCardInfoService(CardInfoService cardInfoService) {
        this.cardInfoService = cardInfoService;
    }
}
