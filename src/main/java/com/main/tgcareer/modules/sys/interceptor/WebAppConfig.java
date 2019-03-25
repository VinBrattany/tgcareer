package com.main.tgcareer.modules.sys.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 *
 * 注册拦截器
 */
@Configuration
public class WebAppConfig extends WebMvcConfigurationSupport {

    @Bean
    public TokenInterceptor tokenInterceptor() {
        return new TokenInterceptor();
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry){
//        registry.addInterceptor(new TokenInterceptor()).addPathPatterns("/**")
//                .excludePathPatterns("/sys/login/**","/college/college/**","/user/user/**","/stat/**");
        super.addInterceptors(registry);
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");

        super.addResourceHandlers(registry);
    }

}