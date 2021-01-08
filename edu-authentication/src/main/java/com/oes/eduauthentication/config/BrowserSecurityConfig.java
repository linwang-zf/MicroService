package com.oes.eduauthentication.config;

import com.oes.eduauthentication.service.CustomPasswordEncoder;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @Author: Jiang Xiaodan
 * @Date: 2020/8/8 19:37
 */
@Component
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {



   /* @Resource
    private AuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Resource
    private AuthenticationFailureHandler customAuthenticationFailureHandler;

    @Resource
    private DataSource dataSource;

    @Resource
    private UserDetailsService customUserDetailsService;


    *//*
        配置token存到数据库
     *//*
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        //建表语句 只需要使用一次
        // tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/swagger-ui.html")
                .antMatchers("/v2/**")
                .antMatchers("/swagger-resources/**")
                .antMatchers("/configuration/**")
                .antMatchers("/webjars/**", "/images/**")
                .antMatchers("/druid/**")
                .antMatchers("/login.html")
                .antMatchers("/rsa/publicKey");
    }*/

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        /*http.formLogin()
                .loginPage("/login.html")
                .usernameParameter("account")
                .loginProcessingUrl("/user/login")
                .successHandler(customAuthenticationSuccessHandler)
                .failureHandler(customAuthenticationFailureHandler)
                .and()
                .rememberMe()
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(604800) //一周过期
                .userDetailsService(userDetailsService()) //从数据库拿到后使用这个做登录
                .and()
                .authorizeRequests()
                //未登录可访问的资源
                .antMatchers("/authentication/require", "/user/login").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf()
                .disable();*/

        http.authorizeRequests()
                .requestMatchers(EndpointRequest.toAnyEndpoint()).permitAll()
                .antMatchers("/rsa/publicKey").permitAll()
                .anyRequest().authenticated();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
/*
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new CustomPasswordEncoder();
    }*/


}
