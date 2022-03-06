package jp.rmatttu.simplebookshelf.repository

import jp.rmatttu.simplebookshelf.entity.Author
import jp.rmatttu.simplebookshelf.entity.Book
import jp.rmatttu.simplebookshelf.entity.BookAuthor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
class Seeder {
    @Autowired
    lateinit var bookRepository: BookRepository

    @Autowired
    lateinit var authorRepository: AuthorRepository

    @Autowired
    lateinit var bookAuthorRepository: BookAuthorRepository

    /**
     * Beanコンテキスト生成後に呼び出される
     */
    @PostConstruct
    fun afterStartup() {
        if (needSeeds()) {
            applySeeds()
        }
    }

    private fun needSeeds(): Boolean {
        // 全てのテーブルが空かどうか、もっといい方法がありそう
        return listOf<Int>(
            authorRepository.count().toInt(),
            bookRepository.count().toInt(),
            bookAuthorRepository.count().toInt()
        ).all { it == 0 }
    }

    private fun applySeeds() {
        var authors = ArrayList<Author>()
        authors.add(Author(1, "Jada Dale", ArrayList<BookAuthor>()))
        authors.add(Author(2, "Misha Huff", ArrayList<BookAuthor>()))
        authors.add(Author(3, "Cally Hoffman", ArrayList<BookAuthor>()))
        authors.add(Author(4, "Maiya Ventura", ArrayList<BookAuthor>()))
        authors.add(Author(5, "Zephaniah Ratliff", ArrayList<BookAuthor>()))
        authors.add(Author(6, "Jacque Partridge", ArrayList<BookAuthor>()))
        authors.add(Author(7, "Kenya Murphy", ArrayList<BookAuthor>()))
        authorRepository.saveAll(authors)

        var books = ArrayList<Book>()
        books.add(Book(1, "10 Awesome Ways to Photograph Foxes", ArrayList<BookAuthor>()))
        books.add(Book(2, "7 Pictures of Paul McCartney That We'd Rather Forget", ArrayList<BookAuthor>()))
        books.add(Book(3, "How to Increase Your Income Using Just Your Teeth.", ArrayList<BookAuthor>()))
        books.add(Book(4, "21 Myths About Foxes Debunked", ArrayList<BookAuthor>()))
        books.add(Book(5, "Introducing Anonymous - Who Am I And Why Should You Follow Me", ArrayList<BookAuthor>()))
        bookRepository.saveAll(books)

        var bookAuthors = ArrayList<BookAuthor>()
        bookAuthors.add(BookAuthor(1, books.get(0), authors.get(0)))
        bookAuthors.add(BookAuthor(2, books.get(1), authors.get(1)))
        bookAuthors.add(BookAuthor(3, books.get(1), authors.get(2)))
        bookAuthors.add(BookAuthor(4, books.get(1), authors.get(3)))
        bookAuthors.add(BookAuthor(5, books.get(2), authors.get(4)))
        bookAuthors.add(BookAuthor(6, books.get(3), authors.get(1)))
        bookAuthors.add(BookAuthor(7, books.get(4), authors.get(5)))
        bookAuthors.add(BookAuthor(8, books.get(4), authors.get(6)))
        bookAuthorRepository.saveAll(bookAuthors)
    }
}
