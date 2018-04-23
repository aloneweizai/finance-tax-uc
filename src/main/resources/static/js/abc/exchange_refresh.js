require(["../config"],function(){
    require(["jquery.full","../abc/util/pagination","jquery.jedate"],function($){

        $(".layui-tab-title>li").eq(1).addClass('layui-this').siblings('.layui-this').removeClass('layui-this');
        $(".layui-tab-item").eq(1).addClass('layui-show').siblings('.layui-show').removeClass('layui-show');

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



        var start1 = {
            format: 'YYYY-MM-DD',
            maxDate: $.nowDate({DD:0}), //最大日期
            choosefun: function(elem, val, date){
                end1.minDate = date; //开始日选好后，重置结束日的最小日期
                endDates1();
            }
        };
        var end1 = {
            format: 'YYYY-MM-DD',
            minDate: $.nowDate({DD:0}), //设定最小日期为当前日期
            maxDate: '2099-06-16 23:59:59', //最大日期
            choosefun: function(elem, val, date){
                start1.maxDate = date; //将结束日的初始值设定为开始日的最大日期
            }
        };

        function endDates1() {
            //将结束日期的事件改成 false 即可
            end.trigger = false;
            $("#duiHuanEnd").jeDate(end1);
        }
        $('#duiHuanBegin').jeDate(start1);
        $('#duiHuanEnd').jeDate(end1);



        $(".grzx_main_rt_nav>ul>li").eq(1).addClass('hover').siblings('.hover').removeClass('hover');
        $(".page_main_xxk").eq(1).show().siblings(".page_main_xxk").hide();

        $(function (){
            $(".grzx_main_rt_nav>ul>li").click(function(){
                $(this).addClass('hover').siblings('.hover').removeClass('hover');
                $(".page_main_xxk").eq($(this).index()).show().siblings(".page_main_xxk").hide();
            })
        });

        $("#duihuan").click(function () {
            window.location.href= ctx +"/member/member_integral.html";
        });
        $("#renwu").click(function () {
            window.location.href= ctx +"/userinfo/task.php";
        });
        $("#chongzhi").click(function () {
            window.location.href= ctx +"/integral/integral_payment.html";
        });
        $("#rule").click(function () {
            window.location.href= ctx +"/pointsExchange/user_point_rule.html";
        });


        /**积分明细根据日期查询*/
        $("#pointQuery").click(function () {
            var startDate = $("#begintime").val();
            var endDate = $("#endtime").val();

            //全局刷新
            window.location.href= ctx + "/pointsExchange/points?start=" + startDate +"&end="+endDate+"&type=01";

            //局部刷新
            /*$.ajax({
                type:"POST",
                url:"pointsForDate.html",
                data:JSON.stringify({start:startDate,end:endDate}),
                async: false,
                contentType: "application/json",
                dataType: "JSON",
                success:function(data){
                    if(data.data.code=='2000'){
                        //debugger;
                        if(data.data.dataList !=null) {
                            $('#pointsTable tr').html();
                            var content = new Array();
                            for(var i= 0;i<data.data.dataList.length;i++){
                                var item = data.data.dataList[i];
                                content.push("<tr align='center'>");
                                if(item.channel != null){
                                    content.push("<td align='center'>"+item.channel+"</td>");
                                }else{
                                    content.push("<td align='center'></td>");
                                }

                                if(item.exp > 0){
                                    content.push("<td align='center'>" + "<div class='grzx_wdjf_dhjl_red'>"+"+"+item.points + "</div>" +"</td>");
                                }else{
                                    content.push("<td align='center'>" + "<div class='grzx_wdjf_dhjl_blue'>"+"-"+item.points + "</div>" +"</td>");
                                }
                                content.push("<td align='center'>"+item.sums+"</td>");
                                var adcDate = abc_date.format(new Date(item.createTime),"yyyy年MM月dd日 HH:mm:ss")
                                content.push("<td align='center'>" + adcDate + "</td>");
                                if(item.remark != null){
                                    content.push("<td align='center'>"+item.remark+"</td>");
                                }else{
                                    content.push("<td align='center'></td>");
                                }
                                content.push("</tr>");
                            }
                            $('#pointsTable').html(content.toString())
                        }else{
                            $('#pointsTable tr').empty();
                        }
                    }else{
                        layer.alert(data.data.message);
                    }
                }
            });*/
        });


        /**积分兑换根据日期查询*/
        $("#orderQuery").click(function () {
            var startDate = $("#duiHuanBegin").val();
            var endDate = $("#duiHuanEnd").val();

            //全局刷新
            window.location.href= ctx + "/pointsExchange/exchange?start=" + startDate +"&end="+endDate+"&type=02";

            //局部刷新
            /*$.ajax({
                type:"POST",
                url:"duiHuanForDate.html",
                data:JSON.stringify({startTime:startDate,endTime:endDate}),
                async: false,
                contentType: "application/json",
                dataType: "JSON",
                success:function(data){
                    if(data != null){
                        //debugger;
                        if(data.data.length > 0){
                            $("#duihuanTable tr").html();
                            var content = new Array();
                            for(var i= 0;i<data.data.length;i++){
                                var item =data.data[i];
                                content.push("<tr align='center'>");
                                if(item.imageUrl != null){
                                    content.push("<td><div class='grzx_wdjf_dhjl_img' ><img src=" + item.imageUrl+"/></div></td>");
                                }else{
                                    //content.push("<td></td>");
                                }

                                var productBOList = item.orderProductBOList;
                                var productBO = null;
                                if(productBOList != null && productBOList.length >0){
                                    for(var j = 0;j <productBOList.length;j++){
                                        var orderProduct = productBOList[j];
                                        productBO = orderProduct.productBO;
                                        if(orderProduct.name != null){
                                            content.push("<td><div class='grzx_wdjf_dhjl_r'><h3>"+orderProduct.name+"</h3>编号："+item.orderNo+"</div></td>");
                                        }else{
                                            content.push("<td><div class='grzx_wdjf_dhjl_r'>编号："+item.orderNo+"</div></td>");
                                        }
                                        break;
                                    }
                                }
                                if(productBO.sellingPrice != null){
                                    content.push("<td><div class='grzx_wdjf_dhjl_blue'>" + "-" + productBO.sellingPrice + "</div></td>");
                                }else{
                                    content.push("<td><div class='grzx_wdjf_dhjl_blue'></div></td>");
                                }

                                var user= item.user;
                                if(user.points != null){
                                    content.push("<td>"+user.points+"</td>");
                                }else{
                                    content.push("<td>0</td>");
                                }
                                content.push("<td>"+abc_date.format(new Date(item.lastUpdate),"yyyy年MM月dd日 HH:mm:ss")+"</td>");
                                content.push("</tr>");
                                $("#duihuanTable").html(content.toString());
                            }
                        }else{
                            $("#duihuanTable ").empty("");
                        }
                    }else{
                        layer.alert("查询失败！");
                    }
                }
            });*/
        });
    })
})
