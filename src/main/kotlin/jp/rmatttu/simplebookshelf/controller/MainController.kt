package jp.rmatttu.simplebookshelf.controller

import jp.rmatttu.simplebookshelf.entity.Author
import jp.rmatttu.simplebookshelf.entity.Book
import jp.rmatttu.simplebookshelf.entity.BookAuthor
import jp.rmatttu.simplebookshelf.repository.AuthorRepository
import jp.rmatttu.simplebookshelf.repository.BookAuthorRepository
import jp.rmatttu.simplebookshelf.repository.BookRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam


@Controller
class MainController {
    @Autowired
    lateinit var bookRepository: BookRepository

    @Autowired
    lateinit var authorRepository: AuthorRepository

    @Autowired
    lateinit var bookAuthorRepository: BookAuthorRepository

    @GetMapping("/")
    fun showUsers(@RequestParam(defaultValue = "") author: String, model: Model): String {
        if (author.isEmpty()){
            // TODO top10件ぐらいを返すよう修正
            model["books"] = bookRepository.findAllByOrderByIdDesc()
            return "index"
        }


        val authors = authorRepository.findByNameContaining(author)
        model["books"] = bookRepository.findAllByOrderByIdDesc()
        return "index"
    }

    fun getTopBooks(limit: Int): MutableIterable<Book> {
        // TODO 時間があれば実装
        return bookRepository.findAll()
    }

}
