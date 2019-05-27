package com.ericpodolsky.tennisscore.viewmodels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.ericpodolsky.tennisscore.models.PlayerModel
import com.ericpodolsky.tennisscore.models.TennisScores

class GameViewModel (player1Model: PlayerModel = PlayerModel("Player 1", true),
                     player2Model: PlayerModel = PlayerModel("Player 2", false),
                     private val rulesStrategy: RulesStrategy = StandardRulesStrategy()) : ViewModel() {
    val player1ModelLiveData = MutableLiveData<PlayerModel>()
    val player2ModelLiveData = MutableLiveData<PlayerModel>()
    val formattedScores = MutableLiveData<TennisScores>()

    init {
        player1ModelLiveData.value = player1Model
        player2ModelLiveData.value = player2Model
        updateFormattedScores()
    }

    fun incrementPlayer1Score() {
        rulesStrategy.updatePlayersAfterIncrement(player1ModelLiveData, player2ModelLiveData)
        updateFormattedScores()
    }

    fun incrementPlayer2Score() {
        rulesStrategy.updatePlayersAfterIncrement(player2ModelLiveData, player1ModelLiveData)
        updateFormattedScores()
    }

    private fun updateFormattedScores() {
        formattedScores.value = rulesStrategy.getFormattedScores(player1ModelLiveData.value?.rawGameScore, player2ModelLiveData.value?.rawGameScore)
    }
}