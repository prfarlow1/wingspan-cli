package com.peterfarlow

import kotlinx.serialization.Serializable

@Serializable
data class Bird(
    val name: String,
    val points: Points = 0,
    val habitat: Set<Habitat>,
    val foodCost: FoodCost,
    val nestType: NestType,
    val wingspan: Int,
    val eggCapacity: Int = 0,
    val powerType: PowerType,
    val powerText: String = "",
)

@Serializable
data class BirdState(
    val bird: Bird,
    val eggs: Int = 0,
    val tuckedCards: Set<Bird> = emptySet(),
    val cachedFood: Set<Food> = emptySet()
)

typealias Points = Int