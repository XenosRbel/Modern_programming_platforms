package com.mpp.laba_1.product;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProductController {
    @GetMapping("")
    public String list(){
        return "products";
    }
}
