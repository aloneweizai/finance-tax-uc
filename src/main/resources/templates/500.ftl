<@compress single_line=true>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<#assign ctx=request.getContextPath()>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>500</title>
    <style>
        *{margin: 0;padding: 0; font-family: "微软雅黑";}
        a{ text-decoration: none; color: white;}
        .photo1{ position:absolute; width: 700px; height: 400px; top: 50%; left: 50%; margin-left: -350px; margin-top: -200px; background-image: url(${ctx}/images/baocuo.jpg);}
        .photo1 .anniu{ width: 90px; height: 30px; position: absolute; top:255px; left: 384px; background-color: #00b4d4;font-size: 14px; text-align: center; line-height: 30px; border-radius: 4%;}
        .photo1 .text{ position: absolute; top:200px; left: 384px; color: #666;}
    </style>
</head>

<body>

<div class="photo1">
    <div class="text">错误代码:500</div>
    <a href="javascript:href();"><div class="anniu">返回</div></a>
</div>
<script type="text/javascript">
    function href(){
        if(typeof loc!="undefined"){
            window.parent.location.href='${ucurl!""}/external_index.html';
        }else{
            window.parent.location.href='${cswurl!""}';
        }
    }
</script>
</body>

</html>
</@compress>