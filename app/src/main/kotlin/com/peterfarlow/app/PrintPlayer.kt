package com.peterfarlow.app

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.required
import com.github.ajalt.clikt.parameters.types.file
import com.peterfarlow.Player
import com.peterfarlow.PlayerState
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import okio.buffer
import okio.source
import java.io.File

class PrintPlayer : CliktCommand() {

    private val name: String by option("--n", "--name").required()

    override fun run() {
        echo("${Repository.getPlayer(name)}")
    }
}