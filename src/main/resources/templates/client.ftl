<@compress single_line=true>
<#assign ctx=request.getContextPath()>
<!doctype html>
<html>
<head>
    <title>专家UC</title>
</head>
<script type="text/javascript">
    var ctx = "${ctx}";
</script>
<style>
    .overlay {
        position: fixed;
        top: 0px;
        left: 0px;
        z-index: 10001;
        display:block;
        filter:alpha(opacity=60);
        background-color: #777;
        opacity: 0.5;
        -moz-opacity: 0.5;
        width: 100%;
        height:100%;
    }
    .loading-tip {
        z-index: 10002;
        position: fixed;
        display:block;
        top:50%;
        left:50%;
    }
    .loading-tip img {
        width:100px;
        height:100px;
    }
</style>
<body style="background: #ececec;">
<!-- 遮罩层DIV -->
<div id="overlay" class="overlay"></div>
<!-- Loading提示 DIV -->
<div id="loadingTip" class="loading-tip">
    <img src="${ctx}/images/loading.gif" />
</div>
<script>
    window.location.href="${url!""}";
</script>
</body>
</html>
</@compress>