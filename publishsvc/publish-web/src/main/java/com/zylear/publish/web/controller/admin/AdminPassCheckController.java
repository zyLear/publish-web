package com.zylear.publish.web.controller.admin;

import com.zylear.publish.web.bean.BaseResponse;
import com.zylear.publish.web.bean.passcheck.LoginRequest;
import com.zylear.publish.web.bean.passcheck.PassCheckResponse;
import com.zylear.publish.web.bean.passcheck.RegisterRequest;
import com.zylear.publish.web.controller.bean.PassCheckBean;
import com.zylear.publish.web.domain.blokusgame.GameAccount;
import com.zylear.publish.web.domain.passcheck.PassCheckCode;
import com.zylear.publish.web.domain.passcheck.UserAccount;
import com.zylear.publish.web.manager.PassCheckManager;
import com.zylear.publish.web.service.passcheck.PassCheckCodeService;
import com.zylear.publish.web.service.passcheck.UserAccountService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by xiezongyu on 2018/8/4.
 */
@Controller
@RequestMapping("/admin/passcheck")
public class AdminPassCheckController {


    private PassCheckCodeService passCheckCodeService;
    private UserAccountService userAccountService;
    private PassCheckManager passCheckManager;


    @RequestMapping(value = "/pull-pass-check-code", produces = "application/json;charset=utf-8;")
    @ResponseBody
    public PassCheckResponse pullPassCheckCode(HttpServletRequest httpServletRequest,
                                               @RequestBody PassCheckBean request) {

        UserAccount userAccount = userAccountService.findByAccountAndPassword(request.getAccount(), request.getPassword());
        if (userAccount == null) {
            return new PassCheckResponse(BaseResponse.ERROR_RESPONSE, "");
        }

        PassCheckCode passCheckCode = passCheckCodeService.findByConfigKey(request.getCodeKey());
        if (passCheckCode != null) {
            passCheckCode = passCheckCodeService.findByConfigKey(passCheckCode.getConfigValue());
            if (passCheckCode != null) {
                return new PassCheckResponse(BaseResponse.SUCCESS_RESPONSE, passCheckCode.getConfigValue());
            }
        }
        return new PassCheckResponse(2, "no found code", "");
    }


    //stand-alone lock
    @ResponseBody
    @RequestMapping(value = "/sure-register")
    public synchronized BaseResponse sureRegister(@RequestBody RegisterRequest request) {

        UserAccount userAccount = userAccountService.findByDeviceId(request.getDeviceId());
        if (userAccount != null) {
            return BaseResponse.ERROR_RESPONSE;
        }
        userAccount = userAccountService.findByAccount(request.getAccount());
        if (userAccount != null) {
            return new BaseResponse(2, "account exist");
        }
        passCheckManager.register(request.getAccount(), request.getPassword(), request.getDeviceId());
        return BaseResponse.SUCCESS_RESPONSE;
    }

    @ResponseBody
    @RequestMapping(value = "/sure-login")
    public synchronized BaseResponse sureLogin(@RequestBody LoginRequest request) {

        UserAccount userAccount = userAccountService.findByAccountAndPassword(request.getAccount(), request.getPassword());
        if (userAccount == null) {
            return BaseResponse.ERROR_RESPONSE;
        }
        return BaseResponse.SUCCESS_RESPONSE;
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
}
