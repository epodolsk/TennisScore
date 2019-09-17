package com.ericpodolsky.tennisscore.activities

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.ericpodolsky.tennisscore.R
import com.ericpodolsky.tennisscore.databinding.ActivityGameScoreBinding
import com.ericpodolsky.tennisscore.viewmodels.GameViewModel

class GameScoreActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityGameScoreBinding = DataBindingUtil.setContentView(this, R.layout.activity_game_score)

        binding.lifecycleOwner = this
        val viewModel = GameViewModel()
        binding.viewModel = viewModel
        /*
        viewModel.player1ModelLiveData.observe(this, Observer {playerData ->
            if(playerData?.gameScore == GameScore.WIN) {
                Toast.makeText(this, "Player 1 wins", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.player2ModelLiveData.observe(this, Observer {playerData ->
            if(playerData?.gameScore == GameScore.WIN) {
                Toast.makeText(this, "Player 2 wins", Toast.LENGTH_SHORT).show()
            }
        })
        */
    }

    /*
    private fun showVictoryFragment() {
        val fragment = VictoryFragment()


    }

     */
}
