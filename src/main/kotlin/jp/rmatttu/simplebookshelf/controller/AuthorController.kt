package jp.rmatttu.simplebookshelf.controller

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
import org.springframework.web.server.ResponseStatusException

@Controller
class AuthorController {
    @Autowired
    lateinit var authorRepository: AuthorRepository

    @Autowired
    lateinit var bookRepository: BookRepository

    @Autowired
    lateinit var bookAuthorRepository: BookAuthorRepository

    @GetMapping("/author/{id}")
    fun index(@PathVariable id: Int, @PageableDefault pageable: Pageable, model: Model): String {
        val authorRecord = authorRepository.findById(id)
        if (authorRecord.isEmpty()) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Not found id: $id")
        }

        val author = authorRecord.get()
        val bookRecords = bookAuthorRepository.findByAuthorId(author.id, pageable)
        val books = bookRecords.map { it.book }

        val totalCount = bookAuthorRepository.countByAuthorId(author.id)
        val pager = Pager(pageable.pageSize, totalCount)
        val pagingInfo = pager.generatePagingInfo(pageable.pageNumber)

        model["author"] = author
        model["books"] = books
        model["pagingInfo"] = pagingInfo
        return "author/author"
    }

}
