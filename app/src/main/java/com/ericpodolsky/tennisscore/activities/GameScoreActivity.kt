package com.ericpodolsky.tennisscore.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import com.ericpodolsky.tennisscore.R

class GameScoreActivity : AppCompatActivity() {
    val players: Pair<PlayerModel, PlayerModel> = Pair(PlayerModel("Player 1"), PlayerModel("Player 2"))
    var gameContainer: ConstraintLayout? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_score)
        gameContainer = findViewById(R.id.scoreContainer)
        updateScores(players.first.scoreValue, players.second.scoreValue, GameState.NO_WINNER)
        players.toList().forEachIndexed { index, element ->
            getPlayerView(index)?.findViewById<TextView>(R.id.player_name)?.text = element.name
            getPlayerView(index)?.setOnClickListener { v ->
                element.incrementScore()
                val gameState = findGameState(players.first.scoreValue, players.second.scoreValue)
                updateScores(players.first.scoreValue, players.second.scoreValue, gameState)
            }
        }
    }

    private fun getPlayerView(index: Int): FrameLayout? {
        return gameContainer?.getChildAt(index) as FrameLayout
    }

    private fun updateScores(player1Score: Int, player2Score: Int, gameState: GameState) {
        val player1ScoreTextView = getPlayerView(0)?.findViewById<TextView>(R.id.score)
        val player2ScoreTextView = getPlayerView(1)?.findViewById<TextView>(R.id.score)
        if(gameState == GameState.NO_WINNER) {
            player1ScoreTextView?.text = tennisScores[player1Score]
            player2ScoreTextView?.text = tennisScores[player2Score]
        } else if(gameState == GameState.DEUCE) {
            player1ScoreTextView?.text = "Deuce"
            player2ScoreTextView?.text = "Deuce"
        } else if(gameState == GameState.AD) {
            if(player1Score > player2Score) {
                player1ScoreTextView?.text = "Ad"
                player2ScoreTextView?.text = ""
            } else {
                player1ScoreTextView?.text = ""
                player2ScoreTextView?.text = "Ad"
            }
        } else {
            if(player1Score > player2Score) {
                player1ScoreTextView?.text = "Win"
                player2ScoreTextView?.text = ""
            } else {
                player1ScoreTextView?.text = ""
                player2ScoreTextView?.text = "Win"
            }
        }
    }
}
