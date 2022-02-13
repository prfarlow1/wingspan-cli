package com.peterfarlow.app

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.CliktError
import com.github.ajalt.clikt.core.PrintMessage

class ValidateGameStart : CliktCommand() {
    override fun run() {
        val players = Repository.getPlayers()
        val offenders = mutableListOf<String>()
        players.forEach {
            val totalCardsInHand = it.state.hand.size
            val totalFood = it.state.foodTokens.total
            val total = totalFood + totalCardsInHand
            if (total > 5) {
                offenders+= "${it.name} has more than 5 starting items: $totalFood food and $totalCardsInHand cards"
            }
            if (total < 5) {
                offenders+= "${it.name} has less than 5 starting items: $totalFood food and $totalCardsInHand cards"
            }
        }
        if (offenders.isEmpty()) {
            echo("All players are ready to start")
        } else {
            echo("Game cannot start", err = true)
            throw PrintMessage(offenders.joinToString(separator = "\n"), error = true)
        }
    }
}