package jp.rmatttu.simplebookshelf.repository

import jp.rmatttu.simplebookshelf.entity.Author
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.CrudRepository

interface AuthorRepository : CrudRepository<Author, Int> {
    fun countByNameContainingIgnoreCaseAndIdNotIn(searchWord: String, ignoreIdList: List<Int>): Int
    fun countByNameContainingIgnoreCase(searchWord: String): Int
    fun findByNameContainingIgnoreCaseAndIdNotIn(
        searchWord: String,
        ignoreIdList: List<Int>,
        pageable: Pageable
    ): List<Author>

    fun findByNameContainingIgnoreCase(searchWord: String, pageable: Pageable): List<Author>
    fun findAllByOrderByIdDesc(): List<Author>
}
