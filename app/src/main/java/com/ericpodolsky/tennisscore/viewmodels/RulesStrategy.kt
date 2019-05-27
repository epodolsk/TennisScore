package com.ericpodolsky.tennisscore.viewmodels

import android.arch.lifecycle.MutableLiveData
import com.ericpodolsky.tennisscore.models.PlayerModel
import com.ericpodolsky.tennisscore.models.TennisScores

interface RulesStrategy {
    fun updatePlayersAfterIncrement(incrementedPlayerLiveData: MutableLiveData<PlayerModel>, staticPlayerLiveData: MutableLiveData<PlayerModel>)

    fun getFormattedScores(player1RawScore: Int?, player2RawScore: Int?): TennisScores?
}
