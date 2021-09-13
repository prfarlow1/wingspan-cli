package com.peterfarlow

import kotlinx.serialization.Serializable

enum class TurnAction {
    PLAY_BIRD,
    GAIN_FOOD,
    LAY_EGGS,
    DRAW_CARDS
}

enum class PowerType {
    BROWN,
    WHITE,
    PINK,
    NONE
}

enum class Habitat {
    FOREST,
    GRASSLAND,
    WETLAND;

    companion object {
        val all: Set<Habitat> = values().toSet()
        val forest: Set<Habitat> = setOf(FOREST)
        val grassland: Set<Habitat> = setOf(GRASSLAND)
        val wetland: Set<Habitat> = setOf(WETLAND)
        val forestGrassland: Set<Habitat> = setOf(FOREST, GRASSLAND)
        val grasslandWetland: Set<Habitat> = setOf(GRASSLAND, WETLAND)

    }
}

enum class NestType {
    PLATFORM,
    BOWL,
    CAVITY,
    GROUND,
    STAR,
    NONE
}

enum class FoodDiceFace {
    INVERTEBRATE,
    SEED,
    RODENT,
    FISH,
    FRUIT,
    INVERTEBRATE_SEED
}

@Serializable
data class PlayerFoodSupply(
    val invertebrates: Int = 0,
    val seeds: Int = 0,
    val rodents: Int = 0,
    val fish: Int = 0,
    val fruit: Int = 0
) {

    init {
        check(invertebrates >= 0)
        check(seeds >= 0)
        check(rodents >= 0)
        check(fish >= 0)
        check(fruit >= 0)
    }

    val total = invertebrates + seeds + rodents + fish + fruit

    fun countFor(food: Food): Int {
        return when (food) {
            Food.INVERTEBRATE -> invertebrates
            Food.SEED -> seeds
            Food.RODENT -> rodents
            Food.FISH -> fish
            Food.FRUIT -> fruit
            Food.WILD -> 0
        }
    }

    fun remove(food: Food): PlayerFoodSupply {
        return when (food) {
            Food.INVERTEBRATE -> copy(invertebrates = invertebrates - 1)
            Food.SEED -> copy(seeds = seeds - 1)
            Food.RODENT -> copy(rodents = rodents - 1)
            Food.FISH -> copy(fish = fish - 1)
            Food.FRUIT -> copy(fruit = fruit - 1)
            Food.WILD -> throw IllegalArgumentException("cannot remove wild")
        }
    }

    fun add(food: Food): PlayerFoodSupply {
        return when (food) {
            Food.INVERTEBRATE -> copy(invertebrates = invertebrates + 1)
            Food.SEED -> copy(seeds = seeds + 1)
            Food.RODENT -> copy(rodents = rodents + 1)
            Food.FISH -> copy(fish = fish + 1)
            Food.FRUIT -> copy(fruit = fruit + 1)
            Food.WILD -> throw IllegalArgumentException("cannot remove wild")
        }
    }

    companion object {
        val initial = PlayerFoodSupply(1, 1, 1, 1, 1)
    }
}

@Serializable
data class BirdFeeder(
    val food: Set<FoodDiceFace> = emptySet()
)

@Serializable
data class PlayedBirds(
    val forestBirds: List<Bird> = emptyList(),
    val grasslandBirds: List<Bird> = emptyList(),
    val wetlandBirds: List<Bird> = emptyList(),
)

data class PlayBirdAction(
    val bird: Bird,
    val foodCost: FoodCost,
    val eggCost: Int,
    val habitat: Habitat
)

enum class TokenColor {
    RED,
    BLUE,
    PURPLE,
    GREEN,
    YELLOW
}

@Serializable
data class GameState(
    val birdTray: Set<Bird> = emptySet(),
    val birdFeeder: BirdFeeder = BirdFeeder()
)

interface BonusCard {
    val name: String
    val description: String
    fun apply(playerState: PlayerState): PlayerState
}
