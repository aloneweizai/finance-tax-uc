require(["../config"], function () {
    require(["jquery.full"], function ($) {
        $(function () {

            $('.layui-tab-title li').click(function () {
                var taskType = $(this).attr("taskType");
                $.ajax({
                    type: "GET",
                    url: ctx + "/task/type?taskType=" + taskType,
                    dataType: "html",
                    success: function (data) {
                        try {
                            var json = eval("(" + data + ")");
                            if (typeof(json.errorCode) == 'undefined') {
                                if (typeof(json.soacode) == "undefined") {
                                    $('.layui-show').html(data);
                                } else {
                                    abc.msg(json.message, {icon: 2});
                                }
                            } else {
                                eval(json.js)
                            }
                        } catch (e) {
                            $('.layui-show').html(data);
                        }

                        $(".wanc").click(function () {
                            specialTask(this);
                        });

                        $(".daliyTask").click(function () {
                            daliyTask(this);
                        });


                        $("li[class='xinshou']").click(function () {
                            noviceTask(this);
                        });
                    }
                });
            });


            $(".wanc").click(function () {
                specialTask(this);
            });

            $(".daliyTask").click(function () {
                daliyTask(this);
            });


            $("li[class='xinshou']").click(function () {
                noviceTask(this);
            });

            $(".complete").click(function () {
                abc.msg("您的任务已完成！", {icon: 1});
            })

            function getUrlParam(skipUrl) {
                var start = skipUrl.indexOf("&&");
                var surl = skipUrl.substring(0, start);
                return surl;
            }

            function daliyTask(kj) {
                var task_status = $(kj).attr("status-id");
                var skipUrl = $(kj).attr("skip-id");
                if (task_status == "0") {
                    if (skipUrl != null && skipUrl.length != 0) {
                        if (skipUrl.indexOf("&&") > 0) {
                            skipUrl = getUrlParam(skipUrl);
                            var el = document.createElement("a");
                            document.body.appendChild(el);
                            el.href = skipUrl; //url 是你得到的连接
                            el.target = '_new'; //指定在新窗口打开
                            el.click();
                            document.body.removeChild(el);
                        } else {
                            window.location.href = skipUrl;
                        }
                    } else {
                        abc.msg("请到相关业务模块完成任务！", {icon: 2});
                    }
                } else {
                    abc.msg("您的任务已完成！", {icon: 1});
                }
            };

            function specialTask(kj) {
                var task_status = $(kj).attr("status-id");
                var skipUrl = $(kj).attr("skip-id");
                if (task_status == "0") {
                    if (skipUrl != null && skipUrl.length != 0) {
                        if (skipUrl.indexOf("&&") > 0) {
                            skipUrl = getUrlParam(skipUrl);
                            var el = document.createElement("a");
                            document.body.appendChild(el);
                            el.href = skipUrl; //url 是你得到的连接
                            el.target = '_new'; //指定在新窗口打开
                            el.click();
                            document.body.removeChild(el);
                        } else {
                            window.location.href = skipUrl;
                        }
                    } else {
                        abc.msg("请到相关业务模块完成任务！", {icon: 2});
                    }
                } else {
                    abc.msg("您的任务已完成！", {icon: 1});
                }
            };

            function noviceTask(kj) {
                var task_status = $(kj).attr("status-id");
                var skipUrl = $(kj).attr("skip-id");
                // debugger;
                if (task_status == "0") {
                    if (skipUrl != null && skipUrl.length != 0) {
                        if (skipUrl.indexOf("&&") > 0) {
                            skipUrl = getUrlParam(skipUrl);
                            var el = document.createElement("a");
                            document.body.appendChild(el);
                            el.href = skipUrl; //url 是你得到的连接
                            el.target = '_new'; //指定在新窗口打开
                            el.click();
                            document.body.removeChild(el);
                        } else {
                            window.location.href = skipUrl;
                        }
                    } else {
                        abc.msg("请到相关业务模块完成任务！", {icon: 2});
                    }
                } else {
                    abc.msg("您的任务已完成！", {icon: 1});
                }
            }
        })
    });
});