package com.peterfarlow

import kotlinx.serialization.Serializable

@Serializable
data class PlayerState(
    val birds: PlayedBirds = PlayedBirds(),
    val hand: Set<Bird> = emptySet(),
    val foodTokens: PlayerFoodSupply = PlayerFoodSupply(),
    val bonusCards: Set<BonusCard> = emptySet()
)

