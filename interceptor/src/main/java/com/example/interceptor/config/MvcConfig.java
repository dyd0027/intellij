package com.example.interceptor.config;

import com.example.interceptor.interceptor.AuthInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor //생성자 만들어 주는거
public class MvcConfig implements WebMvcConfigurer { //interceptor 등록해주기
    private final AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor).addPathPatterns("/api/private/*");//해당 url패턴만 검사하겠다
//        registry.addInterceptor(authInterceptor); 이거는 모든애들 검사하겠다.
    }
}
