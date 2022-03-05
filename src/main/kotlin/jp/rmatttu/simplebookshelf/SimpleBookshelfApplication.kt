package jp.rmatttu.simplebookshelf

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SimpleBookshelfApplication

fun main(args: Array<String>) {
	runApplication<SimpleBookshelfApplication>(*args)
	println("hello")
	Thread.sleep(10000)
	println("hello")
}
