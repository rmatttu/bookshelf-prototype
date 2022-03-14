package jp.rmatttu.simplebookshelf.repository

import jp.rmatttu.simplebookshelf.entity.Author
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.CrudRepository

interface AuthorRepository : CrudRepository<Author, Int> {
    fun countByNameContainingAndIdNotIn(searchWord: String, ignoreIdList: List<Int>): Int
    fun countByNameContaining(searchWord: String): Int
    fun findByNameContainingAndIdNotIn(searchWord: String, ignoreIdList: List<Int>, pageable: Pageable): List<Author>
    fun findByNameContaining(searchWord: String, pageable: Pageable): List<Author>
    fun findAllByOrderByIdDesc(): List<Author>
}
