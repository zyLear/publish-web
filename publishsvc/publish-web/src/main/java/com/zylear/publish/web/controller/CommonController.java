package com.zylear.publish.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by xiezongyu on 2018/8/14.
 */
@Controller
@RequestMapping("/common")
public class CommonController {

    @RequestMapping("/common-navigation")
    public ModelAndView commonNavigation() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("common/common-navigation");
        return modelAndView;
    }

    @RequestMapping("/common-css")
    public ModelAndView commonCss() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("common/common-css");
        return modelAndView;
    }

    @RequestMapping("/common-js")
    public ModelAndView commonJs() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("common/common-js");
        return modelAndView;
    }


}
