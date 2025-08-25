package com.ssg.catchtableapp.controller;

import com.ssg.catchtableapp.model.restaurant.Restaurant;
import com.ssg.catchtableapp.model.restaurant.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OpenDataController {
    private final RestaurantService restaurantService;

    @GetMapping("/list")
    public List<Restaurant> getList() throws Exception {
        String xml = restaurantService.getList();
        return restaurantService.parseFromXmlString(xml);
    }

    @GetMapping("/restaurants/test-exception")
    public String testException() {
        restaurantService.testException();  // 강제로 예외 발생
        return "restaurants/list";
    }
}
