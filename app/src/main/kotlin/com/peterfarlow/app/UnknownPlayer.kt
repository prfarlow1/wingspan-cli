package com.peterfarlow.app

import com.github.ajalt.clikt.core.CliktError

class UnknownPlayer(name: String) : CliktError("unknown player $name")