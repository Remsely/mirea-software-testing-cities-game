package ru.remsely.mirea.testing.cities_game.controller

import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.ExceptionHandler
import ru.remsely.mirea.testing.cities_game.service.Game
import java.util.concurrent.TimeoutException

@RestController
@RequestMapping("/api/game")
class GameController {
    private val game = Game()

    @PostMapping("/start")
    fun startGame(@RequestParam city: String): String {
        return try {
            game.start(city)
            "Game started: $city."
        } catch (e: IllegalArgumentException) {
            throw RuntimeException(e.message)
        }
    }

    @PostMapping("/process")
    fun processCity(@RequestParam city: String): String {
        return "City added: ${game.processCity(city)}."
    }

    @PostMapping("/reset")
    fun resetGame(): String {
        game.dropToFirst()
        return "Game was reset to first city."
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(e: IllegalArgumentException): String {
        return "${e.message}"
    }

    @ExceptionHandler(TimeoutException::class)
    fun handleTimeoutException(e: TimeoutException): String {
        return "${e.message}"
    }
}
