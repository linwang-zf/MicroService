package com.oes.eduauthentication.security.handler;

import com.oes.eduauthentication.util.HttpStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: Jiang Xiaodan
 * @Date: 2020/8/8 16:01
 */
@Component("customAuthenticationFailureHandler")
@Slf4j
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        log.info("登录失败");
        httpServletResponse.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.getWriter().write(e.getMessage());
    }
}
