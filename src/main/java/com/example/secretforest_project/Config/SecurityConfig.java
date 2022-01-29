package com.example.secretforest_project.Config;

import com.example.secretforest_project.Jwt.FilterConfig;
import com.example.secretforest_project.Jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@RequiredArgsConstructor
@EnableWebSecurity // 시큐리티 활성화 -> 기본 스프링 필터체인에 등록
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() { // 비밀번호 암호화
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // .disable() => 사용하지 않겠다는 것을 표시함
                .csrf().disable() // csrf 토큰 검사를 비활성화
                // csrf : 웹의 취약점을 이용하여 사용자가 자신의 의지와는 무관하게 공격자가 의도한 행위를 요청하게 하는 공격하는 행위
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)// 세션을 사용하지 않음
                // 스프링 시큐리티가 생성하지도 않고 기존것을 사용하지도 않음
                .and()
                .formLogin().disable() // 폼 로그인 방식을 사용하지 않음
                // form : 사용자의 데이터를 서버에 전송하는 방법
                .httpBasic().disable() // Http basic Auth  기반으로 로그인 인증창이 뜸.  disable 시에 인증창 뜨지 않음.

                .authorizeRequests() // 시큐리티 처리에 HttpServletRequest를 이용한다는 것을 의미, HttpServletRequest 에 따라 요청을 제한
                // 권한 관리 대상을 지정하는 옵션으로 URL,메소드 별로 관리가 가능하다.
                .antMatchers(HttpMethod.GET, "/admin/report/board").authenticated() // 인증된 사용자에게만 허용
                .antMatchers(HttpMethod.GET, "/admin/report/comments").authenticated()
                .antMatchers(HttpMethod.PUT, "/admin/report/**/pass").authenticated()
                .antMatchers(HttpMethod.DELETE, "/admin/report/**/elmnt").authenticated()

                .antMatchers(HttpMethod.GET, "/admin/cnsrs/board").authenticated()
                .antMatchers(HttpMethod.PUT, "/admin/cnsrs/**/pass").authenticated()
                .antMatchers(HttpMethod.DELETE, "/admin/cnsrs/**/elmnt").authenticated()

                .antMatchers(HttpMethod.GET, "/admin/board").authenticated()
                
                .anyRequest().permitAll()// 나머지 url들은(.anyRequest()) 무조건 접근 허용(.permitAll())한다.

                .and().apply(new FilterConfig(jwtTokenProvider));
    }

}