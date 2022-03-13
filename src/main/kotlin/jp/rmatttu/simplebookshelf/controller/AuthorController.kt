package jp.rmatttu.simplebookshelf.controller

import jp.rmatttu.simplebookshelf.repository.AuthorRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Controller
class AuthorController {
    @Autowired
    lateinit var authorRepository: AuthorRepository

    @GetMapping("/author/{id}")
    fun index(@PathVariable id: Int, model: Model): String {
        val author = authorRepository.findById(id)
        model["author"] = author.get()
        return "author/author"
    }

}
