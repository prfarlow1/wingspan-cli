package com.peterfarlow

import kotlinx.serialization.Serializable

@Serializable
data class PlayerState(
    val birds: PlayedBirds = PlayedBirds(),
    val hand: List<Bird> = emptyList(),
    val foodTokens: PlayerFoodSupply = PlayerFoodSupply(),
    val bonusCards: Set<BonusCard> = emptySet()
)

