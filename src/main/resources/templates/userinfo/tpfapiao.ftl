<@compress single_line=true>
<#assign ctx=request.getContextPath()>
<html>
<head>
    <meta charset="utf-8">
    <#include "../common/meta.ftl">
    <title>财税专家UC</title><link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.css" />
            <link rel="stylesheet" type="text/css" href="${ctx}/css/webpage_main.css">
            <link rel="stylesheet" type="text/css" href="${ctx}/css/iconfont.css">
    <script type="text/javascript">
        var ctx = "${ctx}";
    </script>
    <style>
        .grzx_main_bjgrzl table tr td,th{
            font-size: 14px;
            line-height: 40px;
        }
        select,input,textarea{
            width: 350px;
        }

    </style>
</head>

<body >
<div class="grzx_main_rt">退票</div>

    <div class="modal-body">
        <div class="grzx_main_bjgrzl">
            <form name="form1" action="article_add.php">
                <table class="layui-table" lay-size="sm">
                    <tr>
                        <td>退票原因</td>
                        <td>
                            <select name="xueli">
                                <option value="1">请选择</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>备注</td>
                        <td>
                            <textarea rows="2" cols="40"></textarea>
                        </td>
                    </tr>
                    <tr>
                        <td width="100">物流公司</td>
                        <td>
                            <input type="text" size="30" />
                        </td>
                    </tr>
                    <tr>
                        <td>快递编号 </td>
                        <td>
                            <input type="text" size="30" />
                        </td>
                    </tr>

                </table>
            </form>
        </div>
    </div>
    <div class="modal-footer">
        <button type="button" class="btn btn-default">重置</button>
        <button type="button" class="btn btn-primary">提交</button>
    </div>

<script data-main="${ctx}/js/abc/fapiao" src="${ctx}/js/require.js"></script>
</body>
</html>
</@compress>