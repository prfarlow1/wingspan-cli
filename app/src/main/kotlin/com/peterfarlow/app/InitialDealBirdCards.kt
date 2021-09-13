package com.peterfarlow.app

import com.github.ajalt.clikt.core.CliktCommand
import com.peterfarlow.Bird
import com.peterfarlow.Player
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import okio.buffer
import okio.sink
import okio.source
import java.io.File

class InitialDealBirdCards : CliktCommand() {

    override fun run() {
        val players = Repository.getPlayers()
        val deck = Repository.getBirdDeck()
        val playersWithHand = mutableListOf<Player>()
        players.forEach {
            val removedCards = mutableSetOf<Bird>()
            repeat(5) {
                removedCards.add(deck.removeAt(0))
            }
            playersWithHand += it.drawBirds(removedCards)
        }
        Repository.savePlayers(playersWithHand)
    }





}