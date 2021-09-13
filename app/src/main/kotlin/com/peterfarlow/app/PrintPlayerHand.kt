package com.peterfarlow.app

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.required
import com.peterfarlow.Bird
import com.peterfarlow.Player

class PrintPlayerHand : CliktCommand() {

    private val name: String by option("--n", "--name").required()

    override fun run() {
        val player = Repository.getPlayer(name) ?: throw UnknownPlayer(name)
        echo("${player.name}'s hand:")
        birdHandPrintStatements(player).forEach { echo(it) }
    }


    companion object {
        fun birdHandPrintStatements(player: Player): List<String> {
            val list = mutableListOf<String>()
            player.state.hand.forEachIndexed { index, bird ->
                list += formatBird(index, bird)
            }
            return list
        }

        private fun formatBird(index: Int, bird: Bird): String {
            with(bird) {
                val habitatStr = habitat.map { it.name.first() }
                return "$index: $name, points=$points, habitat=$habitatStr, foodCost=$foodCost"
            }
        }

    }
}