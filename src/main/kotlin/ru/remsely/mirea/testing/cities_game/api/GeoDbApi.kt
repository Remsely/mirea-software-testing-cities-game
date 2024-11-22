package ru.remsely.mirea.testing.cities_game.api

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import okhttp3.OkHttpClient
import okhttp3.Request
import ru.remsely.mirea.testing.cities_game.dto.CityResponse

const val URL = "https://wft-geo-db.p.rapidapi.com/v1/geo/cities?namePrefix="
const val API_KEY = "2cb838d163msh3330dcfcdf68d05p1c4037jsnae3673cbd2d1"

class GeoDbApi {
    private val objectMapper = jacksonObjectMapper()
    private val httpClient = OkHttpClient()

    fun getCitiesByName(name: String): CityResponse =
        Request.Builder()
            .url("$URL$name")
            .get()
            .addHeader("X-RapidAPI-Key", API_KEY)
            .build()
            .let { request ->
                httpClient.newCall(request).execute()
                    .use { response ->
                        objectMapper.readValue(
                            response.body?.string() ?: throw RuntimeException("Empty response from GeoDB API.")
                        )
                    }
            }
}