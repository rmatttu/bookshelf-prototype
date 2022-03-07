package jp.rmatttu.simplebookshelf.entity

import javax.persistence.*

@Entity
//@Table(indexes = @Index(column::= index_column))
@Table(
    indexes = [
        // indexのnameに何も指定しないと適当な名前が設定される e.g. IDXq37qkj7serxg0bh56m450uigs
        Index(name = "idx_book_authors_book_id", columnList = "book_id"),
        Index(name = "idx_book_authors_author_id", columnList = "author_id")
    ]
)
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
