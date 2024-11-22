package ru.remsely.mirea.testing.cities_game.service

import java.time.Duration

object TimeLimiter {
    fun isTimeLimitExceeded(responseTime: Duration): Boolean =
        responseTime > Duration.ofSeconds(30)
}