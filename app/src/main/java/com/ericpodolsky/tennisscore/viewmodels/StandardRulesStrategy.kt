package com.ericpodolsky.tennisscore.viewmodels

import android.arch.lifecycle.MutableLiveData
import com.ericpodolsky.tennisscore.models.PlayerModel
import com.ericpodolsky.tennisscore.models.TennisScores

class StandardRulesStrategy(private val minGamesToWinSet: Int = 6, private val setsToWinMatch: Int = 2) : RulesStrategy {
    override fun updatePlayersAfterIncrement(incrementedPlayerLiveData: MutableLiveData<PlayerModel>, staticPlayerLiveData: MutableLiveData<PlayerModel>) {
        val incrementedPlayer = incrementedPlayerLiveData.value
        val staticPlayer = staticPlayerLiveData.value
        if(incrementedPlayer != null && staticPlayer != null) {
            incrementedPlayer.rawGameScore++
            if(incrementedPlayer.rawGameScore >= 4 && incrementedPlayer.rawGameScore - staticPlayer.rawGameScore >= 2) {
                // win
                incrementedPlayer.rawGameScore = 0
                incrementedPlayer.gamesWon++
                staticPlayer.rawGameScore = 0

                if(incrementedPlayer.gamesWon >= minGamesToWinSet && incrementedPlayer.gamesWon - staticPlayer.gamesWon >= 2) {
                    incrementedPlayer.gamesWon = 0
                    staticPlayer.gamesWon = 0
                    incrementedPlayer.setsWon++
                    if(incrementedPlayer.setsWon >= setsToWinMatch) {
                        incrementedPlayer.matchWon = true
                    }
                }

                incrementedPlayer.isServer = !incrementedPlayer.isServer
                staticPlayer.isServer = !staticPlayer.isServer
            }

            incrementedPlayerLiveData.value = incrementedPlayer
            staticPlayerLiveData.value = staticPlayer
        }
    }

    override fun getFormattedScores(player1RawScore: Int?, player2RawScore: Int?): TennisScores? {
        if (player1RawScore != null && player2RawScore != null) {
            if(player1RawScore < 3 && player2RawScore < 4 || player1RawScore < 4 && player2RawScore < 3) {
                return TennisScores(TENNIS_SCORES[player1RawScore], TENNIS_SCORES[player2RawScore])
            } else if(player1RawScore == player2RawScore) {
                return TennisScores(DEUCE, DEUCE)
            } else if(Math.abs(player1RawScore - player2RawScore) == 1) {
                return if(player1RawScore > player2RawScore) {
                    TennisScores(AD, DISAD)
                } else {
                    TennisScores(DISAD, AD)
                }
            } else {
                return TennisScores(TENNIS_SCORES[0], TENNIS_SCORES[0])
            }
        }
        return null
    }

    companion object {
        val TENNIS_SCORES = arrayOf("0", "15", "30", "40")
        val DEUCE = "Deuce"
        val AD = "Ad"
        val DISAD = "Disad"
        val WIN = "Win"
        val LOSS = "Loss"
    }
}
