package jp.rmatttu.simplebookshelf.entity

import javax.persistence.*

@Entity
class Book() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0

    @Column
    var title: String = ""

    @OneToMany(mappedBy = "author")
    var authors: List<Author> = ArrayList<Author>()

    constructor(id: Int, title: String, ):this() {
        this.id = id
        this.title = title
    }
}
