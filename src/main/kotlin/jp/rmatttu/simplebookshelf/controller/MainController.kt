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
    fun showUsers(model: Model): String {
        val book = Book("this is test")
        bookRepository.save(book)
        val author = Author("tom")
        authorRepository.save(author)
        val bookAuthor = BookAuthor(book, author)
        bookAuthorRepository.save(bookAuthor)

        val books = bookRepository.findAll()
        model.addAttribute("books", books)

        val authors = authorRepository.findAll()
        val bookAuthors = bookAuthorRepository.findAll()
        return "index"
    }

    @GetMapping("/book/new")
    fun showAddPage(): String {
        return "book/new"
    }

    @PostMapping("/book/new")
    fun addNewUser(@RequestParam title: String): String {
        bookRepository.save(Book(title))
        return "redirect:/"
    }
}
