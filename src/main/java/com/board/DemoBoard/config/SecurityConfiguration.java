package com.board.DemoBoard.config;

import com.board.DemoBoard.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration /*extends WebSecurityConfigurerAdapter */ /*F/O*/{

    private final UserService userService;

    // 특정 HTTP 요청에 대한 웹 기반 보안 구성
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return http.csrf().disable() //csrf 공격 방어 해제,
                .authorizeRequests() //권한 부여를 위한 메서드
                .antMatchers("/user/**").authenticated() //인증이 필요한 URI 설정
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                .anyRequest().permitAll() // 특정 URL 제외 모두 인가 처리
                .and()
                .formLogin() //Security가 지원하는 폼 형식 로그인 방식
                .loginPage("/login") // 로그인 페이지
                .loginProcessingUrl("/loginProc") // 로그인 정보를 해당 URL로 전달하면 Security가 자동 처리
                .defaultSuccessUrl("/") // 로그인 완료 후 리턴 URL
                .and().build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
