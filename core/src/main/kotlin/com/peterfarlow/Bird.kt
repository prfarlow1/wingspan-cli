package com.peterfarlow

data class Bird(
    val name: String,
    val latinName: String = "",
    val points: Points = 0,
    val habitat: Set<Habitat>,
    val foodCost: FoodCost,
    val nestType: NestType,
    val description: String = "",
    val wingspan: Int,
    val eggs: Int = 0,
    val eggCapacity: Int = 0,
    val powerType: PowerType,
    val powerText: String,
    val tuckedCards: Set<Bird> = emptySet(),
    val cachedFood: Set<Food> = emptySet(),
)

typealias Points = Int