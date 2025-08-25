package com.ssg.catchtableapp.controller;

import com.ssg.catchtableapp.model.restaurant.RestaurantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    @Value("${map.kakao.jsKey}")
    private String kakaoMapApiKey;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("kakaoMapApiKey", kakaoMapApiKey);
        return "home/home";
    }
}
