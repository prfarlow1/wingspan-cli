package com.peterfarlow

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
    WETLAND
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

data class PlayerFoodSupply(
    val worms: Int = 0,
    val wheat: Int = 0,
    val mice: Int = 0,
    val fish: Int = 0,
    val cherries: Int = 0
)

data class BirdFeeder(
    val food: Set<FoodDiceFace> = emptySet()
)

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

data class Player(
    val id: Int,
    val name: String,
    val tokenColor: TokenColor,
    val state: PlayerState = PlayerState(),
)

enum class TokenColor {
    RED,
    BLUE,
    PURPLE,
    GREEN,
    YELLOW
}

data class GameState(
    val birdTray: Set<Bird> = emptySet(),
    val birdFeeder: BirdFeeder = BirdFeeder()
)

interface BonusCard {
    val name: String
    val description: String
    fun apply(playerState: PlayerState): PlayerState
}
