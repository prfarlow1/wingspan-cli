package com.peterfarlow

import kotlinx.serialization.Serializable

@Serializable
data class Player(
    val id: Int,
    val name: String,
    val tokenColor: TokenColor,
    val state: PlayerState = PlayerState(),
)