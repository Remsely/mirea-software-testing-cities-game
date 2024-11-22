package ru.remsely.mirea.testing.cities_game.service

import java.time.Duration
import java.time.Instant
import java.util.concurrent.TimeoutException

class Game {
    private val cityValidator = CityValidator
    private val timeLimiter = TimeLimiter

    private val citiesSet = LinkedHashSet<String>()

    private lateinit var lastAddition: Instant

    fun processCity(newCity: String) = when {
        !cityValidator.isCityNew(citiesSet, newCity) ->
            throw IllegalArgumentException("This city is already in list!")

        !cityValidator.isCityAllowedNext(citiesSet.last(), newCity) ->
            throw IllegalArgumentException("City must starts with the last character of previous city!")

        !cityValidator.isCityExists(newCity) ->
            throw IllegalArgumentException("City does not exists!")

        timeLimiter.isTimeLimitExceeded(Duration.between(lastAddition, Instant.now())) ->
            dropToFirst().also {
                throw TimeoutException("Time limit exceeded! You lost. Game has been reset to first city.")
            }

        else -> addCity(newCity)
    }

    fun start(initCity: String) = when {
        !cityValidator.isCityExists(initCity) ->
            throw IllegalArgumentException("City does not exists!")

        else -> addCity(initCity)
    }

    fun dropToFirst() = citiesSet.removeAll { it != citiesSet.first() }

    private fun addCity(city: String) =
        city.lowercase().let {
            citiesSet.add(it)
        }.also {
            lastAddition = Instant.now()
        }
}