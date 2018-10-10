package com.zylear.publish.web.controller.admin;

import com.zylear.publish.web.bean.BasePageResponse;
import com.zylear.publish.web.bean.GobangResponse;
import com.zylear.publish.web.bean.SubmitResponse;
import com.zylear.publish.web.controller.bean.ArticlePostBean;
import com.zylear.publish.web.controller.bean.VideoPostBean;
import com.zylear.publish.web.domain.blokusgame.GobangOptimize;
import com.zylear.publish.web.domain.passcheck.PassCheckCode;
import com.zylear.publish.web.manager.SubmitManager;
import com.zylear.publish.web.service.blokusgame.GobangOptimizeService;
import com.zylear.publish.web.service.passcheck.PassCheckCodeService;
import com.zylear.publish.web.service.pubilsh.OwnBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by xiezongyu on 2018/8/4.
 */
@Controller
@RequestMapping("/admin/passcheck")
public class AdminPassCheckController {


    private PassCheckCodeService passCheckCodeService;
    private static final String VERSION_KEY = "VERSION_KEY";

//    @RequestMapping(value = "/gobang/submit", produces = "application/json;charset=utf-8;")
//    @ResponseBody
//    public BasePageResponse submit(@RequestBody GobangOptimize gobangOptimize) {
//        gobangOptimizeService.insert(gobangOptimize);
//        return BasePageResponse.SUCCESS_RESPONSE;
//    }
//
//
//    @RequestMapping(value = "/gobang/all-record", produces = "application/json;charset=utf-8;")
//    @ResponseBody
//    public GobangResponse sync() {
//        GobangResponse gobangResponse = new GobangResponse();
//        gobangResponse.setList(gobangOptimizeService.findAll());
//        return gobangResponse;
//    }

    @RequestMapping(value = "/pull-pass-check-code", produces = "application/json;charset=utf-8;")
    @ResponseBody
    public String pullPassCheckCode() {
        PassCheckCode passCheckCode = passCheckCodeService.findByConfigKey(VERSION_KEY);
        if (passCheckCode != null) {
            passCheckCode = passCheckCodeService.findByConfigKey(passCheckCode.getConfigValue());
            if (passCheckCode != null) {
                return passCheckCode.getConfigValue();
            }
        }
        return "";
    }


    @Autowired
    public void setPassCheckCodeService(PassCheckCodeService passCheckCodeService) {
        this.passCheckCodeService = passCheckCodeService;
    }
}
