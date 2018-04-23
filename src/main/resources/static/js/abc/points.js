require(["../config"],function(){
    require(["jquery.full","../abc/util/pagination","jquery.jedate"],function($){

        // $(".layui-tab-title>li").click(function(){
        //     $(this).addClass('layui-this').siblings('.layui-this').removeClass('layui-this');
        //     $(".layui-tab-item").eq($(this).index()).addClass('layui-show').siblings('.layui-show').removeClass('layui-show');
        //     //清空日期选择框
        //     $("#begintime").val("");
        //     $("#endtime").val("");
        // });

        $('li[type-id="jifen"]').click(function (){
            window.location.href=ctx+'/pointsExchange/points.php?page=1&type=01';
        });

        $('li[type-id="duihuan"]').click(function (){
            window.location.href=ctx+'/pointsExchange/points.php?page=1&type=02';
        });

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

        if(window.location.hash=='#jfcz'){
            $('#chongzhi').trigger("click");
        }

        /**积分明细根据日期查询*/
        $(document).on("click","#pointQuery",function(){
            var start = $("#begintime").val();
            var end = $("#endtime").val();
            var url=ctx+'/pointsExchange/points.php?'+'time1='+start+"&time2="+end+"&page=1&type=01";
            window.location.href=url;
            // $.ajax({
            //     type: "GET",
            //     url: ctx + '/pointsExchange/pointsDate',
            //     data:{start:start,end:end},
            //     dataType:"html",
            //     success: function (data) {
            //         debugger;
            //         $('.point').html("");
            //         $('.point').append(data);
            //     }
            // });
        });

        /**积分兑换根据日期查询*/
        $(document).on("click","#orderQuery",function(){
            var start = $("#duiHuanBegin").val();
            var end = $("#duiHuanEnd").val();
            var url=ctx+'/pointsExchange/points.php?'+'time1='+start+"&time2="+end+"&page=1&type=02";
            window.location.href=url;
            // $.ajax({
            //     type: "GET",
            //     url: ctx + '/pointsExchange/pointsDate',
            //     data:{start:start,end:end},
            //     dataType:"html",
            //     success: function (data) {
            //         debugger;
            //         $('.exchange').html("");
            //         $('.exchange').append(data);
            //     }
            // });
        });



        function pointRefresh(data){
            $('#pointsTable tr').html();
            var content = new Array();
            if(data.data.dataList.length>0){
                for (var i = 0; i < data.data.dataList.length; i++) {
                    var item = data.data.dataList[i];
                    content.push("<tr align='center'>");
                    if (item.channel != null) {
                        content.push("<td align='center'>" + item.channel + "</td>");
                    } else {
                        content.push("<td align='center'></td>");
                    }

                    if (item.exp > 0) {
                        content.push("<td align='center'>" + "<div class='grzx_wdjf_dhjl_red'>" + "+" + item.points + "</div>" + "</td>");
                    } else {
                        content.push("<td align='center'>" + "<div class='grzx_wdjf_dhjl_blue'>" + item.points + "</div>" + "</td>");
                    }
                    content.push("<td align='center'>" + item.sums + "</td>");
                    //var adcDate = jeDate.format(new Date(item.createTime), "yyyy年MM月dd日 HH:mm:ss")
                    content.push("<td align='center'>" + new Date(item.createTime) + "</td>");
                    if (item.remark != null) {
                        content.push("<td align='center'>" + item.remark + "</td>");
                    } else {
                        content.push("<td align='center'></td>");
                    }
                    content.push("</tr>");
                }
            }
            $('#pointsTable').html(content.join(""));
        }


        function exchangeRefresh(data){
            $("#duihuanTable tr").html();
            var content = new Array();
            if(data.data.length > 0){
                for(var i= 0;i<data.data.length;i++){
                    var item =data.data[i];
                    content.push("<tr align='center'>");

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
                    content.push("<td>"+ new Date(item.createTime) +"</td>");
                    //content.push("<td>"+jeDate.format(new Date(item.lastUpdate),"yyyy年MM月dd日 HH:mm:ss")+"</td>");
                    content.push("</tr>");
                }
                $("#duihuanTable").html(content.toString());
            }
        }

        function pagePoint(data){
            //分页标签刷新
            $("#pageOne").html("");
            var content = new Array();
            var pagerSpec = data.pagerSpec1;
            if (pagerSpec != null && pagerSpec.totalPage>0){
                content.push("<input type=\"hidden\" name=\"curr_link\" value= " + pagerSpec.link + "?replace('[:page]',"+  pagerSpec.currentPage + ")>");
                content.push("<nav aria-label=\"Page navigation\" style=\"text-align: right;\">");
                content.push("<ul class=\"pagination\">");
                content.push("<li><a href=\"javascript:void(0)\" data-href= "  + pagerSpec.link + "?replace('[:page]'," + (pagerSpec.currentPage-1) + ")" +" aria-label=\"Previous\"");
                if(pagerSpec.currentPage > 1){
                    content.push("class=\"js_page_previous\"");
                }
                content.push("><span aria-hidden=\"true\">&laquo;</span></a></li>");
                if(pagerSpec.totalPage>0){
                    var i = 1;
                    for(var index = pagerSpec.navOffsest;index<=pagerSpec.totalPage ;index++){
                        content.push("<li data-page-index="+ index +"><a href= "  + pagerSpec.link +"?replace('[:page]'," + index +") ");
                        if(index == pagerSpec.currentPage){
                            content.push("style=\"background: #14B9D5\""+">" + index );
                        }
                        content.push("</a></li>");
                        if(i == 5)break;
                        i++;
                    }
                }
                content.push(" <li><a href=\"javascript:void(0)\" data-href= " + pagerSpec.link + "?replace('[:page]'," +  (pagerSpec.currentPage+1) +")" +"  aria-label=\"Next\"");
                if( pagerSpec.currentPage < pagerSpec.totalPage){
                    content.push("class=\"js_page_next\"" );
                }
                content.push("><span aria-hidden=\"true\">&raquo;</span></a>");
                content.push("</li></ul></nav>");
                //console.log(content.join(""));
            }
            $("#pageOne").html(content.join(""));
        }


        function pageExchange(data){
            //分页标签刷新
            $("#pageTwo").html("");
            var content = new Array();
            var pagerSpec = data.pagerSpec2;
            if (pagerSpec != null && pagerSpec.totalPage>0){
                content.push("<input type=\"hidden\" name=\"curr_link\" value= " + pagerSpec.link + "?replace('[:page]',"+  pagerSpec.currentPage + ")>");
                content.push("<nav aria-label=\"Page navigation\" style=\"text-align: right;\">");
                content.push("<ul class=\"pagination\">");
                content.push("<li><a href=\"javascript:void(0)\" data-href= "  + pagerSpec.link + "?replace('[:page]'," + (pagerSpec.currentPage-1) + ")" +" aria-label=\"Previous\"");
                if(pagerSpec.currentPage > 1){
                    content.push("class=\"js_page_previous\"");
                }
                content.push("><span aria-hidden=\"true\">&laquo;</span></a></li>");
                if(pagerSpec.totalPage>0){
                    var i = 1;
                    for(var index = pagerSpec.navOffsest;index<=pagerSpec.totalPage ;index++){
                        content.push("<li data-page-index="+ index +"><a href= "  + pagerSpec.link +"?replace('[:page]'," + index +") ");
                        if(index == pagerSpec.currentPage){
                            content.push("style=\"background: #14B9D5\""+">" + index );
                        }
                        content.push("</a></li>");
                        if(i == 5)break;
                        i++;
                    }
                }
                content.push(" <li><a href=\"javascript:void(0)\" data-href= " + pagerSpec.link + "?replace('[:page]'," +  (pagerSpec.currentPage+1) +")" +"  aria-label=\"Next\"");
                if( pagerSpec.currentPage < pagerSpec.totalPage){
                    content.push("class=\"js_page_next\"" );
                }
                content.push("><span aria-hidden=\"true\">&raquo;</span></a>");
                content.push("</li></ul></nav>");
                //console.log(content.join(""));
            }
            $("#pageTwo").html(content.join(""));
        }
    })
})
