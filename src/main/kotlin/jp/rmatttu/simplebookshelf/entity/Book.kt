package jp.rmatttu.simplebookshelf.entity

import javax.persistence.*

@Entity
class Book() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0

    @Column
    var title: String = ""

    @OneToMany(mappedBy = "book")
    var bookAuthors: List<BookAuthor> = ArrayList<BookAuthor>()

    constructor(id: Int, title: String) : this() {
        this.id = id
        this.title = title
    }

    override fun toString(): String {
        return "Book(id=$id, title='$title', bookAuthors=$bookAuthors)"
    }

}
