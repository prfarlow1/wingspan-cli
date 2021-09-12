package com.peterfarlow.app

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.UsageError
import com.github.ajalt.clikt.parameters.options.multiple
import com.github.ajalt.clikt.parameters.options.option
import com.peterfarlow.Player
import com.peterfarlow.TokenColor
import com.peterfarlow.app.App.Companion.serializer
import kotlinx.serialization.encodeToString
import okio.buffer
import okio.sink
import java.io.File
import java.nio.file.Paths
import java.util.*

class CreatePlayers : CliktCommand() {

    private val players: List<String> by option("--p", "--player").multiple(
        default = listOf("T.J.", "Alex", "Peter"),
    )

    override fun run() {
        validateNumPlayers()
        createPlayers()
    }

    private fun validateNumPlayers() {
        if (players.size <= 1) {
            throw UsageError("Must supply between two and six players")
        } else if (players.distinct().size != players.size) {
            throw UsageError("each player name must be unique")
        } else {
            echo("You entered ${players.size} players:")
            players.forEach { echo(it) }
        }
    }

    private fun createPlayers() {
        var id = 0
        val playerList = players.map {
            Player(id = id, name = it, tokenColor = TokenColor.values()[id++])
        }
        playerList.forEach { player ->
            val playerName = player.name
            val encoding = serializer.encodeToString(player)
            echo("encoded player $playerName to $encoding")
            playerDir(playerName).sink().buffer().use { sink ->
                sink.writeUtf8(encoding)
            }
        }
    }

    companion object {
        fun playerDir(name: String): File {
            val fileName = name.replace(".", "").trim().lowercase(Locale.US)
            return Paths.get(App.GAME_DIR, fileName).toFile().apply {
                if (!exists()) {
                    createNewFile()
                }
            }
        }
    }
}