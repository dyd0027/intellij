package com.example.interceptor.interceptor;

import com.example.interceptor.annotation.Auth;
import com.example.interceptor.exception.AuthException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;

@Component
@Slf4j
public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI();
        URI uri = UriComponentsBuilder.fromUriString(request.getRequestURI())
                .query(request.getQueryString())
                .build()
                .toUri();

        log.info("request url : {}",url);
        boolean hasAnnotation = checkAnnotation(handler, Auth.class);
        log.info("has annotation : {}",hasAnnotation);
        //나의 서버는 모두 public 으로 동작을 하는데
        //단! Auth 권한을 가진 요청에 대해서는 세션,쿠키를 검사하겠다!
        if(hasAnnotation){
            //권한체크
            String query = uri.getQuery();
            log.info("query : {},",query);
            if(query.equals("name=yh")){
                return true;
            }
            throw new AuthException();
        }

        //반환값에 따라 interceptor에서 더 이상 들어갈지 말지 판단함. -> true:o, false:x // 따라서 주석 위에서 움직여야 함
        return true;
    }
    private boolean checkAnnotation(Object handler,Class clazz){
        //resource 가 js, html
        if(handler instanceof ResourceHttpRequestHandler){
            return true;
        }
        //annotation 체크
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        if(null != handlerMethod.getMethodAnnotation(clazz) || null != handlerMethod.getBeanType().getAnnotation(clazz)){
            //@Auth가 있을경우~
            return true;
        }

        return false;
    }

}
