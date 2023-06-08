package com.example.dbproject.common;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//实现Mvc的控制
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index.html").setViewName("index");
        registry.addViewController("/shop-list.html").setViewName("shop-list");
        registry.addViewController("/single-product.html").setViewName("single-product");
        registry.addViewController("/login.html").setViewName("login");
        registry.addViewController("/register.html").setViewName("register");
        registry.addViewController("/user.html").setViewName("user");
        registry.addViewController("/order.html").setViewName("order");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/loginCustomer","/register","/login.html","/register.html","/css/*","/fonts/*","/img/*","/js/*","/lib.rs-plugin/*","/ecommerce/*");
    }

}
