package com.example.septem.breakoutgame;

import java.util.Comparator;

/**
 * ScoreComparator class, written by Haowei Wang.
 * This class is for ordering in Min Heap of Rank Activity.
 */
public class ScoreComparator implements Comparator<PlayerAndScore> {
    @Override
    public int compare(PlayerAndScore ps1, PlayerAndScore ps2) {

        if (ps1.getScore() == ps2.getScore()) {
            return 0;
        }
        return ps1.getScore() < ps2.getScore() ? -1 : 1;
    }
}
