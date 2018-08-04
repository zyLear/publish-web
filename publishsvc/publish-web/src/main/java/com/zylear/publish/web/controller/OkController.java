package com.zylear.publish.web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by xiezongyu on 2018/4/6.
 */
@Controller
@RequestMapping(value = "/ok")
public class OkController {

    @RequestMapping(value = "/ok")
    public ModelAndView test(){
        ModelAndView mav = new ModelAndView("ok");
        mav.addObject("css", css);
        mav.addObject("value", value);
        return mav;
    }

    private String css = " <link rel=\"stylesheet\" href=\"https://mini.eastday.com/toutiaoh5/css/photoswipe/photoswipe.min.css\">\n" +
            "    <link rel=\"stylesheet\" href=\"https://mini.eastday.com/toutiaoh5/css/common.min.css\">\n" +
            "    <link rel=\"stylesheet\" href=\"https://mini.eastday.com/toutiaoh5/css/page_details_v5.min.css\">";

    private String value = "<div id=\"content\" class=\"J-article-content article-content\" data-pswp-uid=\"1\" style=\"margin-top: 0px; overflow: hidden; height: auto;\">\n" +
            "<p class=\"section txt\">对于刺激<a style=\"color:#4B7BB7;\" href=\"http://toutiao.eastday.com/search.html?kw=战场\">战场</a>的<a style=\"color:#4B7BB7;\" href=\"http://toutiao.eastday.com/search.html?kw=玩家\">玩家</a>来说，前二十分钟基本都是搜索装备，很有可能半天也没遇到一个人影存活20分钟之后，就是<a style=\"color:#4B7BB7;\" href=\"http://toutiao.eastday.com/search.html?kw=游戏\">游戏</a>最紧张时候了。</p>\n" +
            "<p class=\"section txt\">在这里给大家分享一些“隐身”的<a style=\"color:#4B7BB7;\" href=\"http://toutiao.eastday.com/search.html?kw=技巧\">技巧</a>。学会了这些藏匿之处，对于我们吃鸡有很大的帮助！<a style=\"color:#4B7BB7;\" href=\"http://toutiao.eastday.com/search.html?kw=小木屋\">小木屋</a></p>\n" +
            "<figure class=\"section img\">\n" +
            "<a class=\"img-wrap\" style=\"padding-bottom: 56.25%;\" data-href=\"https://00.imgmini.eastday.com/mobile/20180303/20180303022524_ad8dbf9665accf4f8005255d60dce33e_1.jpeg\" data-size=\"640x360\">\n" +
            "<img width=\"100%\" alt=\"\" src=\"https://00.imgmini.eastday.com/mobile/20180303/20180303022524_ad8dbf9665accf4f8005255d60dce33e_1.jpeg\" data-weight=\"640\" data-width=\"640\" data-height=\"360\">\n" +
            "</a>\n" +
            "</figure>\n" +
            "<p class=\"section txt\">野外常见小木屋</p>\n" +
            "<p class=\"section txt\"><a style=\"color:#4B7BB7;\" href=\"http://toutiao.eastday.com/search.html?kw=决赛圈\">决赛圈</a>的小木屋，是最不理想的<a style=\"color:#4B7BB7;\" href=\"http://toutiao.eastday.com/search.html?kw=掩体\">掩体</a>，不到万不得已的情况，不建议躲到小木屋里面，虽然看起来很安全，但是一般玩家都会很小心的大门，或者先从窗口投掷手雷，这个时候想躲都躲不掉～而且只有一个门，跑也很难跑出对方的视野。</p>\n" +
            "<figure class=\"section img\">\n" +
            "<a class=\"img-wrap\" style=\"padding-bottom: 56.25%;\" data-href=\"https://00.imgmini.eastday.com/mobile/20180303/20180303022524_ad8dbf9665accf4f8005255d60dce33e_2.jpeg\" data-size=\"640x360\">\n" +
            "<img width=\"100%\" alt=\"\" src=\"https://00.imgmini.eastday.com/mobile/20180303/20180303022524_ad8dbf9665accf4f8005255d60dce33e_2.jpeg\" data-weight=\"640\" data-width=\"640\" data-height=\"360\">\n" +
            "</a>\n" +
            "</figure>\n" +
            "<p class=\"section txt\">喷子守门守楼</p>\n" +
            "<p class=\"section txt\">如果决赛圈是巷战，守住二楼是一个不错的选择，喷子是守楼的不二法宝，注意守楼一定要观察位置，是否可以通过其他更高的楼层打到你，是否存在死角等情况，不要专心守楼，被先进来的玩家用平底锅敲倒。</p>\n" +
            "<figure class=\"section img\">\n" +
            "<a class=\"img-wrap\" style=\"padding-bottom: 56.25%;\" data-href=\"https://00.imgmini.eastday.com/mobile/20180303/20180303022524_ad8dbf9665accf4f8005255d60dce33e_3.jpeg\" data-size=\"640x360\">\n" +
            "<img width=\"100%\" alt=\"\" src=\"https://00.imgmini.eastday.com/mobile/20180303/20180303022524_ad8dbf9665accf4f8005255d60dce33e_3.jpeg\" data-weight=\"640\" data-width=\"640\" data-height=\"360\">\n" +
            "</a>\n" +
            "</figure>\n" +
            "<p class=\"section txt\">守住二楼唯一入口石头</p>\n" +
            "<p class=\"section txt\">背对毒的石头，可以说是很好的掩体，既可以保护前方没有队友发现你，又可以搜住后面，不过决赛圈的石头少之又少，记得在爬过去之前，观察是否已经被人占领了，或者先一个手雷过去试探一下。</p>\n" +
            "<figure class=\"section img\">\n" +
            "<a class=\"img-wrap\" style=\"padding-bottom: 56.25%;\" data-href=\"https://00.imgmini.eastday.com/mobile/20180303/20180303022524_ad8dbf9665accf4f8005255d60dce33e_4.jpeg\" data-size=\"640x360\">\n" +
            "<img width=\"100%\" alt=\"\" src=\"https://00.imgmini.eastday.com/mobile/20180303/20180303022524_ad8dbf9665accf4f8005255d60dce33e_4.jpeg\" data-weight=\"640\" data-width=\"640\" data-height=\"360\">\n" +
            "</a>\n" +
            "</figure>\n" +
            "<p class=\"section txt\">背对毒的石头是不错的选择<a style=\"color:#4B7BB7;\" href=\"http://toutiao.eastday.com/search.html?kw=大树\">大树</a></p>\n" +
            "<p class=\"section txt\">在决赛圈里面，你的关注点主要是每一棵大树，因为每一棵大树后面都有可能隐藏着一个玩家，大树的阴影里面是最好的藏身之地。</p>\n" +
            "<figure class=\"section img\">\n" +
            "<a class=\"img-wrap\" style=\"padding-bottom: 56.25%;\" data-href=\"https://00.imgmini.eastday.com/mobile/20180303/20180303022524_ad8dbf9665accf4f8005255d60dce33e_5.jpeg\" data-size=\"640x360\">\n" +
            "<img width=\"100%\" alt=\"\" src=\"https://00.imgmini.eastday.com/mobile/20180303/20180303022524_ad8dbf9665accf4f8005255d60dce33e_5.jpeg\" data-weight=\"640\" data-width=\"640\" data-height=\"360\">\n" +
            "</a>\n" +
            "</figure>\n" +
            "<p class=\"section txt\">大树</p>\n" +
            "<p class=\"section txt\">这里告诉大家一个小技巧【左右探头射击】开启后，可以根据选择，选择左边或者右边进行射击，减少自身的暴露。</p>\n" +
            "<figure class=\"section img\">\n" +
            "<a class=\"img-wrap\" style=\"padding-bottom: 56.25%;\" data-href=\"https://00.imgmini.eastday.com/mobile/20180303/20180303022524_ad8dbf9665accf4f8005255d60dce33e_6.jpeg\" data-size=\"640x360\">\n" +
            "<img width=\"100%\" alt=\"\" src=\"https://00.imgmini.eastday.com/mobile/20180303/20180303022524_ad8dbf9665accf4f8005255d60dce33e_6.jpeg\" data-weight=\"640\" data-width=\"640\" data-height=\"360\">\n" +
            "</a>\n" +
            "</figure>\n" +
            "<p class=\"section txt\"><a style=\"color:#4B7BB7;\" href=\"http://toutiao.eastday.com/search.html?kw=探身\">探身</a>开关</p>\n" +
            "<p class=\"section txt\">探身开关选择右边射击，只露出头和枪口，身体部分还是在大树的掩护之下的，不过如果运气不佳，对手优先发现了你，一把98K在外面等着，那你只能自求多福了。</p>\n" +
            "<figure class=\"section img\">\n" +
            "<a class=\"img-wrap\" style=\"padding-bottom: 56.25%;\" data-href=\"https://00.imgmini.eastday.com/mobile/20180303/20180303022524_ad8dbf9665accf4f8005255d60dce33e_7.jpeg\" data-size=\"640x360\">\n" +
            "<img width=\"100%\" alt=\"\" src=\"https://00.imgmini.eastday.com/mobile/20180303/20180303022524_ad8dbf9665accf4f8005255d60dce33e_7.jpeg\" data-weight=\"640\" data-width=\"640\" data-height=\"360\">\n" +
            "</a>\n" +
            "</figure>\n" +
            "<p class=\"section txt\">探身之后效果</p>\n" +
            "<p class=\"section txt\">即使被对手一中，只要不是一枪爆头，第一时间爬起来，分辨出对手方向（关注小地图），围绕着大树躲到射击来源的反方向，再打包等待转移。</p>\n" +
            "<figure class=\"section img\">\n" +
            "<a class=\"img-wrap\" style=\"padding-bottom: 56.25%;\" data-href=\"https://00.imgmini.eastday.com/mobile/20180303/20180303022524_ad8dbf9665accf4f8005255d60dce33e_8.jpeg\" data-size=\"640x360\">\n" +
            "<img width=\"100%\" alt=\"\" src=\"https://00.imgmini.eastday.com/mobile/20180303/20180303022524_ad8dbf9665accf4f8005255d60dce33e_8.jpeg\" data-weight=\"640\" data-width=\"640\" data-height=\"360\">\n" +
            "</a>\n" +
            "</figure>\n" +
            "<p class=\"section txt\">探身射击</p>\n" +
            "<p class=\"section txt\">千万不要一动不动，因为已经有玩家发现了你，同样，也不要在起来的瞬间找对手在哪里，和对手刚枪，躲起来才是正确的判断，只要不是1v1，总会有第三个玩家帮我们消灭他！<a style=\"color:#4B7BB7;\" href=\"http://toutiao.eastday.com/search.html?kw=斜坡\">斜坡</a></p>\n" +
            "<figure class=\"section img\">\n" +
            "<a class=\"img-wrap\" style=\"padding-bottom: 56.25%;\" data-href=\"https://00.imgmini.eastday.com/mobile/20180303/20180303022524_ad8dbf9665accf4f8005255d60dce33e_9.jpeg\" data-size=\"640x360\">\n" +
            "<img width=\"100%\" alt=\"\" src=\"https://00.imgmini.eastday.com/mobile/20180303/20180303022524_ad8dbf9665accf4f8005255d60dce33e_9.jpeg\" data-weight=\"640\" data-width=\"640\" data-height=\"360\">\n" +
            "</a>\n" +
            "</figure>\n" +
            "<p class=\"section txt\">危险的斜坡</p>\n" +
            "<p class=\"section txt\">还有一种掩体就是斜坡，斜坡作为跑到大树掩体下的中继站，也是不错的选择之一，不过斜坡主要依赖的是角度，如果角度过低，或者对手在其他方向，反而把自己暴露的阳光之下，成为了对手的活靶子。</p>\n" +
            "<figure class=\"section img\">\n" +
            "<a class=\"img-wrap\" style=\"padding-bottom: 56.25%;\" data-href=\"https://00.imgmini.eastday.com/mobile/20180303/20180303022524_ad8dbf9665accf4f8005255d60dce33e_10.jpeg\" data-size=\"640x360\">\n" +
            "<img width=\"100%\" alt=\"\" src=\"https://00.imgmini.eastday.com/mobile/20180303/20180303022524_ad8dbf9665accf4f8005255d60dce33e_10.jpeg\" data-weight=\"640\" data-width=\"640\" data-height=\"360\">\n" +
            "</a>\n" +
            "</figure>\n" +
            "<p class=\"section txt\">斜坡也是一种不错的掩体</p>\n" +
            "<p class=\"section txt\">综上，刺激战场是一个打败第二名才能吃鸡的游戏。在决赛圈，尽可能少移动，如果运气太差，只能听天由命了。即使是看到第三名玩家也不要急着出手，等待时机。</p>\n" +
            "<p class=\"section txt\">切记不要激动，不要激动，不要激动，重要的事情说三遍。</p>\n" +
            "<p class=\"section txt\">最后，预祝大家大吉大利，今晚吃鸡！</p>\n" +
            "\n" +
            "</div>";

}
