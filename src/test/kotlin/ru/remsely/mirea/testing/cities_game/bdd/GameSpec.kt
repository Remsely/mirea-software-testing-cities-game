package ru.remsely.mirea.testing.cities_game.bdd

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import ru.remsely.mirea.testing.cities_game.service.Game
import java.lang.Thread.sleep
import java.util.concurrent.TimeoutException

class GameSpec : BehaviorSpec ({ //./gradlew test --tests GameSpec
    Given("A game started with Moscow") {
        val game = Game()
        val initCity = "Moscow"
        game.start(initCity)

        When("adding a valid new city") {
            sleep(1500)

            val newCity = "Watford"

            Then("The city should be added to the set") {
                shouldNotThrow<IllegalArgumentException> {
                    game.processCity(newCity)
                }
            }

            game.dropToFirst()
        }

        When("adding an city that already exists in the game") {
            sleep(1500)

            Then("'This city is already in list!' exception should be thrown") {
                shouldThrow<IllegalArgumentException> {
                    game.processCity(initCity)
                }.message shouldBe "This city is already in list!"
            }

            game.dropToFirst()
        }

        When("adding a city that does not start with the last character of previous city") {
            sleep(1500)

            val wrongCity = "Mexico"

            Then("'City must starts with the last character of previous city!' an exception should be thrown") {
                shouldThrow<IllegalArgumentException> {
                    game.processCity(wrongCity)
                }.message shouldBe "City must starts with the last character of previous city!"
            }

            game.dropToFirst()
        }

        When("'City does not exists!' adding a city that does not exist") {
            sleep(1500)

            val fakeCity = "Watforddddddddddddddd"

            Then("an exception should be thrown") {
                shouldThrow<IllegalArgumentException> {
                    game.processCity(fakeCity)
                }.message shouldBe "City does not exists!"
            }

            game.dropToFirst()
        }

        When("time limit is exceeded") {
            sleep(1500)

            val validCity = "Watford"
            sleep(31000)

            Then("an timeout exception should be thrown") {
                shouldThrow<TimeoutException> {
                    game.processCity(validCity)
                }.message shouldBe "Time limit exceeded!"
            }

            game.dropToFirst()
        }
    }
})