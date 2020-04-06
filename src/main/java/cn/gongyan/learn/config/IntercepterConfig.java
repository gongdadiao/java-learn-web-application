/***********************************************************
 * @Description : 拦截器配置
 * @author      : 龚研
 * @date        : 2019-05-16 23:40
 * @qq          : 1085704190
 ***********************************************************/
package cn.gongyan.learn.config;

import cn.gongyan.learn.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class IntercepterConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 拦截user下的api
        System.out.println("装载拦截器");
        registry.addInterceptor(loginInterceptor).
                addPathPatterns("/user/**").
                addPathPatterns("/exam/**").
                addPathPatterns("/res/**").
                addPathPatterns("/auth/**").
                addPathPatterns("/record/**").
                addPathPatterns("/code/**");
    }

}
