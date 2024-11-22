package ru.remsely.mirea.testing.cities_game.service

import ru.remsely.mirea.testing.cities_game.api.GeoDbApi

object CityValidator {
    private val deoDbApi = GeoDbApi()

    fun isCityExists(cityName: String): Boolean =
        deoDbApi.getCitiesByName(cityName)
            .let { response ->
                response.data.any {
                    it.name.equals(cityName, ignoreCase = true)
                }
            }

    fun isCityAllowedNext(previousCity: String, newCity: String): Boolean =
        previousCity.last().lowercase() == newCity.first().lowercase()

    fun isCityNew(cities: LinkedHashSet<String>, newCity: String): Boolean =
        !cities.contains(newCity.lowercase())
}