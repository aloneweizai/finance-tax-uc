require(["../config"],function(){
    require(["jquery.full","../abc/util/pagination","jquery.jedate"],function($){



        var start = {
            format: 'YYYY-MM-DD',
            maxDate: $.nowDate({DD:0}), //最大日期
            choosefun: function(elem, val, date){
                end.minDate = date; //开始日选好后，重置结束日的最小日期
                endDates();
            }
        };
        var end = {
            format: 'YYYY-MM-DD',
            minDate: $.nowDate({DD:0}), //设定最小日期为当前日期
            maxDate: '2099-06-16 23:59:59', //最大日期
            choosefun: function(elem, val, date){
                start.maxDate = date; //将结束日的初始值设定为开始日的最大日期
            }
        };

        function endDates() {
            //将结束日期的事件改成 false 即可
            end.trigger = false;
            $("#endtime").jeDate(end);
        }
        $('#begintime').jeDate(start);
        $('#endtime').jeDate(end);





        $("#btnQuery").click(function () {
            var startDate = $("#begintime").val();
            var endDate = $("#endtime").val();

            window.location.href = ctx + "/userinfo/expLog?start="+ startDate +"&end="+ endDate;

            //局部刷新
            /*$.ajax({
                type:"POST",
                url:"expForDate.html",
                data:JSON.stringify({start:startDate,end:endDate}),
                async: false,
                contentType: "application/json",
                dataType: "JSON",
                success:function(data){
                    if(typeof(data.soacode)=="undefined"){
                        if(data.data.code=='2000'){
                            if(data.data.dataList != null) {
                                $('#expTable tr').empty();
                                $.each(data.data.dataList, function (index, item) {
                                    var content = new Array();
                                    content.push("<tr align='center'>");
                                    content.push("<td>" + item.channel + "</td>");
                                    if (item.exp > 0) {
                                        content.push("<td><div class='grzx_wdjf_dhjl_red'>" + "+" + item.exp + "</div></td>");
                                    } else {
                                        content.push("<td><div class='grzx_wdjf_dhjl_blue'>" + "-" + item.exp + "</div></td>");
                                    }
                                    content.push("<td>" + item.sums + "</td>");
                                    content.push("<td>" + abc_date.format(new Date(item.createTime), "yyyy年MM月dd日 HH:mm:ss") + "</td>");
                                    content.push("<td>" + item.remark + "</td>");
                                    content.push("</tr>");
                                    $('#expTable').html(content.toString());
                                })
                            }else{
                                $('#expTable tr:not(:first)').empty();
                            }
                        }else{
                            layer.alert(data.data.message);
                        }
                    }else{
                        layer.msg(data.message);
                    }
                }
            });*/
        });

        //经验值明细规则
        $('#rule').click(function (){
            window.location.href=ctx+"/userinfo/user_exp_rule.html";
            $.iframeHeight();
        });



    })
})
