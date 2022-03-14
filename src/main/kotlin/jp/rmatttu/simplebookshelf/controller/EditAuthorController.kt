package jp.rmatttu.simplebookshelf.controller

import jp.rmatttu.simplebookshelf.entity.Author
import jp.rmatttu.simplebookshelf.entity.Book
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

    private fun getBook(id: Int): Book {
        val bookRecord = bookRepository.findById(id)
        if (bookRecord.isEmpty) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Not found id: $id")
        }
        return bookRecord.get()
    }

    private fun getAuthor(addAuthorId: Int): Author {
        val authorRecord = authorRepository.findById(addAuthorId)
        if (authorRecord.isEmpty) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Not found addAuthorId id: $addAuthorId")
        }
        return authorRecord.get()
    }

    private fun getFindAuthors(
        book: Book,
        searchAuthor: String,
        pageable: Pageable
    ): Pair<Int, List<Author>> {
        val ignoreAuthorIdList = book.bookAuthors.map { it.author.id }
        if (ignoreAuthorIdList.isEmpty()) {
            // 初回登録等で、無視するべき著者が居ない場合
            val totalDataCount = authorRepository.countByNameContaining(searchAuthor)
            val findAuthors = authorRepository.findByNameContaining(searchAuthor, pageable)
            return Pair(totalDataCount, findAuthors)
        } else {
            // すでにこの書籍に登録してある著者を無視する
            val totalDataCount = authorRepository.countByNameContainingAndIdNotIn(searchAuthor, ignoreAuthorIdList)
            val findAuthors =
                authorRepository.findByNameContainingAndIdNotIn(searchAuthor, ignoreAuthorIdList, pageable)
            return Pair(totalDataCount, findAuthors)
        }
    }


    @GetMapping("/books/{id}/editauthor")
    fun getEditAuthors(
        @PathVariable id: Int,
        @PageableDefault pageable: Pageable,
        @RequestParam(defaultValue = "") searchAuthor: String,
        model: Model
    ): String {
        val book = getBook(id)
        val (totalDataCount, findAuthors) = getFindAuthors(book, searchAuthor, pageable)
        val pager = Pager(pageable.pageSize, totalDataCount)
        val pagerInfo = pager.generatePagerInfo(pageable.pageNumber)

        model["findAuthors"] = findAuthors
        model["searchAuthor"] = searchAuthor
        model["book"] = book
        model["pagerInfo"] = pagerInfo
        model["authors"] = book.bookAuthors.map { it.author }
        return "books/editauthor"
    }

    @PostMapping("/books/{id}/editauthor", params = ["addAuthorId"])
    fun postEditAuthorAddAuthorId(
        @PathVariable id: Int,
        @RequestParam addAuthorId: Int,
        @RequestParam(defaultValue = "") searchAuthor: String,
        model: Model
    ): String {
        val book = getBook(id)
        val author = getAuthor(addAuthorId)
        val newBookAuthor = BookAuthor(book, author)
        // TODO すでにbookに紐付いているauthor.idを受け取った場合の処理を実装
        bookAuthorRepository.save(newBookAuthor)

        model["book"] = book
        model["authors"] = book.bookAuthors.map { it.author }
        model["addAuthorId"] = addAuthorId
        model["searchAuthor"] = ""
        model["findAuthors"] = ArrayList<Author>()
        return "books/editauthor"
    }

    @PostMapping("/books/{id}/editauthor", params = ["removeAuthorId"])
    fun postEditAuthorRemoveAuthorId(
        @PathVariable id: Int,
        @RequestParam removeAuthorId: Int,
        @RequestParam(defaultValue = "") searchAuthor: String,
        model: Model
    ): String {
        val book = getBook(id)
        val author = getAuthor(removeAuthorId)
        val removeTargetRecords = bookAuthorRepository.findByBookIdAndAuthorId(book.id, author.id)
        if (removeTargetRecords.isEmpty()) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Not found bookId($book.id) and authorId($author.id)")
        }
        val removeTarget = removeTargetRecords.get(0)
        bookAuthorRepository.delete(removeTarget)
        // TODO 削除したRecordをログに出力
        // TODO 1件も紐づく書籍がなくなっていた場合は、authorも自動削除しメッセージを表示

        model["book"] = book
        model["authors"] = book.bookAuthors.map { it.author }
        model["RemoveBookAuthorId"] = removeTarget.id
        model["RemoveAuthorId"] = author.id
        model["searchAuthor"] = ""
        model["findAuthors"] = ArrayList<Author>()
        return "books/editauthor"
    }

    @PostMapping("/books/{id}/editauthor", params = ["newAuthorName"])
    fun postEditAuthorNewAuthorName(
        @PathVariable id: Int,
        @RequestParam newAuthorName: String,
        @RequestParam(defaultValue = "") searchAuthor: String,
        model: Model
    ): String {
        val book = getBook(id)
        val insertAuthor = Author(newAuthorName)
        authorRepository.save(insertAuthor)
        val newBookAuthor = BookAuthor(book, insertAuthor)
        bookAuthorRepository.save(newBookAuthor)

        // TODO 追加した情報をログに出力

        model["findAuthors"] = ArrayList<Author>()
        model["searchAuthor"] = searchAuthor
        model["book"] = book
        model["authors"] = book.bookAuthors.map { it.author }
        return "books/editauthor"
    }

}
