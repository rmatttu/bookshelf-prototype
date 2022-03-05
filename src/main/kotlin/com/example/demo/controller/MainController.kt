package com.example.demo.com.example.demo.controller

import com.example.demo.com.example.demo.controller.com.example.demo.entity.Book
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping


@Controller
class MainController {
    @GetMapping("/")
    fun showUsers(): String{
        val book = Book()
        book.title="this is test"
        return "index"
    }
}
