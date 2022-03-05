package jp.rmatttu.simplebookshelf.entity

import javax.persistence.*

@Entity
class Author (){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0

    @Column
    var name: String = ""

    @OneToMany(mappedBy = "author")
    var bookAuthors: List<BookAuthor> = ArrayList<BookAuthor>()

    constructor(id: Int, name: String):this() {
        this.id = id
        this.name = name
    }

    override fun toString(): String {
        return "Author(id=$id, name='$name', bookAuthors=$bookAuthors)"
    }

}
