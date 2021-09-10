package com.peterfarlow

data class PlayerState(
    val birds: PlayedBirds = PlayedBirds(),
    val hand: Set<Bird> = emptySet(),
    val foodTokens: PlayerFoodSupply = PlayerFoodSupply(),
    val bonusCards: Set<BonusCard> = emptySet()
)

