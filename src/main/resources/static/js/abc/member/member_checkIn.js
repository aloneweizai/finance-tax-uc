require(["../../config"], function () {
    require(["jquery.full"], function ($) {
        $(function () {
            var year=$(".year").text().trim();
            var date=$(".date").text().trim();
            var month=$(".month").text().trim();

            var $thead = $('.thead'),
                $tbody = $('.tbody'),
                $button = $('.button'),
                slidate = new Date(year,month,date),
                _nullnei = '',
                de = slidate.getDate();

            //获取当前年/月/日
            function ymd(num) {
                return num < 10 ? "0" + num : num;
            }

            var year = slidate.getFullYear()
            var month = slidate.getMonth();
            var current = slidate.getMonth();
            $(".year").text(year + '年');
            $(".date").text(ymd(de) + '日');
            $(".month").text(ymd(month + 1) + '月');

            //上个月/下个月
            $(".ym-box i").click(function () {
               // debugger;
                var i = $(this).data("index");
                if (i == 0) {
                    if (month > 0) {
                        month--;
                        lastLogs();

                        $(".month").text(ymd(month + 1) + '月');
                        mon();
                        dat();
                        add();
                    }
                } else if (i == 1) {
                    if (month < 11 && parseInt(month) < parseInt(current)) {
                        month++;
                        lastLogs();

                        $(".month").text(ymd(month + 1) + '月');
                        mon();
                        dat();
                        add();
                    }
                }
            })

            function lastLogs(){
                //获取上个月签到记录
                var yearMon = month + 1;
                var yearMonth = year + "-" + yearMon ;
                var mot = mon("monthFirst");
                if (mot == 0) {
                    mot = mot + 7;
                }
                $.ajax({
                    type:"GET",
                    url:ctx + "/member/checkIn/" + yearMonth ,
                    contentType: "application/json",
                    dataType: "JSON",
                    success:function(data){
                        if (typeof(data.errorCode) == 'undefined') {
                            if (typeof(data.soacode) == "undefined") {
                                if(data.data.dataList != null && data.data.dataList.length > 0){
                                    for(var i= 0;i<data.data.dataList.length;i++) {
                                        var item = data.data.dataList[i];
                                        var date = new Date(item.date);
                                        if(item.check){
                                            var index = date.getDate();
                                            var $img = $("<img src='../images/riliq.png'>");
                                            $slitd.eq(parseInt(index - 2 + mot)).find("p").append($img);
                                        }
                                    }
                                }
                            } else {
                                abc.msg(data.message,{icon: 2});
                            }
                        } else {
                            eval(data.js)
                        }

                    }
                });
            }

            //获取当月的1日等于星期几
            function mon(monthFirst) {
                return monthFirst = new Date(slidate.getFullYear(), parseInt(month), 1).getDay();
            }

            //获取当月天数
            function dat(d) {
                return d = new Date(slidate.getFullYear(), parseInt(month + 1), 0).getDate();
            }

            //遍历日历网格
            for (var i = 2; i <= 7; i++) {
                _nullnei += "<tr>";
                for (var j = 1; j <= 7; j++) {
                    _nullnei += '<td></td>';
                }
                _nullnei += "</tr>";
            }
            $tbody.html(_nullnei);

            //添加节点删除节点
            var $slitd = $tbody.find("td");

            function add() {
                var conter = dat('d');
                var mot = mon("monthFirst");
                if (mot == 0) {
                    mot = mot + 7;
                }
                //删除节点
                $(".home tbody tr td").empty();
                //添加节点
                for (var i = 1; i < conter + 1; i++) {
                    is = i < 10 ? '0' + i : i;
                    $slitd.eq(i - 2 + mot).html("<p>" + is + "</p>")
                }
                ;
                //小于||大于当前月份变灰色
                var $home = $(".tbody tr td p");
                if (current < month || current > month) {
                    $home.css("color", "#c2c2c2");
                }
                ;
                //给当前日加样式
                $slitd.eq(de - 2 + mot).find("p").addClass('color')

                //小于当前日期&&等于当前月添加灰色和手指
                var ps = parseInt($(".tbody tr .color").text());
                $home.each(function () {
                    if (parseInt($(this).text()) < ps && current == month) {
                        $(this).addClass("gray")
                    }
                });

                //补签点击事件
                $home.on("click", function () {
                    var index = $(this).text();
                    var mon = current + 1;
                    var date = year + "-" + mon + "-" + index;
                    var pcheck = $(".tbody").attr("precheck");
                    if (parseInt($(this).text()) < ps && current == month) {
                        abc.confirm('补签需要消耗'+ pcheck +'积分',{icon:3}, function () {
                                $.ajax({
                                    type: "POST",
                                    url: ctx + "/member/check/" + date,
                                    contentType: "application/json",
                                    dataType: "JSON",
                                    success: function (data) {
                                        if (typeof(data.errorCode) == 'undefined') {
                                            if (typeof(data.soacode) == "undefined") {
                                                if (data.data.code == '2000') {
                                                    var $img = $("<img src='../images/riliq.png'>");
                                                    $slitd.eq(parseInt(index - 2 + mot)).find("p").append($img);
                                                    abc.msg('补签成功',{icon:1});
                                                    //$("#points",parent.document).text(points);
                                                } else {
                                                    abc.msg(data.data.message,{icon: 2});
                                                }
                                            } else {
                                                abc.msg(data.message,{icon: 2});
                                            }
                                        } else {
                                            eval(data.js)
                                        }
                                    }
                                });
                            });
                    }
                });

                //签到点击事件
                $button.one("click", function () {
                    var yearMon = current + 1;
                    var date = year + "-" + yearMon + "-" + de;
                    console.log($slitd.eq());
              /*      if (current == month) {*/
                        $.ajax({
                            type: "POST",
                            url: ctx + "/member/checkForId.html"+ "?date="+date ,
                            contentType: "application/json",
                            dataType: "JSON",
                            success: function (data) {
                                if (typeof(data.errorCode) == 'undefined') {
                                    if (typeof(data.soacode) == "undefined") {
                                        if (data.data.code == '2000') {
                                            //$("#jifen").text("今日已领取"+ data.data.data +"分，明日继续签到");
                                            var $img = $("<img src='../images/riliq.png'>");
                                            $slitd.eq(parseInt(de - 2 + mot)).find("p").append($img);
                                            abc.msg('今日签到成功领取'+ data.data.data +'分，明日继续签到',{icon:1});
                                            $button.text("您已签到");
                                            //$("#points",parent.document).text(points);
                                        } else {
                                            abc.alert(data.data.message,{icon: 2});
                                        }
                                    } else {
                                        abc.msg(data.message,{icon: 2});
                                    }
                                } else {
                                    eval(data.js)
                                }

                            }
                        });
               /*     }*/
                });
            }
            add();

            function logs() {
                //获取本月已签到的日期
                var yearMon = current + 1;
                var yearMonth = year + "-" + yearMon;
                var mot = mon("monthFirst");
                if (mot == 0) {
                    mot = mot + 7;
                }
                $.ajax({
                    type: "GET",
                    url: ctx +"/member/checkIn/" + yearMonth,
                    contentType: "application/json",
                    dataType: "JSON",
                    success: function (data) {
                        if (typeof(data.errorCode) == 'undefined') {
                            if (typeof(data.soacode) == "undefined") {
                                if (data.data.dataList != null && data.data.dataList.length > 0) {
                                    for (var i = 0; i < data.data.dataList.length; i++) {
                                        var item = data.data.dataList[i];
                                        var date = new Date(item.date);
                                        var index = date.getDate();
                                        if (item.check) {
                                            var $img = $("<img src='../images/riliq.png'>");
                                            $slitd.eq(parseInt(index - 2 + mot)).find("p").append($img);
                                        }
                                        if(index == de){
                                           if(item.check){
                                               $button.text("您已签到");
                                           }else{
                                               $button.text("立即签到");
                                           }
                                        }
                                    }
                                }
                            } else {
                                abc.msg(data.message,{icon: 2});
                            }
                        } else {
                            eval(data.js)
                        }

                    }
                });
            }
            logs();
        })
    })
})