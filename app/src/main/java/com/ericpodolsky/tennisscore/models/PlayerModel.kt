package com.ericpodolsky.tennisscore.models

data class PlayerModel(
        val name: String,
        var isServer: Boolean,
        var rawGameScore: Int = 0,
        var gamesWon: Int = 0,
        var setsWon: Int = 0,
        var matchWon: Boolean = false)