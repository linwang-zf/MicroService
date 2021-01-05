package com.oes.controller;

import com.oes.dto.ReturnData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {
    @GetMapping("/fallback")
    public ReturnData fallback() {
        return new ReturnData(404,"服务忙，请稍后再试",null);
    }
}
