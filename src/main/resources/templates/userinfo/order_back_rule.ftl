<@compress single_line=true>
<#assign ctx=request.getContextPath()>
<html>
<head>
    <meta charset="utf-8">
<#include "../common/meta.ftl">
    <title>退换货规则</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/iconfont.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/easyui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/webpage_main.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <script type="text/javascript">
        var ctx = "${ctx}";
    </script>
</head>

<body>
    <div class="grzx_main_rt">退换货规则
       <a href="${ctx}/orderback/back.php" class="layui-btn layui-btn-small huiyuanfanhui"><i class="iconfont">&#xe700;</i>退换货申请</a>
   </div>
    <div class="clear"></div>

   <textarea  rows="26" style="width: 96%;padding: 10px">
       1. 外包装及附件赠品，退换货时请一并退回，如有破损或丢失，请自行寻找相似包装代替。
       2. 关于物流损：请您在收货时务必仔细验货，如发现商品外包装或商品本身外观存在异常，需当场向配送人员指出，并拒收整个包裹；如您在收货后发现外观异常，请在收货24小时内提交退换货申请。如超时未申请，将无法受理。
       3. 关于商品实物与网站描述不符：京东商城保证所出售的商品均为正品行货，并与时下市场上同样主流新品一致。但因厂家会在没有任何提前通知的情况下更改产品包装、产地或者一些附件，所以我们无法确保您收到的货物与商城图片、产地、附件说明完全一致。
       4. 如果您在使用时对商品质量表示置疑，您可出具相关书面鉴定，会按照国家法律规定予以处理。
       5、如果您符合以上退换服务规格，那么在下方填写退换货原因，填写退换地址等即可，填写完成后，点击底部的“提交”即可。
   </textarea>
</body>
</html>
</@compress>