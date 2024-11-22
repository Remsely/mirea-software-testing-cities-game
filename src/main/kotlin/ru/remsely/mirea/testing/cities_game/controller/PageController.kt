package ru.remsely.mirea.testing.cities_game.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/")
class PageController {
    @GetMapping
    fun index() = "redirect:/index.html"
}