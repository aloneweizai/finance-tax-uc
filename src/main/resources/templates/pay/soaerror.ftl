<@compress single_line=true>
<#assign ctx=request.getContextPath()>
<!doctype html>
<html>
<head>
<#include "../common/meta.ftl">
    <title>专家UC</title>
</head><link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
<script type="text/javascript">
    var ctx = "${ctx}";
</script>
<style>
    #abnormal{
        display: flex;
        flex-direction: column;
        justify-content: space-between;
        align-items: center;
    }
    #abnormal .abnormal-img{
        position: relative;
    }
    #abnormal .abnormal-img p{
        position: absolute;
        top: 196px;
        left: 206px;
        font-size: 24px;
        color: #666666;
    }
    #abnormal .abnormal-btn button{
        width: 200px;
        line-height: 42px;
        border-radius: 6px;
        border: 2px solid #c2c2c2;
        background: #f1f1f1;
        font-size: 18px;
        color: #666666;
    }
    #abnormal .abnormal-btn button:hover{
        color: #80cc8b;
        border-color: #80cc8b;
        background-color: #fff;
        cursor: pointer;
    }
</style>
<body>
<div id="abnormal">
    <div class="abnormal-img">
        <img src="${ctx}/images/abnormal.jpg" alt="1">
        <p>订单已失效!</p>
    </div>
    <div class="abnormal-btn">
        <button type="button" onclick="tiaozhuan()">点击返回</button>
    </div>
</div>
</body>
<script>
    function tiaozhuan(){
        window.parent.location.replace('${ucurl}/index.html');
    }
</script>
</html>
</@compress>