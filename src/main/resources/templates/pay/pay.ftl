<@compress single_line=true>
    <#assign ctx=request.getContextPath()>
<!doctype html>
<html>
<head>
    <meta http-equiv="Expires" CONTENT="0">
    <meta http-equiv="Cache-Control" CONTENT="no-cache">
    <meta http-equiv="Pragma" CONTENT="no-cache">
    <title>专家UC</title>
</head>
<script type="text/javascript">
    var ctx = "${ctx}";
</script>
<body style="background: #ececec;">

<script>
    window.location.href=ctx+"/order_settlement/${lsh!""}";
</script>
</body>
</html>
</@compress>