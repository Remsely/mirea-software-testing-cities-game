package ru.remsely.mirea.testing.cities_game.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class City(val name: String)

@JsonIgnoreProperties(ignoreUnknown = true)
data class CityResponse(val data: List<City>)