package com.sinse.electroshop.controller.shop;

import com.sinse.electroshop.domain.Product;
import com.sinse.electroshop.model.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;

    @GetMapping("/product/list")
    @ResponseBody
    public List<Product> getList() {
        return service.getList();
    }

    //상품 상세 요청 처리
    @GetMapping("/product/detail")
    public String getDetail(@RequestParam(required = false, name = "product_id") int productId, Model model){

        Product product=service.getDetail(productId);
        model.addAttribute("product", product);

        return "electro/product";
    }
}




