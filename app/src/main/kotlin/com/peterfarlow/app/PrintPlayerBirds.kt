package com.peterfarlow.app

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.required
import com.peterfarlow.Bird

class PrintPlayerHand : CliktCommand() {

    private val name: String by option("--n", "--name").required()

    override fun run() {
        val player = Repository.getPlayer(name) ?: throw UnknownPlayer(name)
        echo("${player.name}'s hand:")
        player.state.hand.forEach {
            echo(formatBird(it))
        }
    }

    private fun formatBird(bird: Bird): String {
        with(bird) {
            val habitatStr = habitat.map { it.name.first() }
            return "$name, points=$points, habitat=$habitatStr, foodCost=$foodCost"
        }
    }
}