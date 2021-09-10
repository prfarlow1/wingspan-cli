package com.peterfarlow

enum class Food(val symbol: String) {
    INVERTEBRATE("I"),
    SEED("S"),
    RODENT("R"),
    FISH("F"),
    FRUIT("C"),
    WILD("W");

    companion object {
        fun deserialize(symbol: Char): Food = values().first { it.symbol == symbol.toString() }
    }
}