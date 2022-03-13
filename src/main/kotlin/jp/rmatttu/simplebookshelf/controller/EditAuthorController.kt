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
class EditAuthorController {
    @Autowired
    lateinit var bookRepository: BookRepository

    @GetMapping("/book/{id}/editauthor")
    fun editAuthor(
        @PathVariable id: Int,
        @RequestParam(defaultValue = "") searchAuthor: String,
        model: Model
    ): String {
        val book = bookRepository.findById(id)
        if (book.isEmpty) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Not found id: $id")
        }
        model["message"] = "searchAuthor: $searchAuthor"
        model["searchAuthor"] = searchAuthor
        model["book"] = book.get()
        model["authors"] = book.get().bookAuthors.map { it.author }
        return "book/editauthor"
    }

    @PostMapping("/book/{id}/editauthor", params = ["add"])
    fun editAuthorAdd(
        @PathVariable id: Int,
        @RequestParam add: Int,
        @RequestParam(defaultValue = "") searchAuthor: String,
        model: Model
    ): String {
        val book = bookRepository.findById(id)
        if (book.isEmpty) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Not found id: $id")
        }
        model["message"] = "ADD $add"
        model["searchAuthor"] = searchAuthor
        model["book"] = book.get()
        model["authors"] = book.get().bookAuthors.map { it.author }
        return "book/editauthor"
    }

    @PostMapping("/book/{id}/editauthor", params = ["remove"])
    fun editAuthorRemove(
        @PathVariable id: Int,
        @RequestParam remove: String,
        @RequestParam(defaultValue = "") searchAuthor: String,
        model: Model
    ): String {
        val book = bookRepository.findById(id)
        if (book.isEmpty) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Not found id: $id")
        }
        model["message"] = "REMOVE $remove"
        model["searchAuthor"] = searchAuthor
        model["book"] = book.get()
        model["authors"] = book.get().bookAuthors.map { it.author }
        return "book/editauthor"
    }

    @PostMapping("/book/{id}/editauthor", params = ["newAuthor"])
    fun editAuthorNewAuthor(
        @PathVariable id: Int,
        @RequestParam newAuthor: String,
        @RequestParam(defaultValue = "") searchAuthor: String,
        model: Model
    ): String {
        val book = bookRepository.findById(id)
        if (book.isEmpty) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Not found id: $id")
        }
        model["message"] = "newAuthor"
        model["searchAuthor"] = searchAuthor
        model["book"] = book.get()
        model["authors"] = book.get().bookAuthors.map { it.author }
        return "book/editauthor"
    }

}
