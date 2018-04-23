<@compress single_line=true>
<#assign ctx=request.getContextPath()>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-dns-prefetch-control" content="on" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <#include "../common/meta.ftl">
        <title>取消订单</title>
        <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
        <link rel="stylesheet" type="text/css" media="screen and (min-width:481px)" href="${ctx}/css/webpage_main.css">
        <link rel="stylesheet" type="text/css" media="screen and (max-width:480px)" href="${ctx}/css/style-480.css">
        <link rel="stylesheet" type="text/css" media="screen and (max-width:480px)" href="${ctx}/css/uc-m-480.css">
        <link rel="stylesheet" type="text/css" href="${ctx}/css/iconfont.css">

        <script type="text/javascript">
            var ctx = "${ctx}";
        </script>
    </head>
    <body>
   <div class="grzx_main_rt pc_zishiying">取消订单
       <a href="${ctx}/userinfo/order.php" class="layui-btn layui-btn-small huiyuanfanhui"><i class="iconfont">&#xe700;</i>我的订单</a>
   </div>
   <div class="clear"></div>
   <div class="tuiHuo tableBorder">
       <form id="cancelOrder" name="cancelForm">
           <div class="form-group layui-form">
               <label class="control-label" for="recipient-name" style="line-height: 26px">取消原因:</label><br/>
               <select class="form-control" id="recipient-name"  style="width: 400px;height: 40px">
                   <option value="cuoxuan" selected>选错了款式</option>
                   <option value="chongfu">重复下单</option>
                   <option value="qita">其他</option>
               </select>
           </div>
           <div class="form-group" >
               <label class="control-label" for="message-text" style="line-height: 26px">详细描述:</label><br/>
               <textarea placeholder="请输入取消原因" rows="5" class="layui-textarea" id="message-text"></textarea>
           </div>
           <div class="modal-footer">
              <#-- <button type="button" id="fanhui" class="btn btn-default">返回</button>-->
               <button id="queding" class="layui-btn" order-id="${orderNo!""}" order-status="${orderStatus!""}" type="button">提交</button>
           </div>
       </form>
   </div>

    </body>
    <script data-main="${ctx}/js/abc/order" src="${ctx}/js/require.js"></script>
</html>
</@compress>