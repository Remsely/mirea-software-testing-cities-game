package ru.remsely.mirea.testing.cities_game.tdd

import org.junit.jupiter.api.Test
import ru.remsely.mirea.testing.cities_game.service.TimeLimiter
import java.time.Duration
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class TimeLimiterTest {
    private val timeLimiter = TimeLimiter

    @Test
    fun `test time limit exception`() {
        assertFalse(
            timeLimiter.isTimeLimitExceeded(
                Duration.ofSeconds(30)
            )
        )
        assertTrue(
            timeLimiter.isTimeLimitExceeded(
                Duration.ofSeconds(31)
            )
        )
    }
}
