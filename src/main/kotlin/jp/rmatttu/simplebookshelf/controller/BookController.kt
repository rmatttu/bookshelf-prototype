package jp.rmatttu.simplebookshelf.controller

import jp.rmatttu.simplebookshelf.entity.Book
import jp.rmatttu.simplebookshelf.repository.BookRepository
import jp.rmatttu.simplebookshelf.view.Pager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
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

    private fun getBook(id: Int): Book {
        val bookRecord = bookRepository.findById(id)
        if (bookRecord.isEmpty) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Not found id: $id")
        }
        return bookRecord.get()
    }

    @GetMapping("/books/new")
    fun getNew(model: Model): String {
        return "books/new"
    }

    @PostMapping("/books/new")
    fun postNew(@RequestParam title: String, model: Model): String {
        // TODO titleのバリデーションを追加、空文字などが登録できてしまう

        val book = Book(title)
        bookRepository.save(book)
        // newBook.id など更新されている

        model["commitedBook"] = book
        return "books/new"
    }

    @GetMapping("/books/search")
    fun getSearch(
        @RequestParam(defaultValue = "") searchTitle: String,
        pageable: Pageable,
        model: Model
    ): String {
        val findBooks = bookRepository.findByTitleContaining(searchTitle, pageable)
        val totalCount = bookRepository.countByTitleContaining(searchTitle)
        val pager = Pager(pageable.pageSize, totalCount)
        val pagerInfo = pager.generatePagerInfo(pageable.pageNumber)

        model["searchTitle"] = searchTitle
        model["findBooks"] = findBooks
        model["pagerInfo"] = pagerInfo
        return "books/search"
    }

    @GetMapping("/books/{id}")
    fun getBooks(@PathVariable id: Int, model: Model): String {
        val book = getBook(id)
        model["message"] = book.title
        model["book"] = book
        model["authors"] = book.bookAuthors.map { it.author }
        return "books/book"
    }

    @GetMapping("books/{id}/edit")
    fun getEdit(@PathVariable id: Int, model: Model): String {
        val book = getBook(id)
        model["edited"] = false
        model["book"] = book
        return "books/edit"
    }

    @PostMapping("books/{id}/edit")
    fun postEdit(@PathVariable id: Int, @RequestParam title: String, model: Model): String {
        val book = getBook(id)

        // TODO titleのバリデーションを追加、現状、前回同様のタイトル、空文字などが登録できる
        // TODO ログに変更を記録

        val editedBook = Book(book.id, title, book.bookAuthors)
        bookRepository.save(editedBook)
        model["edited"] = true
        model["book"] = editedBook
        return "books/edit"
    }

}
