package com.example.gonglee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // 기존 "/" 경로를 "/home"으로 변경
    @GetMapping("/")
    public String home() {
        return "home";  // home.html이 있다면
    }

    // 또는 아예 삭제하고 싶다면 메서드를 지워도 됨
}