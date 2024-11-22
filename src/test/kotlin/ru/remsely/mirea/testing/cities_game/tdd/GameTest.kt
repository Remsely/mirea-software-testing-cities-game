package ru.remsely.mirea.testing.cities_game.tdd

import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import ru.remsely.mirea.testing.cities_game.service.Game
import java.lang.Thread.sleep
import java.util.concurrent.TimeoutException

class GameTest {
    companion object {
        val game = Game()

        @BeforeAll
        @JvmStatic
        fun init() {
            sleep(1500)
            game.start("Moscow")
            sleep(1500)
            game.processCity("Watford")
        }
    }

    @Test
    fun `test city processing`() {
        sleep(1500)
        assertThrows<IllegalArgumentException> { game.processCity("adsfadf") }
        sleep(1500)
        assertThrows<IllegalArgumentException> { game.processCity("Mexico") }
        sleep(1500)
        assertThrows<IllegalArgumentException> { game.processCity("Moscow") }
        sleep(1500)
        assertDoesNotThrow { game.processCity("Dallas") }
        sleep(35000)
        assertThrows<TimeoutException> { game.processCity("Shanghai") }
    }
}
