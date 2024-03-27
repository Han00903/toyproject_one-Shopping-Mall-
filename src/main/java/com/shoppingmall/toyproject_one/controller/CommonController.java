package com.shoppingmall.toyproject_one.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class CommonController {

    @GetMapping(value = "toyproject_one/common")
    public String ad_main() {
        return "common";
    }
}
