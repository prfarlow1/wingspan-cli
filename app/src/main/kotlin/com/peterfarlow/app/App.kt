/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package com.peterfarlow.app

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.subcommands
import kotlinx.serialization.json.Json
import java.nio.file.Paths

class App : CliktCommand(invokeWithoutSubcommand = true, allowMultipleSubcommands = true) {

    companion object {
        const val GAME_DIR = "game"

        val serializer = Json { prettyPrint = true }
    }

    override fun run() {
        Paths.get(GAME_DIR).toFile().apply {
            mkdir()
        }
        Paths.get(GAME_DIR, CreatePlayers.PLAYERS_DIR_NAME).toFile().apply {
            mkdir()
        }
    }
}

fun main(args: Array<String>) = App().subcommands(
    CreatePlayers(), CreateBirds(), PrintPlayer(), InitialDealBirdCards(),
    InitialDealFood(), PrintPlayerHand(), PrintPlayerFood(), DiscardFood(), DiscardHandBird(),
    ValidateGameStart(), TakeFoodFromSupply()
).main(args)
