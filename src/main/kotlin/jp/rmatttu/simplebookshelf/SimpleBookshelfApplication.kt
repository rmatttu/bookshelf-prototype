package jp.rmatttu.simplebookshelf

import jp.rmatttu.simplebookshelf.repository.Seeder
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SimpleBookshelfApplication

fun main(args: Array<String>) {
    runApplication<SimpleBookshelfApplication>(*args)
}
