package com.oes.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Slf4j
public class JwtCheckGatewayFilterFactory extends AbstractGatewayFilterFactory<JwtCheckGatewayFilterFactory.Config> {

    public JwtCheckGatewayFilterFactory() {
        super(Config.class);
    }

    private String[] skipAuthUrls;

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            String url = exchange.getRequest().getURI().getPath();

            //跳过不需要验证的路径
            if (null != skipAuthUrls && Arrays.asList(skipAuthUrls).contains(url)) {
                return chain.filter(exchange);
            }

            String auth = exchange.getRequest().getHeaders().getFirst("Authorization");

            if (auth == null) {
                ServerHttpResponse resp = exchange.getResponse();
                resp.setStatusCode(HttpStatus.FOUND);
                resp.getHeaders().add("Location", "http://localhost:9527/loginUser");
                return resp.setComplete();
            }
            //4.如果存在,继续执行
            return chain.filter(exchange); //继续向下执行
        };
    }

    public static class Config {
    }
}
