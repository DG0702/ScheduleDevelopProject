package com.project.scheduledelevopproject.config;

import com.project.scheduledelevopproject.filter.LoginFilter;
import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {

    @Bean
    public FilterRegistrationBean loginFilter(){

        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();

        // Filter 등록
        filterRegistrationBean.setFilter(new LoginFilter());

        // Filter 적용 순서
        filterRegistrationBean.setOrder(1);

        // Filter 적용할 URL 설정 -> "/*" : 전체설정
        filterRegistrationBean.addUrlPatterns("/*");

        return filterRegistrationBean;
    }
}
