package com.example.interceptor.annotation;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
public @interface Auth { //해당 어노테이션을 사용하면 세션 있는 사용자만 들어올수 있게
}
