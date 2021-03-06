package com.peterfarlow.app

import com.peterfarlow.Bird
import com.peterfarlow.Player
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import okio.buffer
import okio.sink
import okio.source
import java.io.File

object Repository {

    fun getPlayers(): List<Player> {
        val players = mutableListOf<Player>()
        val playersDir = CreatePlayers.PLAYERS_DIR.toFile()
        requireNotNull(playersDir.listFiles()) { "Failed to find players" }.map {
            players += fileToPlayer(it)
        }
        return players
    }

    fun getPlayer(name: String): Player? {
        val file = CreatePlayers.playerDir(name)
        val player: Player?
        file.source().use { fileSource ->
            fileSource.buffer().use { bufferedFileSource ->
                val rawData = bufferedFileSource.readUtf8()
                player = if (rawData.isEmpty()) {
                    null
                } else {
                    App.serializer.decodeFromString<Player>(rawData)
                }
            }
        }
        if (player == null) {
            file.delete()
        }
        return player
    }

    fun getBirdDeck(): MutableList<Bird> {
        CreateBirds.DECK_DIR.toFile().source().use {
            it.buffer().use { bufferedFileSource ->
                val rawData = bufferedFileSource.readUtf8()
                val obj = App.serializer.decodeFromString<List<Bird>>(rawData)
                return obj.toMutableList()
            }
        }
    }

    fun discard(bird: Bird) {
        val file = CreateBirds.DISCARD_DIR.toFile()
        val new: List<Bird>
        file.source().use {
            it.buffer().use { bufferedFileSource ->
                val rawData = bufferedFileSource.readUtf8()
                val existingDeck = if (rawData.isEmpty()) {
                    emptyList<Bird>()
                } else {
                    App.serializer.decodeFromString(rawData)
                }
                new = existingDeck.toMutableList().apply {
                    add(bird)
                }
            }
        }
        file.sink().use {
            it.buffer().use {  bufferedFileSink ->
                val raw = App.serializer.encodeToString(new)
                bufferedFileSink.writeUtf8(raw)
            }
        }
    }

    fun savePlayer(player: Player) {
        savePlayers(listOf(player))
    }

    fun savePlayers(vararg players: Player) {
        savePlayers(players.toList())
    }

    fun savePlayers(players: List<Player>) {
        players.forEach { player ->
            val playerName = player.name
            val encoding = App.serializer.encodeToString(player)
            val playerFile = CreatePlayers.playerDir(playerName)
            playerFile.delete()
            playerFile.sink().buffer().use { sink ->
                sink.writeUtf8(encoding)
            }
        }
    }

    private fun fileToPlayer(file: File): Player {
        file.source().use { fileSource ->
            fileSource.buffer().use { bufferedFileSource ->
                val rawData = bufferedFileSource.readUtf8()
                return App.serializer.decodeFromString(rawData)
            }
        }
    }
}