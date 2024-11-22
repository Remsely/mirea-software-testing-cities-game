package ru.remsely.mirea.testing.cities_game.tdd

import org.junit.jupiter.api.Assertions.assertFalse
import ru.remsely.mirea.testing.cities_game.service.CityValidator
import java.lang.Thread.sleep
import kotlin.test.Test
import kotlin.test.assertTrue

class CityValidatorTest {
    private val cityValidator = CityValidator

    @Test
    fun `test cities existence`() {
        sleep(1500)
        assertTrue(cityValidator.isCityExists("Moscow"))
        sleep(1500)
        assertTrue(cityValidator.isCityExists("LonDon"))
        sleep(1500)
        assertFalse(cityValidator.isCityExists("alkdhfahsdf"))
        sleep(1500)
        assertFalse(cityValidator.isCityExists("akhdfajd"))
    }

    @Test
    fun `test cities next allowed`() {
        assertTrue(cityValidator.isCityAllowedNext("Moscow", "Watford"))
        assertFalse(cityValidator.isCityAllowedNext("Moscow", "London"))
    }
}
