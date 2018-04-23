package com.abc.application;

import com.abc.argResolver.PagerSpecArgumentResolver;
import com.abc.argResolver.UserArgumentResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.nio.charset.Charset;
import java.util.List;

/**
 * @Author admin
 * @Date 2017/7/11 16:04
 */
@Configuration
public class WebAppConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {



        registry.addInterceptor(new UserSecurityInterceptor()).addPathPatterns("/**")
        .excludePathPatterns("/login")
        .excludePathPatterns("/login.html")
        .excludePathPatterns("/login_v2.html")
        .excludePathPatterns("/zhuce.html")
        .excludePathPatterns("/zhuce_v2.html")
        .excludePathPatterns("/sms.html")
        .excludePathPatterns("/validatecode.html")
        .excludePathPatterns("/forgot.html")
        .excludePathPatterns("/logout.html")
        .excludePathPatterns("/phone_forgot.html")
        .excludePathPatterns("/external_index.html")
        .excludePathPatterns("/tp_forgot.html")
        .excludePathPatterns("/forgotpassword.html")
        .excludePathPatterns("/callback.html")
        .excludePathPatterns("/weixin_callback.html")
        .excludePathPatterns("/orderpay/success.html")
        .excludePathPatterns("/smsforgot.html")
        .excludePathPatterns("/saveforgot.html")
        .excludePathPatterns("/saveforgot_v2.html")
        .excludePathPatterns("/phone_forgot_login.html")
        .excludePathPatterns("/forgot_phone_new_fangxiang.html")
        .excludePathPatterns("/rsa.html")
        .excludePathPatterns("/rsa_v2.html")
        .excludePathPatterns("/clientjump.html")
        .excludePathPatterns("/logout_session.html")
        .excludePathPatterns("/logout_index.html")
        .excludePathPatterns("/overtime.html")
        .excludePathPatterns("/client_currency.html")
        .excludePathPatterns("/client_currency_v2.html")
        .excludePathPatterns("/clientjump_v2.html")
        .excludePathPatterns("/logout_client.html")
        .excludePathPatterns("/error")
        .excludePathPatterns("/client_token.html")
        .excludePathPatterns("/500.html")
        .excludePathPatterns("/404.html")
        .excludePathPatterns("/weixin/callback.html")
        .excludePathPatterns("/phone_forgot_login_phone.html")
        .excludePathPatterns("/phonelongsms.html")
        .excludePathPatterns("/toPayServiceCharge")
        .excludePathPatterns("/extension.html")
//        .excludePathPatterns("/company")
//        .excludePathPatterns("/company/**")
        ;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new UserArgumentResolver());
        argumentResolvers.add(new PagerSpecArgumentResolver());
    }

    @Bean
    public HttpMessageConverter<String> responseBodyConverter() {
        StringHttpMessageConverter converter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
        return converter;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        converters.add(responseBodyConverter());
    }
}
