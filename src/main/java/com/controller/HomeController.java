package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping(value = "order")
    public String showOrder(){
        return "order-table";
    }

    @GetMapping(value = "404")
    public String show404(){
        return "error";
    }

    @GetMapping(value = "403")
    public String show403(){
        return "403";
    }
}
