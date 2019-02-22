package com.zylear.publish.web.controller.admin;

import com.zylear.publish.web.bean.BaseResponse;
import com.zylear.publish.web.bean.passcheck.*;
import com.zylear.publish.web.constants.CardInfoConstant;
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
import com.zylear.publish.web.util.WebUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by xiezongyu on 2018/8/4.
 */
@Controller
@RequestMapping("/passcheck")
public class AdminPassCheckController {

    private static final Logger logger = LoggerFactory.getLogger(AdminPassCheckController.class);

    private PassCheckCodeService passCheckCodeService;
    private UserAccountService userAccountService;
    private UserLogService userLogService;
    private PassCheckManager passCheckManager;
    private CardInfoService cardInfoService;
    private static final String PULL_VALIDATE = "pull_validate";
    private static final String PULL_HELPER = "pull_helper";
    private static final String PULL_VIP_HELPER = "pull_vip_helper";
    private static final String PULL_DEVICE_VALIDATE = "pull_device_validate";
    private static final String PASS_CHECK_PERMISSION = "pass_check_permission";
    private static final String PLUGIN_PERMISSION = "plugin_permission";
    private static final String SHELL_CODE = "shell_code";


    @ResponseBody
    @RequestMapping(value = "/sure-activate")
    public synchronized ActivateResponse sureActivate(@RequestBody ActivateRequest request) {

        if (StringUtils.isEmpty(request.getDeviceId())) {
            return new ActivateResponse(1, "失败，设备ID为空！", "");
        }

        if (StringUtils.isEmpty(request.getAccount()) ||
                StringUtils.isEmpty(request.getCardNumber()) ||
                StringUtils.isEmpty(request.getCardPassword())) {
            return new ActivateResponse(1, "失败，输入信息不能为空！", "");
        }

        UserAccount userAccount = userAccountService.findByAccount(request.getAccount());
        if (userAccount == null) {
            return new ActivateResponse(1, "失败，账号不存在！", "");
        }

        CardInfo cardInfo = cardInfoService.findByNumberAndPassword(request.getCardNumber(), request.getCardPassword());
        if (cardInfo == null) {
            return new ActivateResponse(1, "失败，卡号密码不正确！", "");
        }

        if (cardInfo.getIsUsed()) {
            return new ActivateResponse(1, "失败，此卡号已使用！", "");
        }
        Integer offset = cardInfo.getMonths() - cardInfo.getMonths() % 100;

        Date vipExpireTime;
        Date pluginVipExpireTime;
        Integer months = cardInfo.getMonths() - offset;
        switch (offset) {
            case CardInfo.PASS_CHECK_VIP_CARD:
                vipExpireTime = formVipExpireTime(userAccount.getVipExpireTime(), months);
                passCheckManager.activatePassCheckCard(request, vipExpireTime, months);
                break;
            case CardInfo.PLUGIN_VIP_CARD:
                pluginVipExpireTime = formVipExpireTime(userAccount.getPluginVipExpireTime(), months);
                passCheckManager.activatePluginCard(request, pluginVipExpireTime, months);
                break;
            case CardInfo.BOTH_CARD:
                vipExpireTime = formVipExpireTime(userAccount.getVipExpireTime(), months);
                pluginVipExpireTime = formVipExpireTime(userAccount.getPluginVipExpireTime(), months);
                passCheckManager.activateBothCard(request, vipExpireTime, pluginVipExpireTime, months);
                break;
            default:
                return new ActivateResponse(1, "失败，卡类型错误！", "");
        }

        UserAccount newUserAccount = userAccountService.findByAccount(request.getAccount());
        if (newUserAccount == null) {
            newUserAccount = userAccount;
        }

        return new ActivateResponse(BaseResponse.SUCCESS_RESPONSE, formAccountInfo(newUserAccount));
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

        if (months == CardInfoConstant.WEEK_CARD) {
            calendar.add(Calendar.WEEK_OF_YEAR, 1);
        } else {
            calendar.add(Calendar.MONTH, months);
        }
        return calendar.getTime();
    }


