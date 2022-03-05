package jp.rmatttu.simplebookshelf.entity

import javax.persistence.*

@Entity
class BookAuthor() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0

    /**
     * Book
     *
     * `book_id`カラムのidから自動的にマッピングされます
     */
    @ManyToOne(cascade = arrayOf(CascadeType.ALL))
    var book: Book = Book()

    /**
     * Author
     *
     * `author_id`カラムから自動的にマッピングされます
     */
    @ManyToOne(cascade = arrayOf(CascadeType.ALL))
    var author: Author = Author()

    constructor(id: Int, book: Book, author: Author):this() {
        this.id = id
        this.book = book
        this.author = author
    }

    override fun toString(): String {
        return "BookAuthor(id=$id, book.id=${book.id}, author.id=${author.id})"
    }

}
