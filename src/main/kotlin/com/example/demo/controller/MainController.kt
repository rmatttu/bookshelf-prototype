package com.example.demo.controller

import com.example.demo.entity.Book
import com.example.demo.repository.BookRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping


@Controller
class MainController {
    @Autowired
    lateinit var bookRepository: BookRepository

    @GetMapping("/")
    fun showUsers(model: Model): String{
        val book = Book()
        book.title="this is test"

        val books = bookRepository.findAll()
        model.addAttribute("books", books)
        return "index"
    }
}
