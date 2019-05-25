package com.ericpodolsky.tennisscore.activities

val tennisScores = arrayOf("0", "15", "30", "40")

fun findGameState(player1Score: Int, player2Score: Int): GameState {
    val difference = Math.abs(player1Score - player2Score)
    val higherScore = Math.max(player1Score, player2Score)
    when (difference) {
        0 -> return if(higherScore >= 3) {
            GameState.DEUCE
        } else {
            GameState.NO_WINNER
        }
        1 -> return if(higherScore >= 4) {
            GameState.AD
        } else {
            GameState.NO_WINNER
        }
        else -> return if(higherScore >= 4) {
            GameState.WIN
        } else {
            GameState.NO_WINNER
        }
    }
}