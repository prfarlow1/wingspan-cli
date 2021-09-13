package com.peterfarlow.app

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.required

class PrintPlayerFood : CliktCommand() {

    private val name: String by option("--n", "--name").required()

    override fun run() {
        val player = Repository.getPlayer(name) ?: throw UnknownPlayer(name)
        echo("${player.name}'s food supply:")
        echo(player.state.foodTokens.toString())

    }
}