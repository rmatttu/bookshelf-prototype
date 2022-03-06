package jp.rmatttu.simplebookshelf.entity

import javax.persistence.*

@Entity
class BookAuthor(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,

    /**
     * Book
     *
     * `book_id`カラムのidから自動的にマッピングされます
     */
    @ManyToOne(cascade = arrayOf(CascadeType.ALL))
    val book: Book,

    /**
     * Author
     *
     * `author_id`カラムから自動的にマッピングされます
     */
    @ManyToOne(cascade = arrayOf(CascadeType.ALL))
    val author: Author
) {

    constructor(book: Book, author: Author): this(0, book, author){
    }

    override fun toString(): String {
        return "BookAuthor(id=$id, book.id=${book.id}, author.id=${author.id})"
    }

}
