package jp.rmatttu.simplebookshelf.entity

import javax.persistence.*

@Entity
class Author (){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0

    @Column
    var name: String = ""

    @OneToMany(mappedBy = "book")
    var bookAuthors: Set<BookAuthor>? = null

    constructor(id: Int, name: String):this() {
        this.id = id
        this.name = name
    }
}
