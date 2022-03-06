package jp.rmatttu.simplebookshelf.entity

import javax.persistence.*

@Entity
class Book(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,

    @Column
    val title: String,

    @OneToMany(mappedBy = "book")
    val bookAuthors: List<BookAuthor>
) {

    constructor(title: String) : this(0, title, ArrayList<BookAuthor>()) {
    }

    override fun toString(): String {
        return "Book(id=$id, title='$title', bookAuthors=$bookAuthors)"
    }

}
