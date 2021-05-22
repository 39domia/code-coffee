package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "products")
public class ProductController {

    @GetMapping
    public String showProductList(){
        return "product";
    }

}
