package com.hy.springbootquickstart.controller;

import com.hy.springbootquickstart.service.LoginService;
import com.hy.springbootquickstart.util.RestResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/login")
    public RestResponse<Object> login(String username, String password) {
        loginService.login(username, password);
        return RestResponse.ok();
    }
}
