package com.peterfarlow.app

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.requireObject
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.types.file
import com.peterfarlow.Player
import com.peterfarlow.app.App.Companion.serializer
import kotlinx.serialization.encodeToString
import okio.buffer
import okio.sink
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

class SavePlayerState : CliktCommand() {

    private val player: Player by requireObject()

    private val file: File? by option("--file").file(canBeDir = false)
    private val fileName: String? by option("--filename")

    override fun run() {
        val playerName = player.name
        val finalFile: File = when {
            file?.exists() == true -> file!!
            !fileName.isNullOrEmpty() -> Files.createFile(Paths.get(fileName!!)).toFile()
            else -> Files.createFile(Paths.get(playerName)).toFile()
        }
        val encoding = serializer.encodeToString(player)
        finalFile.sink().buffer().use { sink ->
            sink.writeUtf8(encoding)
        }
    }
}