package com.board.DemoBoard.config;

import com.board.DemoBoard.service.CustomOAuth2UserService;
import com.board.DemoBoard.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration /*extends WebSecurityConfigurerAdapter */ /*F/O*/{

    private final UserServiceImpl userServiceImpl;
    private final CustomOAuth2UserService customOAuth2UserService;
    // 특정 HTTP 요청에 대한 웹 기반 보안 구성
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
            .csrf().disable() //csrf 공격 방어 해제,
            .headers().frameOptions().disable() // h2-console 화면 사용을 위한 옵션
            .and()
                // 권한 설정
                .authorizeRequests() //권한 부여를 위한 메서드
                    .antMatchers( "/"
                                            ,"/api/**"
                                            ,"/signup"
                                            ,"/signup/**"
                                            ,"/formLogin"
                                            ,"/loginProc"
                                            ,"/oauth2/**"
                                            ,"/error"
                                            ,"/login/**"
                                            ,"/loginProc"
                                            ,"/vue/css/**"
                                            ,"/vue/images/**"
                                            ,"/vue/js/**"
                                            ,"/vue/h2-console/**"
                                            ,"/swagger-ui.html"
                                ).permitAll()
                    .anyRequest().permitAll()

            .and()

                // form 로그인 설정
                .formLogin() //Security가 지원하는 폼 형식 로그인 방식
                    .loginPage("/formLogin") // 로그인 페이지
                    .loginProcessingUrl("/loginProc") // 로그인 정보를 해당 URL로 전달하면 Security가 자동 처리
                    .defaultSuccessUrl("/") // 로그인 완료 후 리턴 URL
            .and() 
                //로그아웃 설정
                .logout()
                    .logoutSuccessUrl("/")
            .and()
                // oauth 설정
                .oauth2Login()
                    .loginPage("/formLogin")
                /*
                .authorizationEndpoint() // 인증 엔드포인트 설정
                        .baseUri("/oauth2/authorize") // OAuth 2.0 인증 엔드포인트
                    .and()
                    .redirectionEndpoint() // 리디렉션 엔드포인트 설정
                        .baseUri("/oauth2/code/*") // OAuth 2.0 인증 완료 후 리디렉션할 경로
                .and()*/
                    .userInfoEndpoint()
                        .userService(customOAuth2UserService);

        return http.build();
    }


}
