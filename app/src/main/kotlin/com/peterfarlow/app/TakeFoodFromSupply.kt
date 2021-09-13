package com.peterfarlow.app

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.CliktError
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.required
import com.peterfarlow.Food

class TakeFoodFromSupply : CliktCommand() {

    private val name: String by option("--n", "--name").required()
    private val food: String by option("--f").required()

    override fun run() {
        val player = Repository.getPlayer(name) ?: throw UnknownPlayer(name)
        val foodType = Food.deserialize(food) ?: throw CliktError("unknown food $food")
        val newPlayer = player.addFood(foodType)
        echo("Added $foodType to ${player.name}. new supply:")
        echo(player.state.foodTokens.toString())
        Repository.savePlayer(newPlayer)
    }
}