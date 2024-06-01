package com.example.testsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity  //이 어노테이션으로 인해 스프링 시큐리티가 이 클래스를 관리할 수 있게됨
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http
                .authorizeHttpRequests((auth) -> auth //스프링부트 3.1.X버전부터는 lambda 형식으로 작성해줘야함
//                        상단부터 아래 순서로 기능함. 상단부터 모두 permitAll 되는중임!
                        .requestMatchers("/","/login").permitAll()
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .requestMatchers("/my/**").hasAnyRole("ADMIN", "USER")
                        .anyRequest().authenticated()
                );

//        login 페이지
        http
                .formLogin((auth) -> auth.loginPage("/login")
                        .loginProcessingUrl("/loginProc")
                        .permitAll()
                );

//        CSRF (사이트위변조방지)설정이 스프링시큐리티에는 자동으로 되어있음.
//        이게 동작하면, 로그인이 진행될때 CSRF 토큰도 같이 보내져야 진행이 됨.
//        개발 중에는 CSRF 를 잠시 disable 시켜줌
        http
                .csrf((auth)-> auth.disable());

        return http.build();
    }
}
