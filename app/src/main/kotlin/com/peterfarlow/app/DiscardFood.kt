package com.peterfarlow.app

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.CliktError
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.required
import com.peterfarlow.Food

class DiscardFood : CliktCommand() {

    private val name: String by option("--n", "--name").required()
    private val food: String by option("--f").required()

    override fun run() {
        val player = Repository.getPlayer(name) ?: throw UnknownPlayer(name)
        val foodType = Food.deserialize(food) ?: throw CliktError("unknown food type $food")
        if (player.state.foodTokens.countFor(foodType) == 0) throw CliktError("${player.name} doesn't have any $foodType")
        val newFood = player.state.foodTokens.remove(foodType)
        val newState = player.state.copy(foodTokens = newFood)
        Repository.savePlayer(player.copy(state = newState))
        echo("Removed $foodType from ${player.name}. New Food supply: $newFood")
    }
}