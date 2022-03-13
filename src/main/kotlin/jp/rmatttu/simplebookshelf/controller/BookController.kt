package jp.rmatttu.simplebookshelf.controller

import jp.rmatttu.simplebookshelf.entity.Book
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

    @GetMapping("book/{id}/edit")
    fun edit(@PathVariable id: Int, model: Model): String {
        val bookRecord = bookRepository.findById(id)
        if (bookRecord.isEmpty) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Not found id: $id")
        }
        val book = bookRecord.get()

        model["id"] = id
        model["message"] = ""
        model["book"] = book
        return "book/edit"
    }

    @PostMapping("book/{id}/edit")
    fun editPost(@PathVariable id: Int, @RequestParam title: String, model: Model): String {
        val bookRecord = bookRepository.findById(id)
        if (bookRecord.isEmpty) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Not found id: $id")
        }
        val book = bookRecord.get()

        // TODO titleのバリデーションを追加、現状、前回同様のタイトル、空文字などが登録できる
        // TODO ログに変更を記録

        val editedBook = Book(book.id, title, book.bookAuthors)
        bookRepository.save(editedBook)

        model["id"] = id
        model["message"] = "「${editedBook.title}」へ変更しました"
        model["book"] = editedBook
        return "book/edit"
    }

}
