package com.peterfarlow

import kotlinx.serialization.Serializable

@Serializable
sealed class FoodCost {

    abstract val items: Collection<Food>

    abstract fun payFoodCost(availableFood: Collection<Food>): FoodCostOutcome

    companion object {
        val none = None
        val justWild = JustWild
    }
}

@Serializable
object JustWild : FoodCost() {
    override val items: Collection<Food> = setOf(Food.WILD)

    override fun payFoodCost(availableFood: Collection<Food>): FoodCostOutcome {
        return if (availableFood.isEmpty()) {
            FoodCostOutcome.Failure(this)
        } else {
            val usedFood = availableFood.toMutableList().apply {
                removeFirst()
            }
            FoodCostOutcome.Success(usedFood)
        }
    }

    override fun toString() = "JustWild"
}

@Serializable
object None : FoodCost() {

    override val items: Collection<Food> = emptyList()

    override fun payFoodCost(availableFood: Collection<Food>) =
        FoodCostOutcome.Success(emptyList())

    override fun toString() = "None"
}

@Serializable
data class Any(override val items: Collection<Food>) : FoodCost() {

    override fun payFoodCost(availableFood: Collection<Food>): FoodCostOutcome {
        if (availableFood.isEmpty()) return FoodCostOutcome.Failure(this)
        val remainingFood = availableFood.toMutableList()
        for (food in items) {
            if (remainingFood.remove(food)) {
                return FoodCostOutcome.Success(listOf(food))
            }
        }
        return FoodCostOutcome.Failure(this)
    }
}

@Serializable
data class All(override val items: Collection<Food>) : FoodCost() {

    override fun payFoodCost(availableFood: Collection<Food>): FoodCostOutcome {
        if (availableFood.isEmpty()) return FoodCostOutcome.Failure(this)
        val remainingFood = availableFood.toMutableList()
        val missingFood = mutableListOf<Food>()
        val usedFood = mutableListOf<Food>()
        val sortedFoodCost = items.sorted() // put the wilds last
        for (food in sortedFoodCost) {
            if (remainingFood.remove(food)) {
                usedFood.add(food)
            } else {
                if (food == Food.WILD) {
                    if (remainingFood.isEmpty()) {
                        missingFood.add(food)
                    } else {
                        val first = remainingFood.first()
                        remainingFood.remove(first)
                        usedFood.add(first)
                    }
                } else {
                    missingFood.add(food)
                }
            }
        }
        return if (missingFood.isEmpty()) {
            FoodCostOutcome.Success(usedFood)
        } else {
            FoodCostOutcome.Failure(All(missingFood))
        }
    }
}

sealed class FoodCostOutcome {
    data class Success(val foodUsed: Collection<Food>) : FoodCostOutcome()
    data class Failure(val missingFood: FoodCost) : FoodCostOutcome()

    val isSuccess get() = this is Success
}

fun String.toFoodCost(): FoodCost {
    val parts = split("-")
    check(parts.size == 2)
    return when {
        parts[0] == "any" -> {
            Any(parts[1].toFoodList())
        }
        parts[0] == "all" -> {
            All(parts[1].toFoodList())
        }
        else -> {
            throw IllegalArgumentException()
        }
    }
}

fun String.toFoodList(): List<Food> = toCharArray().map { Food.deserialize(it) }