    @RequestMapping(value = "/pull-pass-check-code", produces = "application/json;charset=utf-8;")
    @ResponseBody
    public PassCheckResponse pullPassCheckCode(HttpServletRequest httpServletRequest,
                                               @RequestBody PassCheckRequest request) {

        if (StringUtils.isEmpty(request.getDeviceId())) {
            return new PassCheckResponse(1, "失败，设备ID为空！", "");
        }

        UserAccount userAccount = userAccountService.findByAccountAndPassword(request.getAccount(), request.getPassword());
        if (userAccount == null) {
            return new PassCheckResponse(1, "失败，请登录！！！", "");
        }

//        pullVipValidate(userAccount);

        if (validate(PULL_VALIDATE, "true")) {

            Set<String> passCheckPermissionSet = getPermissionSet(PASS_CHECK_PERMISSION);
            Set<String> pluginPermissionSet = getPermissionSet(PLUGIN_PERMISSION);

            if (pluginPermissionSet.contains(request.getCodeKey())) {
                if (userAccount.getPluginVipExpireTime().getTime() < System.currentTimeMillis()) {
                    return new PassCheckResponse(1, "失败，请开通辅助vip！！！", "");
                }
            } else if (passCheckPermissionSet.contains(request.getCodeKey())) {
                if (userAccount.getVipExpireTime().getTime() < System.currentTimeMillis()) {
                    return new PassCheckResponse(1, "失败，请开通vip！！！", "");
                }
            } else {
                if ((userAccount.getPluginVipExpireTime().getTime() < System.currentTimeMillis())
                        && userAccount.getVipExpireTime().getTime() < System.currentTimeMillis()) {
                    return new PassCheckResponse(1, "失败，请开通vip！！！", "");
                }
            }
        }

//        if (validate(PULL_DEVICE_VALIDATE, "true")) {
//            if (!userAccount.getDeviceId().equals(request.getDeviceId())) {
//                return new PassCheckResponse(1, "失败，账号注册的设备和当前使用设备不一致，请联系卖家换绑设备！！！", "");
//            }
//        }

        PassCheckCode passCheckCode = passCheckCodeService.findByConfigKey(request.getCodeKey());
        if (passCheckCode != null) {
            passCheckCode = passCheckCodeService.findByConfigKey(passCheckCode.getConfigValue());
            if (passCheckCode != null) {
                UserLog userLog = formLog(request.getAccount(), request.getDeviceId(), request.getCodeKey(), passCheckCode.getConfigKey());
                userLogService.insert(userLog);
                if (passCheckCode.getConfigKey().contains(SHELL_CODE)) {
                    return new PassCheckResponse(BaseResponse.SUCCESS_RESPONSE, convert(passCheckCode.getConfigValue()));
                } else {
                    return new PassCheckResponse(BaseResponse.SUCCESS_RESPONSE, passCheckCode.getConfigValue());
                }
            }
        }
        return new PassCheckResponse(2, "失败，暂时关闭此功能！！！", "");
    }

    private Set<String> getPermissionSet(String codeKey) {
        PassCheckCode passCheckCode = passCheckCodeService.findByConfigKey(codeKey);
        if (passCheckCode != null) {
            try {
                String value = passCheckCode.getConfigValue();
                String[] items = value.split("\n");
                HashSet<String> set = new HashSet<>(items.length);
                for (String item : items) {
                    if (!StringUtils.isEmpty(item)) {
                        set.add(item);
                    }
                }
                return set;
            } catch (Exception e) {
                logger.warn("getPermissionSet exception. codeKey:{}", codeKey);
            }
        }
        return Collections.EMPTY_SET;
    }

//    private void pullVipValidate(UserAccount userAccount) {
//    }

    private boolean validate(String key, String value) {
        PassCheckCode passCheckCode = passCheckCodeService.findByConfigKey(key);
        if (passCheckCode != null && value.equals(passCheckCode.getConfigValue())) {
            return true;
        } else {
            return false;
        }
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

//        return new BaseResponse(1, "失败");

        if (StringUtils.isEmpty(request.getAccount()) || StringUtils.isEmpty(request.getPassword())) {
            return new BaseResponse(1, "失败，账号或密码不能为空！");
        }

        if (StringUtils.isEmpty(request.getDeviceId())) {
            return new BaseResponse(1, "失败，设备ID为空！");
        }

        if (!request.getPassword().contains("wdgj")) {
            return new BaseResponse(1, "失败");
        }
//
//        UserAccount userAccount = userAccountService.findByDeviceId(request.getDeviceId());
//        if (userAccount != null) {
//            return new BaseResponse(1, "失败，此设备已经注册！");
//        }
        UserAccount userAccount = userAccountService.findByAccount(request.getAccount());
        if (userAccount != null) {
            return new BaseResponse(1, "失败，此账号已经注册！");
        }
        passCheckManager.register(request.getAccount(), request.getPassword(), request.getDeviceId());
        return BaseResponse.SUCCESS_RESPONSE;
    }

    @ResponseBody
    @RequestMapping(value = "/sure-login")
    public synchronized LoginResponse sureLogin(@RequestBody LoginRequest request,
                                                HttpServletRequest httpServletRequest) {

        if (StringUtils.isEmpty(request.getDeviceId())) {
            return new LoginResponse(new BaseResponse(1, "失败，设备ID为空！"), "", "", "");
        }

        UserAccount userAccount = userAccountService.findByAccountAndPassword(request.getAccount(), request.getPassword());
        if (userAccount == null) {
            return new LoginResponse(new BaseResponse(1, "失败，账号或密码不正确！"), "", "", "");
        }
        String ip = WebUtil.getIpAddress(httpServletRequest);
        ip = (ip == null) ? "login" : ip;
        UserLog userLog = formLog(request.getAccount(), request.getDeviceId(), "login", ip);
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
        return "账号：\n"
                + userAccount.getAccount()
                + "\n过检VIP到期时间：\n"
                + formVipExpireTimeText(userAccount.getVipExpireTime());
//                + "\n辅助VIP到期时间："
//                + formVipExpireTimeText(userAccount.getPluginVipExpireTime());
    }

    private String formVipExpireTimeText(Date date) {
        if (date.getTime() < System.currentTimeMillis()) {
            return "未开通VIP";
        } else {
            return DateUtil.formatToYDMHM(date);
        }
    }

    private String convert(String string) {
        if (string == null) {
            return null;
        } else {
            char[] chars = string.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                chars[i] = (char) (chars[i] ^ 999);
            }
            return new String(chars);
        }
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
