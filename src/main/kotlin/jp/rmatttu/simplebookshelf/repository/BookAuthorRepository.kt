package jp.rmatttu.simplebookshelf.repository

import jp.rmatttu.simplebookshelf.entity.BookAuthor
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.CrudRepository

interface BookAuthorRepository : CrudRepository<BookAuthor, Int> {
    fun findByAuthorId(authorId: Int, pageable: Pageable): List<BookAuthor>
    fun countByAuthorId(authorId: Int): Int
    fun findByBookIdAndAuthorId(bookId: Int, authorId: Int): List<BookAuthor>
}
