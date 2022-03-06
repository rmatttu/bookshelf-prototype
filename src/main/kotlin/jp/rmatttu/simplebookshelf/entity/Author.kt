package jp.rmatttu.simplebookshelf.entity

import javax.persistence.*

@Entity
class Author(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,

    @Column
    val name: String,

    @OneToMany(mappedBy = "author")
    val bookAuthors: List<BookAuthor>
) {

    constructor(name: String) : this(0, name, ArrayList<BookAuthor>()) {
    }

    override fun toString(): String {
        return "Author(id=$id, name='$name', bookAuthors=$bookAuthors)"
    }

}
