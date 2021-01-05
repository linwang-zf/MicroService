package com.oes.eduauthentication.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oes.eduauthentication.security.CustomUserDetails;
import com.oes.eduauthentication.security.JwtTokenUtil;
import com.oes.eduauthentication.util.HttpResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashMap;

/**
 * @Author: Jiang Xiaodan
 * @Date: 2020/8/8 15:46
 */
@Component("customAuthenticationSuccessHandler")
@Slf4j
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        log.info("登录成功");
//        httpServletResponse.setContentType("application/json;charset=UTF-8");
//        httpServletResponse.getWriter().write(objectMapper.writeValueAsString(authentication));
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        StringBuffer as = new StringBuffer();
        for (GrantedAuthority authority : authorities) {
            as.append(authority.getAuthority())
                    .append(",");
        }
        CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();
        String token = JwtTokenUtil.sign(user.getId(), authentication.getName(), as.toString());
        HashMap<String, Object> data = new HashMap<>();
        data.put("token", token);
        httpServletResponse.setContentType("application/json;charset=utf-8");
        PrintWriter out = httpServletResponse.getWriter();
        out.write(new ObjectMapper().writeValueAsString(HttpResult.ok("登录成功", data)));
        out.flush();
        out.close();
    }
}
