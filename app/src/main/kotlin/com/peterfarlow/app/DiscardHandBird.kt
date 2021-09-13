package com.peterfarlow.app

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.CliktError
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.required
import com.github.ajalt.clikt.parameters.types.int

class DiscardHandBird : CliktCommand() {

    private val name: String by option("--n", "--name").required()
    private val index: Int by option("--i").int().required()

    override fun run() {
        val player = Repository.getPlayer(name) ?: throw UnknownPlayer(name)
        val playerBirds = player.state.hand
        if (index !in 0..playerBirds.lastIndex) throw CliktError("${player.name} only has ${playerBirds.size} birds")
        val newBirds = playerBirds.toMutableList().apply {
            removeAt(index)
        }
        val newPlayer = player.copy(state = player.state.copy(hand = newBirds))
        Repository.savePlayer(newPlayer)
        echo("Discarded ")
        echo("new hand for ${player.name}:")
        PrintPlayerHand.birdHandPrintStatements(newPlayer).forEach { echo(it) }
    }
}