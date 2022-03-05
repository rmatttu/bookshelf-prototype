package jp.rmatttu.simplebookshelf.repository

import jp.rmatttu.simplebookshelf.entity.BookAuthor
import org.springframework.data.repository.CrudRepository

interface BookAuthorRepository : CrudRepository<BookAuthor, Int> {
}
