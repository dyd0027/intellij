package com.sp.fc.web.teacher;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Set;

@Component
public class TeacherManager implements AuthenticationProvider, InitializingBean {

    private HashMap<String, Teacher> teacherDB = new HashMap<>();

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if(authentication instanceof UsernamePasswordAuthenticationFilter){
            UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
            if(teacherDB.containsKey(token.getCredentials())){
                getAuthenticationToken(token.getName());
            }
            return null;
        }

        TeacherAuthenticationToken token = (TeacherAuthenticationToken) authentication;
        if(teacherDB.containsKey(token.getCredentials())){
            return getAuthenticationToken(token.getCredentials());
        }
        return null;
    }

    private TeacherAuthenticationToken getAuthenticationToken(String id) {
        Teacher teacher = teacherDB.get(id);
        return TeacherAuthenticationToken.builder()
                .principal(teacher)
                .details(teacher.getUsername())
                .authenticated(true)
                .build();
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication == TeacherAuthenticationToken.class ||
                authentication == UsernamePasswordAuthenticationToken.class;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Set.of(
                new Teacher("yong2", "용휘선생님", Set.of(new SimpleGrantedAuthority("ROLE_TEACHER")))
        ).forEach(s->
            teacherDB.put(s.getId(), s)
        );
    }
}
