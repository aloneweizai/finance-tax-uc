<@compress single_line=true>
<#assign ctx=request.getContextPath()>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>我的vip特权</title>
        <#include "../common/meta.ftl">
        <script type="text/javascript">var ctx= "${ctx}"</script>
        <script src="${ctx}/js/jquery-1.11.1.min.js" type="text/javascript"></script>
        <script src="${ctx}/js/bootstrap.js" type="text/javascript"></script>
        <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
        <link href="${ctx}/css/bootstrap.css" rel="stylesheet" type="text/css">
        <link href="${ctx}/css/webpage_main.css" rel="stylesheet" type="text/css">
        <link href="${ctx}/css/iconfont.css" rel="stylesheet" type="text/css">
        <script src="${ctx}/js/webpage_main.js" type="text/javascript"></script>

    </head>
    <body>
    <div class="grzx_main_rt">
        我的vip特权
    </div>

    <!-- 个人中心内容右侧 -->
    <div class="grzx_wdjf_dhjl">
        <div class="ktviptequan">
            <div class="viptequantishi">
                <div class="icon_vip"><i class="iconfont icon-huiyuan1"></i>您还未获开通VIP特权</div>
                <span>开通VIP可享受八大特权</span>
            </div>
            <div class="ktvip">
                <a href="#">开通VIP</a>
                <span>享受最优质的服务</span>
            </div>
            <div class="clear"></div>
        </div>
        <ul class="icontequan">
            <li><div class="iconyuan"><i class="iconfont icon-fengxian"></i></div><span>涉税风险预警</span></li>
            <li><div class="iconyuan"><i class="iconfont icon-ruanjiankaifa"></i></div><span>软件功能特权</span></li>
            <li><div class="iconyuan"><i class="iconfont icon-kehu-copy"></i></div><span>客户优先</span></li>
            <li><div class="iconyuan"><i class="iconfont icon-huiyuan"></i></div><span>VIP尊享专线</span></li>
            <li><div class="iconyuan"><i class="iconfont icon-shangmenfuwu"></i></div><span>上门服务</span></li>
            <li><div class="iconyuan"><i class="iconfont icon-houtai-huojian"></i></div><span>办税无忧服务</span></li>
            <li><div class="iconyuan"><i class="iconfont icon-yuanchengyanshiicon"></i></div><span>专家远程服务</span></li>
            <li><div class="iconyuan"><i class="iconfont icon-shijian"></i></div><span>提醒服务</span></li>
        </ul>
    </div>

    <!-- Modal -->
    <div tabindex="-1" class="modal fade" id="myModal" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document" style="width: 400px;">
            <div class="modal-content">
                <div class="modal-header">
                    <button class="close" aria-label="Close" type="button" data-dismiss="modal"><span aria-hidden="true">×</span></button>
                    <h4 class="modal-title" id="myModalLabel">取消订单</h4>
                </div>
                <div class="modal-body">
                    <form>
                        <div class="form-group">
                            <label class="control-label" for="recipient-name">取消原因:</label>
                            <input class="form-control" id="recipient-name" type="text">
                        </div>
                        <div class="form-group">
                            <label class="control-label" for="message-text">备注:</label>
                            <textarea class="form-control" id="message-text"></textarea>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-default" type="button">重置</button>
                    <button class="btn btn-primary" type="button">确定</button>
                </div>
            </div>
        </div>
    </div>
    </body>
</html>
</@compress>