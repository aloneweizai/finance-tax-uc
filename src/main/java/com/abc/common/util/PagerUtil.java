package com.abc.common.util;

/**
 * @Author liuqi
 * @Date 2017/5/12 14:07
 */
public class PagerUtil {

    public static PagerSpec calculatePagerSpec(PagerSpec pagerSpec){
        long totalItems     = pagerSpec.getTotalItems();
        int page            = pagerSpec.getCurrentPage();
        int perPageNum      = pagerSpec.getPerPageNum();
        if (page < 1)
        {
            page    = 1;
        }
        int offset      = (page - 1) * perPageNum;
        int totalPage   = (int) Math.ceil(totalItems / (double) perPageNum);
        if (offset >= totalItems)
        {
            page    = totalPage;
            offset  = (page - 1) * perPageNum;
        }
        if (offset < 0)
        {
            page    = 1;
            offset  = 0;
        }
        pagerSpec.setCurrentPage(page);
        pagerSpec.setOffset(offset);
        pagerSpec.setTotalPage(totalPage);

        if(page <= 3){
            pagerSpec.setNavOffsest(1);
        }else{
            if(totalPage <=5){
                pagerSpec.setNavOffsest(1);
            }else{
                if(totalPage-page >= 2){
                    pagerSpec.setNavOffsest(page-2);
                }else{
                    pagerSpec.setNavOffsest(totalPage-4);
                }
            }
        }
        return pagerSpec;
    }

    public static String pager(String url, PagerSpec ps) {
        int totalPage = ps.getTotalPage();
        int currentPage = ps.getCurrentPage();
        StringBuilder sb = new StringBuilder();
        sb.append("<table><tbody class='js-page-tr'><tr><td align=\"center\">");
        sb.append("共").append(ps.getTotalItems()).append("条 每页 ").append(ps.getPerPageNum()).append(" 条</li>");
        //如果当前页是首页，首页 和 上一页 禁用
        if(currentPage <= 1){
            sb.append("<input class='btn btn-default btn-xs' value='首 页'  type='button'>");
            sb.append("<input class='btn btn-default btn-xs' value='上一页' type='button'>");
            currentPage = 1;
        }else{
            //如果不是首页，首页 和 上一页 开启
            sb.append("<input class='btn btn-default btn-xs' value='首 页' onclick='javascript:window.location.href=\"").append(url.replace("[:page]", "1")).append("\"' type='button'>");
            sb.append("<input class='btn btn-default btn-xs' value='上一页' onclick='javascript:window.location.href=\"").append(url.replace("[:page]", String.valueOf(currentPage - 1))).append("\"' type='button'>");
        }
        //如果当前是尾页  尾页  和 下一页 禁用
        if(currentPage >= totalPage){
            sb.append("<input class='btn btn-default btn-xs' value='下一页' type='button'>");
            sb.append("<input class='btn btn-default btn-xs' value='尾 页'  type='button'>");
            currentPage = totalPage;
        }else{
            //如果不是尾页   尾页  和 下一页  开启
            sb.append("<input class='btn btn-default btn-xs' value='下一页' onclick='javascript:window.location.href=\"").append(url.replace("[:page]", String.valueOf(currentPage + 1))).append("\"' type='button'>");
            sb.append("<input class='btn btn-default btn-xs' value='尾 页' onclick='javascript:window.location.href=\"").append(url.replace("[:page]", String.valueOf(totalPage))).append("\"' type='button'>");
        }
        sb.append("当前").append(" ").append(currentPage).append("/").append(totalPage).append(" ").append("页 转到第");
        sb.append("<input id='_goPs' onkeypress='if(event.keyCode==13){$(\"#_goPage\").click();return false;}' style='width:50px' type='text'>页");
        sb.append("<input id='_goPage' onclick='javascript:abc.goPage()' data-url='"+ url +"' class='btn btn-default btn-xs' value='转' type='button'>");
        sb.append("</td></tr></tbody></table>");
        return sb.toString();
    }

    public static String ajaxPager(String url, PagerSpec ps) {
        int totalPage = ps.getTotalPage();
        int currentPage = ps.getCurrentPage();
        StringBuilder sb = new StringBuilder();
        sb.append("<tr><td align=\"center\">");
        sb.append("共").append(ps.getTotalItems()).append("条 每页 ").append(ps.getPerPageNum()).append(" 条</li>");
        //如果当前页是首页，首页 和 上一页 禁用
        if(currentPage <= 1){
            sb.append("<input class='btn btn-default btn-xs' value='首 页'  type='button'>");
            sb.append("<input class='btn btn-default btn-xs' value='上一页' type='button'>");
            currentPage = 1;
        }else{
            //如果不是首页，首页 和 上一页 开启
            sb.append("<input class='btn btn-default btn-xs' value='首 页' onclick='javascript:abc.ajaxPage(\"").append(url.replace("[:page]", "1")).append("\")' type='button'>");
            sb.append("<input class='btn btn-default btn-xs' value='上一页' onclick='javascript:abc.ajaxPage(\"").append(url.replace("[:page]", String.valueOf(currentPage - 1))).append("\")' type='button'>");
        }
        //如果当前是尾页  尾页  和 下一页 禁用
        if(currentPage >= totalPage){
            sb.append("<input class='btn btn-default btn-xs' value='下一页' type='button'>");
            sb.append("<input class='btn btn-default btn-xs' value='尾 页'  type='button'>");
            currentPage = totalPage;
        }else{
            //如果不是尾页   尾页  和 下一页  开启
            sb.append("<input class='btn btn-default btn-xs' value='下一页' onclick='javascript:abc.ajaxPage(\"").append(url.replace("[:page]", String.valueOf(currentPage + 1))).append("\")' type='button'>");
            sb.append("<input class='btn btn-default btn-xs' value='尾 页' onclick='javascript:abc.ajaxPage(\"").append(url.replace("[:page]", String.valueOf(totalPage))).append("\")' type='button'>");
        }
        sb.append("当前").append(" ").append(currentPage).append("/").append(totalPage).append(" ").append("页 转到第");
        sb.append("<input id='_goPs' onkeypress='if(event.keyCode==13){$(\"#_goPage\").click();return false;}' style='width:50px' type='text'>页");
        sb.append("<input id='_goPage' onclick='javascript:abc.ajaxGoPage()' data-url='"+ url +"' class='btn btn-default btn-xs' value='转' type='button'>");
        sb.append("</td></tr>");
        return sb.toString();
    }
}
