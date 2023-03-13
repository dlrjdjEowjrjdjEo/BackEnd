package com.medic.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class MypageController {

    @GetMapping("/check")
    public String check() {
        return "Hello MyPage Controller";
    }
}
