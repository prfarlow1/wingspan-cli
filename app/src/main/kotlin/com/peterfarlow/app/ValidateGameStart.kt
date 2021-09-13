package com.peterfarlow.app

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.CliktError

class ValidateGameStart : CliktCommand() {
    override fun run() {
        val players = Repository.getPlayers()
        players.forEach {
            val totalCardsInHand = it.state.hand.size
            val totalFood = it.state.foodTokens.total
            val total = totalFood + totalCardsInHand
            if (total > 5) {
                throw CliktError("${it.name} has more than 5 starting items: $totalFood food and $totalCardsInHand cards")
            }
            if (total < 5) {
                throw CliktError("${it.name} has less than 5 starting items: $totalFood food and $totalCardsInHand cards")
            }
        }
        echo("All players are ready to start")
    }
}