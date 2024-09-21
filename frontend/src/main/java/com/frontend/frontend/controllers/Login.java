package com.frontend.frontend.controllers;

import com.frontend.frontend.services.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("VIEW")
public class Login {
    @RequestMapping
    public String display()
    {
        return "index.html";
    }


    private LoginService loginService;

    public Login(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public String login(@RequestParam("email") String email, @RequestParam("password") String password) {
        Object xd = loginService.login(email, password);
        System.out.println(xd);
        return "success";
    }

}
