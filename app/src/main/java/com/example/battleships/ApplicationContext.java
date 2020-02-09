package com.example.battleships;

import android.widget.Button;

public class ApplicationContext {

    boolean bonus;

    Integer playerShipsHit = 0;
    Integer computerShipsHit = 0;

    Cell[][] playerField = new Cell[3][3];
    Cell[][] computerField = new Cell[3][3];

    Button[][] computerButtons;
    Button[][] playerButtons;

    Integer numberOfBullets = 0;
    Integer finalScore = 0;
    Integer numberOfTurns = 1;

    boolean isWinner;

    boolean gameHasFinished;
    boolean gameHasStarted;

    String category;
    Integer question = 1;

    private static ApplicationContext context = new ApplicationContext();

    private ApplicationContext() {
    }

    public static ApplicationContext getContext(){
        return context;
    }
}
