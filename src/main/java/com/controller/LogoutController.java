package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/logout")
public class LogoutController {

    @GetMapping
    public void logout(HttpServletRequest request, HttpServletResponse response){
        Cookie rememberMeCookie = new Cookie("remember-me", "");

        rememberMeCookie.setMaxAge(0);

        response.addCookie(rememberMeCookie);
    }
}
