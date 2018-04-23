package com.abc.argResolver;

import com.abc.common.util.CommonUtils;
import com.abc.common.util.PagerSpec;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author liuqi
 * @Date 2017/5/31 15:06
 */
public class PagerSpecArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterType() == PagerSpec.class;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        PagerSpec pagerSpec = new PagerSpec();
        String page = nativeWebRequest.getParameter("page");
        if(CommonUtils.checkEmpty(page)){
            pagerSpec.setCurrentPage(1);
        }else{
            pagerSpec.setCurrentPage(Integer.valueOf(page));
        }
        String perPageNum = nativeWebRequest.getParameter("perPageNum");
        if(CommonUtils.checkEmpty(perPageNum)){
            pagerSpec.setPerPageNum(10);
        }else{
            pagerSpec.setPerPageNum(Integer.valueOf(perPageNum));
        }
        HttpServletRequest req = (HttpServletRequest)nativeWebRequest.getNativeRequest();

        String queryStr = req.getQueryString();
        if(CommonUtils.nullOrBlank(queryStr)){
            queryStr = "page=[:page]";
        }else{
            if(queryStr.contains("page=")){
                queryStr = queryStr.replaceAll("page=\\d*","page=[:page]");
            }else{
                queryStr = queryStr + "&page=[:page]";
            }
        }
        String link = new StringBuilder(req.getRequestURI()).append("?").append(queryStr).toString();
        pagerSpec.setLink(link);
        return pagerSpec;
    }
}
