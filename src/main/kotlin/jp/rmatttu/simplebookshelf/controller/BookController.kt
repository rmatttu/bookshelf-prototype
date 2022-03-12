package jp.rmatttu.simplebookshelf.controller

import jp.rmatttu.simplebookshelf.repository.BookRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.server.ResponseStatusException

@Controller
class BookController {
    @Autowired
    lateinit var bookRepository: BookRepository

    @GetMapping("/book/{id}")
    fun index(@PathVariable id: Int, model: Model): String {
        val book = bookRepository.findById(id)
        if (book.isEmpty) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Not found id: $id")
        }
        model["message"] = book.get().title
        model["book"] = book.get()
        model["authors"] = book.get().bookAuthors.map { it.author }
        return "book/book"
    }

}
