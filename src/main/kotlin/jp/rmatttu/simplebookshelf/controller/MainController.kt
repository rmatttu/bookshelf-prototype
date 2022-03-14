package jp.rmatttu.simplebookshelf.controller

import jp.rmatttu.simplebookshelf.repository.AuthorRepository
import jp.rmatttu.simplebookshelf.repository.BookAuthorRepository
import jp.rmatttu.simplebookshelf.repository.BookRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
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
        model["author"] = author

        if (author.isEmpty()) {
            // TODO top10件ぐらいを返すよう修正
            model["authors"] = authorRepository.findAllByOrderByIdDesc()
            return "index"
        }

        model["authors"] = authorRepository.findByNameContaining(author, PageRequest.of(0, 5))
        return "index"
    }

}
