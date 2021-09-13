package com.peterfarlow

import kotlinx.serialization.Serializable

@Serializable
data class Player(
    val id: Int,
    val name: String,
    val tokenColor: TokenColor,
    val state: PlayerState = PlayerState(),
) {
    fun drawBirds(cards: Collection<Bird>): Player {
        val newHand = state.hand.plus(cards)
        return copy(state = state.copy(hand = newHand))
    }

    fun setFood(foodSupply: PlayerFoodSupply): Player {
        return copy(state = state.copy(foodTokens = foodSupply))
    }

    fun removeFood(food: Food): Player {
        val new = state.foodTokens.remove(food)
        return copy(state = state.copy(foodTokens =  new))
    }

    fun addFood(food: Food): Player {
        val new = state.foodTokens.add(food)
        return copy(state = state.copy(foodTokens = new))
    }
}