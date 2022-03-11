package jp.rmatttu.simplebookshelf

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SimpleBookshelfApplication

fun main(args: Array<String>) {
    runApplication<SimpleBookshelfApplication>(*args)
}
