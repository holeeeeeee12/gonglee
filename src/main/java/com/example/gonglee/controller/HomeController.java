package com.example.gonglee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String root() {
        return "redirect:/worldcup"; // /worldcup으로 리다이렉트
    }

    @GetMapping("/home")
    public String home() {
        return "redirect:/worldcup"; // /worldcup으로 리다이렉트
    }

    @GetMapping("/worldcup")
    public String worldcup() {
        return "worldcup"; // src/main/resources/templates/worldcup.html 반환
    }
}