package com.example.septem.breakoutgame;

/**
 * Player name and score class, written by Haowei Wang on 5/2/16.
 */
public class PlayerAndScore {

    // This class consist of player's name and score.
    private String player;
    private int score;

    public PlayerAndScore(String player, int score) {
        this.player = player;
        this.score = score;
    }

    public String getPlayer() {
        return player;
    }

    public int getScore() {
        return score;
    }
}
