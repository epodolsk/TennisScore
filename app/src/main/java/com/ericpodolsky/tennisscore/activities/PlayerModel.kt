package com.ericpodolsky.tennisscore.activities

class PlayerModel(name: String) {
    val name: String = name
    var scoreValue: Int = 0

    fun incrementScore() {
        scoreValue++
    }
}