package com.oes.filter;

import com.oes.constant.CookiesConstant;
import com.oes.util.jwt.JwtUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Map;


@Component
@Slf4j
public class TokenFilter implements GlobalFilter, Ordered {
    @Value("${server.skip-auth-urls}")
    private String skipAuthUrls;


    @SneakyThrows
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String url = exchange.getRequest().getURI().getPath();
        log.info("url="+exchange.getRequest().getURI());
        log.info("url="+url);
        //跳过不需要验证的路径
        if(null != skipAuthUrls && url != skipAuthUrls){
            return chain.filter(exchange);
        }
        HttpCookie httpCookie = exchange.getRequest().getCookies().getFirst(CookiesConstant.COOKIES_KEY);
        if(httpCookie == null){
            log.info("***cookies 为 null*****");
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            return exchange.getResponse().setComplete();
        }
        String token = httpCookie.getValue();
        Map<String, Object> tokenMap  = JwtUtil.parseJWT(token);
        int roleId = (int) tokenMap.get("role");
        ResponseCookie responseCookie = ResponseCookie.from("roleId", String.valueOf(roleId)).build();
        exchange.getResponse().addCookie(responseCookie);//是先filter再断言匹配吗？
        return chain.filter(exchange);
    }

    //getOrder方法中的返回值的数据越小，过滤器的级别越高
    @Override
    public int getOrder() {
        return 0;
    }
}
