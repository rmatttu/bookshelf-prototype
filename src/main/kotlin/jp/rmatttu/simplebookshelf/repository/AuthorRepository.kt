package jp.rmatttu.simplebookshelf.repository

import jp.rmatttu.simplebookshelf.entity.Author
import org.springframework.data.repository.CrudRepository

interface AuthorRepository : CrudRepository<Author, Int> {
    fun findByNameContaining(searchWord: String): List<Author>
}
