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
}