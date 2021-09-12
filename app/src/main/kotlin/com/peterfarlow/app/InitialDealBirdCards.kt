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
        val players = obtainPlayers()
        val deck = obtainDeck()
        val playersWithHand = mutableListOf<Player>()
        players.forEach {
            val removedCards = mutableSetOf<Bird>()
            repeat(5) {
                removedCards.add(deck.removeAt(0))
            }
            playersWithHand += it.drawBirds(removedCards)
        }
        savePlayers(playersWithHand)
    }

    private fun obtainPlayers(): List<Player> {
        val players = mutableListOf<Player>()
        val playersDir = CreatePlayers.PLAYERS_DIR.toFile()
        requireNotNull(playersDir.listFiles()) { "Failed to find players" }.map {
            players += fileToPlayer(it)
        }
        return players
    }

    private fun fileToPlayer(file: File): Player {
        file.source().use { fileSource ->
            fileSource.buffer().use { bufferedFileSource ->
                val rawData = bufferedFileSource.readUtf8()
                val obj = App.serializer.decodeFromString<Player>(rawData)
                echo("decoded as $obj")
                return obj
            }
        }
    }

    private fun obtainDeck(): MutableList<Bird> {
        CreateBirds.DECK_DIR.toFile().source().use {
            it.buffer().use { bufferedFileSource ->
                val rawData = bufferedFileSource.readUtf8()
                val obj = App.serializer.decodeFromString<Array<Bird>>(rawData)
                return obj.toMutableList()
            }
        }
    }

    private fun savePlayers(players: List<Player>) {
        players.forEach { player ->
            val playerName = player.name
            val encoding = App.serializer.encodeToString(player)
            echo("encoded player $playerName to $encoding")
            val playerFile = CreatePlayers.playerDir(playerName)
            playerFile.delete()
            playerFile.sink().buffer().use { sink ->
                sink.writeUtf8(encoding)
            }
        }
    }

}