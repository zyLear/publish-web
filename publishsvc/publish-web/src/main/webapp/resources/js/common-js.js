/**
 * Created by xiezongyu on 2018/8/13.
 */



function BindSelected(parent, son) {
    $('#' + parent).click(function () {
        $('#' + son).removeClass("custom-hide").addClass('custom-show');
    });

    $('#' + parent).blur(function () {
        $('#' + son).removeClass("custom-show").addClass('custom-hide');
    })
}

var SelecterInit = function () {

    var iSelecter = new Object();
    iSelecter.init = function (params, data) {
        //生成弹窗
        var input = $("" + params);
        iSelecter.input = input;
        input.after("<div class='q-dropdown-select s-hide'></div>");
        var selecter = $(".q-dropdown-select");
        // $(document).click(function () {
        //     iSelecter.hide();
        // });


        $(".q-dropdown-select").empty();
        var array = data;
        var outerHTML = "<ol>";
        for (var i = 0; i < array.length; i++) {
            var ele = "" + array[i].wechatAccount +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                array[i].nickName;
            outerHTML += "<li ><a data-obj=" + JSON.stringify(array[i]) + ele + "</a></li>";


        }
        outerHTML += "</ol>";
        //生成弹窗
        $(".q-dropdown-select").append(outerHTML);


        //监听 输入框数字改变
        iSelecter.input.blur(function () {

            alert('ddd');

        });

    };

    iSelecter.show = function () {
        $(".q-dropdown-select").removeClass("s-hide").addClass('s-show');
    };
    iSelecter.hide = function () {
        $(".q-dropdown-select").removeClass("s-show").addClass('s-hide');
    };
    iSelecter.fresh = function (data) {


    };

    SelecterInit.clickItem = function (wechatAccount) {
        iSelecter.input.focus();
        iSelecter.input.val(wechatAccount);
        iSelecter.hide();
    };


    return iSelecter;
};
