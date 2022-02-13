package com.peterfarlow

import kotlinx.serialization.Serializable

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

    fun remove(vararg food: Food): PlayerFoodSupply {
        var newSupply = this
        food.forEach {
            newSupply = when (it) {
                Food.INVERTEBRATE -> copy(invertebrates = invertebrates - 1)
                Food.SEED -> copy(seeds = seeds - 1)
                Food.RODENT -> copy(rodents = rodents - 1)
                Food.FISH -> copy(fish = fish - 1)
                Food.FRUIT -> copy(fruit = fruit - 1)
                Food.WILD -> throw IllegalArgumentException("cannot remove wild")
            }
        }
        return newSupply
    }

    fun add(vararg food: Food): PlayerFoodSupply {
        var newSupply = this
        food.forEach {
            newSupply = when (it) {
                Food.INVERTEBRATE -> copy(invertebrates = invertebrates + 1)
                Food.SEED -> copy(seeds = seeds + 1)
                Food.RODENT -> copy(rodents = rodents + 1)
                Food.FISH -> copy(fish = fish + 1)
                Food.FRUIT -> copy(fruit = fruit + 1)
                Food.WILD -> throw IllegalArgumentException("cannot add wild")
            }
        }
        return newSupply
    }

    companion object {
        val initial = PlayerFoodSupply(1, 1, 1, 1, 1)
    }
}