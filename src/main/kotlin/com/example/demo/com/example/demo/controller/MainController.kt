package com.example.demo.com.example.demo.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping


@Controller
class MainController {
    @GetMapping("/")
    fun showUsers(): String{
        return "index"
    }
}