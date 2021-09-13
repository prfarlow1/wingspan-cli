package com.peterfarlow.app

import com.github.ajalt.clikt.core.CliktCommand
import com.peterfarlow.PlayerFoodSupply

class InitialDealFood : CliktCommand() {
    override fun run() {
        val players = Repository.getPlayers()
        val newPlayers = players.toMutableList()
        players.forEach {
            newPlayers += it.setFood(PlayerFoodSupply.initial)
        }
        Repository.savePlayers(newPlayers)
    }
}