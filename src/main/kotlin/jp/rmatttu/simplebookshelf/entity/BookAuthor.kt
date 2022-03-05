package jp.rmatttu.simplebookshelf.entity

import javax.persistence.*

@Entity
class BookAuthor() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0

    @ManyToOne(cascade = arrayOf(CascadeType.ALL))
    @JoinColumn(name = "book_id")
    var book: Book = Book()

    @ManyToOne(cascade = arrayOf(CascadeType.ALL))
    @JoinColumn(name = "author_id")
    var author: Author = Author()

    constructor(id: Int, book: Book, author: Author):this() {
        this.id = id
        this.book = book
        this.author = author
    }
}
