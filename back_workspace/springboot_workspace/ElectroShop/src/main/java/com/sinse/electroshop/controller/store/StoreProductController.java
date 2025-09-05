package com.sinse.electroshop.controller.store;

import com.sinse.electroshop.controller.dto.ProductDTO;
import com.sinse.electroshop.domain.Product;
import com.sinse.electroshop.domain.Store;
import com.sinse.electroshop.model.product.ProductService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/store")
@RequiredArgsConstructor
public class StoreProductController {
    private final ProductService productService;

    @GetMapping("/product/registform")
    public String registForm() {
        return "store/product/regist";
    }

    @PostMapping("/product/regist")
    @ResponseBody
    public ResponseEntity<String> regist(ProductDTO productDTO) {
        log.debug("productDTO = "+productDTO);

        Product product = new Product();
        Store store = new Store();
        product.setStore(store);//Store를 Product에 주입
        store.setStoreId(productDTO.getStore().getStoreId());
        product.setPrice(productDTO.getPrice());
        product.setBrand(productDTO.getBrand());

        productService.save(product);

        return ResponseEntity.status(HttpStatus.CREATED).body("등록성공");
    }

    @GetMapping("/product/list")
    public String getList(Model model){
        List productList=productService.getList();
        model.addAttribute("productList", productList);

        return "store/product/list";
    }

    @GetMapping("/product/listbystore")
    public String getListByStore(Model model,  @RequestParam(name="storeId", required = false, defaultValue = "0") int storeId, HttpSession session){
        log.debug("storeId="+storeId);

        if(storeId==0){
            Store store=(Store)session.getAttribute("store");
            storeId=store.getStoreId();
        }

        List productList = productService.getListByStoreId(storeId);
        model.addAttribute("productList", productList);
        return "store/product/list";
    }

    @GetMapping("/product/detail")
    public String getDetail(Model model, @RequestParam(name="product_id", required = false, defaultValue = "0") int productId){

        Product product=productService.getDetail(productId);
        model.addAttribute("product", product);

        return "store/product/detail";
    }


}
