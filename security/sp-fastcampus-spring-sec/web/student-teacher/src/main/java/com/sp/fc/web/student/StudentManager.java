package com.sp.fc.web.student;

import com.sp.fc.web.teacher.Teacher;
import com.sp.fc.web.teacher.TeacherAuthenticationToken;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class StudentManager implements AuthenticationProvider, InitializingBean {

    private HashMap<String, Student> studentDB = new HashMap<>();

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if(authentication instanceof UsernamePasswordAuthenticationFilter){
            UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
            if(studentDB.containsKey(token.getCredentials())){
                getAuthenticationToken(token.getName());
            }
            return null;
        }

        StudentAuthenticationToken token = (StudentAuthenticationToken) authentication;
        if(studentDB.containsKey(token.getCredentials())){
            Student student = studentDB.get(token.getCredentials());
            return getAuthenticationToken(token.getCredentials());
        }
        return null;
    }
    private StudentAuthenticationToken getAuthenticationToken(String id) {
        Student student = studentDB.get(id);
        return StudentAuthenticationToken.builder()
                .principal(student)
                .details(student.getUsername())
                .authenticated(true)
                .build();
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication == StudentAuthenticationToken.class ||
                authentication == UsernamePasswordAuthenticationToken.class;
    }

    public List<Student> myStudents(String teacherId){
        return studentDB.values().stream().filter(s->s.getTeacherId().equals(teacherId))
                .collect(Collectors.toList());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Set.of(
                new Student("yong", "용휘", Set.of(new SimpleGrantedAuthority("ROLE_STUDENT")),"yong2"),
                new Student("in", "인수", Set.of(new SimpleGrantedAuthority("ROLE_STUDENT")),"yong2"),
                new Student("sy", "서연", Set.of(new SimpleGrantedAuthority("ROLE_STUDENT")),"yong2")
        ).forEach(s->
            studentDB.put(s.getId(), s)
        );
    }
}
