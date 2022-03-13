package jp.rmatttu.simplebookshelf.controller

import jp.rmatttu.simplebookshelf.entity.Author
import jp.rmatttu.simplebookshelf.entity.BookAuthor
import jp.rmatttu.simplebookshelf.repository.AuthorRepository
import jp.rmatttu.simplebookshelf.repository.BookAuthorRepository
import jp.rmatttu.simplebookshelf.repository.BookRepository
import jp.rmatttu.simplebookshelf.view.Pager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
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

    @Autowired
    lateinit var authorRepository: AuthorRepository

    @Autowired
    lateinit var bookAuthorRepository: BookAuthorRepository

    @GetMapping("/book/{id}/editauthor")
    fun editAuthor(
        @PathVariable id: Int,
        @PageableDefault pageable: Pageable,
        @RequestParam(defaultValue = "") searchAuthor: String,
        model: Model
    ): String {
        val book = bookRepository.findById(id)
        if (book.isEmpty) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Not found id: $id")
        }

        val totalDataCount = authorRepository.countByNameContaining(searchAuthor)
        val findAuthors = authorRepository.findByNameContaining(searchAuthor)
        val pager = Pager(pageable.pageSize, totalDataCount)
        val pagerInfo = pager.generatePagerInfo(pageable.pageNumber)

        model["message"] = "searchAuthor: $searchAuthor"
        model["findAuthors"] = findAuthors
        model["searchAuthor"] = searchAuthor
        model["book"] = book.get()
        model["authors"] = book.get().bookAuthors.map { it.author }
        return "book/editauthor"
    }

    @PostMapping("/book/{id}/editauthor", params = ["addAuthorId"])
    fun editAuthorAdd(
        @PathVariable id: Int,
        @RequestParam addAuthorId: Int,
        @RequestParam(defaultValue = "") searchAuthor: String,
        model: Model
    ): String {
        val bookRecord = bookRepository.findById(id)
        if (bookRecord.isEmpty) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Not found book id: $id")
        }
        val authorRecord = authorRepository.findById(addAuthorId)
        if (authorRecord.isEmpty) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Not found addAuthorId id: $addAuthorId")
        }

        val book = bookRecord.get()
        val author = authorRecord.get()
        val newBookAuthor = BookAuthor(book, author)
        bookAuthorRepository.save(newBookAuthor)

        model["book"] = book
        model["authors"] = book.bookAuthors.map { it.author }
        model["addAuthorId"] = addAuthorId
        model["searchAuthor"] = ""
        model["findAuthors"] = ArrayList<Author>()
        return "book/editauthor"
    }

    @PostMapping("/book/{id}/editauthor", params = ["removeAuthorId"])
    fun editAuthorRemove(
        @PathVariable id: Int,
        @RequestParam removeAuthorId: Int,
        @RequestParam(defaultValue = "") searchAuthor: String,
        model: Model
    ): String {
        val bookRecord = bookRepository.findById(id)
        if (bookRecord.isEmpty) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Not found book id: $id")
        }
        val authorRecord = authorRepository.findById(removeAuthorId)
        if (authorRecord.isEmpty) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Not found removeAuthorId id: $removeAuthorId")
        }

        val book = bookRecord.get()
        val author = authorRecord.get()
        val removeTargetRecords = bookAuthorRepository.findByBookIdAndAuthorId(book.id, author.id)
        if (removeTargetRecords.isEmpty()) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Not found bookId($book.id) and authorId($author.id)")
        }
        val removeTarget = removeTargetRecords.get(0)
        bookAuthorRepository.delete(removeTarget)
        // TODO 削除したRecordをログに出力

        // TODO 1件も紐づく書籍がなくなっていた場合は、authorも削除

        model["book"] = book
        model["authors"] = book.bookAuthors.map { it.author }
        model["RemoveBookAuthorId"] = removeTarget.id
        model["RemoveAuthorId"] = author.id
        model["searchAuthor"] = ""
        model["findAuthors"] = ArrayList<Author>()
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
        model["findAuthors"] = ArrayList<Author>()
        model["searchAuthor"] = searchAuthor
        model["book"] = book.get()
        model["authors"] = book.get().bookAuthors.map { it.author }
        return "book/editauthor"
    }

}
