package jp.rmatttu.simplebookshelf.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam


@Controller
class MainController {
    @GetMapping("/")
    fun getTop(@RequestParam(defaultValue = "") author: String, model: Model): String {
        return "index"
    }

}
