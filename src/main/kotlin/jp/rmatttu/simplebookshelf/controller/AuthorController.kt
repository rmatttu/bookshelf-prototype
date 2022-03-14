package jp.rmatttu.simplebookshelf.controller

import jp.rmatttu.simplebookshelf.entity.Author
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
class AuthorController {
    @Autowired
    lateinit var authorRepository: AuthorRepository

    @Autowired
    lateinit var bookRepository: BookRepository

    @Autowired
    lateinit var bookAuthorRepository: BookAuthorRepository

    private fun getAuthor(id: Int): Author {
        val authorRecord = authorRepository.findById(id)
        if (authorRecord.isEmpty()) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Not found id: $id")
        }
        return authorRecord.get()
    }

    @GetMapping("/authors/search")
    fun getSearch(
        @RequestParam(defaultValue = "") searchName: String,
        @PageableDefault pageable: Pageable,
        model: Model
    ): String {
        val findAuthors = authorRepository.findByNameContaining(searchName, pageable)
        val totalCount = authorRepository.countByNameContaining(searchName)
        val pager = Pager(pageable.pageSize, totalCount)
        val pagerInfo = pager.generatePagerInfo(pageable.pageNumber)

        model["searchName"] = searchName
        model["findAuthors"] = findAuthors
        model["pagerInfo"] = pagerInfo
        return "authors/search"
    }


    @GetMapping("/authors/{id}")
    fun getAuthors(@PathVariable id: Int, @PageableDefault pageable: Pageable, model: Model): String {
        val author = getAuthor(id)
        val bookRecords = bookAuthorRepository.findByAuthorId(author.id, pageable)
        val books = bookRecords.map { it.book }

        val totalCount = bookAuthorRepository.countByAuthorId(author.id)
        val pager = Pager(pageable.pageSize, totalCount)
        val pagerInfo = pager.generatePagerInfo(pageable.pageNumber)

        model["author"] = author
        model["books"] = books
        model["pagerInfo"] = pagerInfo
        return "authors/author"
    }

    @GetMapping("/authors/{id}/edit")
    fun getEdit(@PathVariable id: Int, model: Model): String {
        val author = getAuthor(id)
        model["edited"] = false
        model["author"] = author
        return "authors/edit"
    }

    @PostMapping("authors/{id}/edit")
    fun postEdit(@PathVariable id: Int, @RequestParam name: String, model: Model): String {
        val author = getAuthor(id)

        // TODO nameのバリデーションを追加、現状、前回同様のタイトル、空文字などが登録できる
        // TODO ログに変更を記録

        val editedAuthor = Author(author.id, name, author.bookAuthors)
        authorRepository.save(editedAuthor)
        model["edited"] = true
        model["author"] = editedAuthor
        return "authors/edit"
    }

}
