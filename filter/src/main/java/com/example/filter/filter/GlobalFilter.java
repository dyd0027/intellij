package com.example.filter.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;


//@Component //spring에서 직접 관리하게 component달아줌
@WebFilter(urlPatterns = "/api/user/*")
@Slf4j
public class GlobalFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //전처리
        ContentCachingRequestWrapper httpServletRequest = new ContentCachingRequestWrapper((HttpServletRequest) request);
        ContentCachingResponseWrapper httpServletResponse = new ContentCachingResponseWrapper((HttpServletResponse) response);

        String url = httpServletRequest.getRequestURI();

        chain.doFilter(httpServletRequest,httpServletResponse);

        //후처리
        String reqContent = new String(httpServletRequest.getContentAsByteArray());
        log.info("request url : {}, resquestBody : {}",url,reqContent);

        String resContent = new String(httpServletResponse.getContentAsByteArray()); //바이트로 모든 내용 뽑아내서 response에 아무내용도 안담김.
        int httpStatus = httpServletResponse.getStatus();
        httpServletResponse.copyBodyToResponse();
        log.info("response status : {}, responseBody : {}",httpStatus,resContent);


    }
}
