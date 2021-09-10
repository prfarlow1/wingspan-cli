package com.peterfarlow

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream


class FoodCostTest {

    private val testMap: List<Triple<FoodCost, List<Food>, FoodCostOutcome>> = listOf(
        Triple("any-ISR".toFoodCost(), "I".toFoodList(), FoodCostOutcome.Success("I".toFoodList())),
        Triple("all-RCF".toFoodCost(), "C".toFoodList(), FoodCostOutcome.Failure("all-RF".toFoodCost())),
        Triple(FoodCost.None, emptyList(), FoodCostOutcome.Success(emptyList())),
        Triple("all-SSS".toFoodCost(), "SS".toFoodList(), FoodCostOutcome.Failure("all-S".toFoodCost())),
        Triple("all-RRSWW".toFoodCost(), "FFFFFFFFSRR".toFoodList(), FoodCostOutcome.Success("SRRFF".toFoodList())),
        Triple("all-CCSRW".toFoodCost(), "CCCCCCCCCC".toFoodList(), FoodCostOutcome.Failure("all-SR".toFoodCost()))
    )

    @Test
    fun testFood() {
        testMap.forEach {
            assertEquals(
                it.third,
                it.first.payFoodCost(it.second),
                "input ${it.first} ${it.second}",
            )
        }
    }
}