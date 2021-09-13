package com.peterfarlow.app

import com.github.ajalt.clikt.core.UsageError

class UnknownPlayer(name: String) : UsageError("unknown player $name")