package com.peterfarlow.app

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.required
import com.github.ajalt.clikt.parameters.types.file
import com.peterfarlow.PlayerState
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import okio.buffer
import okio.source
import java.io.File

class PrintPlayerState : CliktCommand() {

    private val playerStoreLocation: File by option("--file")
        .file(mustExist = true, canBeDir = false, mustBeReadable = true).required()

    override fun run() {
        readLines(playerStoreLocation)
    }

    private fun readLines(file: File) {
        file.source().use { fileSource ->
            fileSource.buffer().use { bufferedFileSource ->
                val rawData = bufferedFileSource.readUtf8()
                val obj = Json.decodeFromString<PlayerState>(rawData)
                echo("decoded as $obj")
            }
        }
    }
}