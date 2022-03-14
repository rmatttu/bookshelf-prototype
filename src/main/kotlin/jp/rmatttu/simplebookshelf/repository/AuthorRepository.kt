package jp.rmatttu.simplebookshelf.repository

import jp.rmatttu.simplebookshelf.entity.Author
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.CrudRepository

interface AuthorRepository : CrudRepository<Author, Int> {
    fun countByNameContaining(searchWord: String): Int
    fun findByNameContaining(searchWord: String, pageable: Pageable): List<Author>
    fun findAllByOrderByIdDesc(): List<Author>
}
