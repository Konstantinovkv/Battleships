package com.example.battleships_0;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.example.battleships_0.context.ApplicationContext;
import java.util.Random;

public class Fields extends AppCompatActivity {

    private Random randomNum = new Random();

    private Cell[][] playerField;
    private Cell[][] computerField = new Cell[3][3];

    private Button[][] computerButtons;
    private Button[][] playerButtons;

    private Button one, two, three, four, five, six, seven, eight, nine,
            back, next,
            pcOne, pcTwo, pcThree, pcFour, pcFive, pcSix, pcSeven, pcEight, pcNine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fields);

        initializeButtons();

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ApplicationContext.getContext().getNumberOfBullets() > 0){
                    makeToast("You have more bullets.");
                    return;
                }
                goToAsk();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToMain();
            }
        });

        if (ApplicationContext.getContext().isGameHasStarted()){
            repopulateField();
            if (ApplicationContext.getContext().getNumberOfTurns()>2){
                placeSecondShip();
            }
            if (ApplicationContext.getContext().getNumberOfBullets() == 0){
                shootAtPlayer();
            }
            ApplicationContext.getContext().setNumberOfTurns(ApplicationContext.getContext().getNumberOfTurns()+1);
            ApplicationContext.getContext().setFields(this);
            if (ApplicationContext.getContext().isGameHasFinished()){
                endGameClear();
                Intent intent = new Intent(this, WinLose.class);
                startActivity(intent);
                return;
            }
            return;
        }

        pcFieldOnClicker();

        makePcArr();
        makePlayerArr();

        buttonColourSetter();

        playerField = ApplicationContext.getContext().getPlayerField();

        playerShipMarker();

        placeFirstShip();
        ApplicationContext.getContext().setGameHasStarted(true);
        if (ApplicationContext.getContext().getNumberOfBullets() == 0){
            shootAtPlayer();
        }
        ApplicationContext.getContext().setFields(this);
        ApplicationContext.getContext().setNumberOfTurns(ApplicationContext.getContext().getNumberOfTurns()+1);
    }

    private void repopulateField(){
        makePcArr();
        makePlayerArr();
        playerField = ApplicationContext.getContext().getFields().getPlayerField();
        computerField = ApplicationContext.getContext().getFields().getComputerField();
        buttonColourSetter();
        pcFieldOnClicker();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToMain();
            }
        });

        for (int i = 0; i < playerField.length; i++) {
            for (int j = 0; j < playerField[i].length; j++) {
                if (playerField[i][j].hasShip){
                    playerButtons[i][j].setBackgroundColor(Color.rgb(98, 34, 121));
                }
                if (playerField[i][j].isHit && playerField[i][j].hasShip){
                    playerButtons[i][j].setBackgroundColor(Color.RED);
                }
                if (playerField[i][j].isHit && !playerField[i][j].hasShip){
                    playerButtons[i][j].setBackgroundColor(Color.rgb(189, 41, 127));
                }
                if (computerField[i][j].isHit && computerField[i][j].hasShip){
                    computerButtons[i][j].setBackgroundColor(Color.RED);
                }
                if (computerField[i][j].isHit && !computerField[i][j].hasShip){
                    computerButtons[i][j].setBackgroundColor(Color.rgb(189, 41, 127));
                }
            }

        }
    }

    private void pcFieldOnClicker(){
        pcOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickMethods(0,0, pcOne);
            }
        });

        pcTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickMethods(0,1, pcTwo);
            }
        });

        pcThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickMethods(0,2, pcThree);
            }
        });

        pcFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickMethods(1,0, pcFour);
            }
        });

        pcFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickMethods(1,1, pcFive);
            }
        });

        pcSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickMethods(1,2, pcSix);
            }
        });

        pcSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickMethods(2,0, pcSeven);
            }
        });

        pcEight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickMethods(2,1, pcEight);
            }
        });

        pcNine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickMethods(2,2, pcNine);
            }
        });
    }

    private void clickMethods(int x, int y, Button fieldButton){
        if (ApplicationContext.getContext().getNumberOfBullets() == 0){
            makeToast("You need to answer questions correctly for bullets.");
            return;
        }
        if(checkIfHit(x,y) || ApplicationContext.getContext().isGameHasFinished()){
            return;
        }
        if (shootAtComputer(x,y)){
            fieldButton.setBackgroundColor(Color.RED);
        }
        else{
            fieldButton.setBackgroundColor(Color.rgb(189, 41, 127));
        }
        if (ApplicationContext.getContext().isGameHasFinished()){
            endGameClear();
            Intent intent = new Intent(this, WinLose.class);
            startActivity(intent);
            return;
        }
        if (ApplicationContext.getContext().getNumberOfBullets() > 0){
            makeToast("You have "+ApplicationContext.getContext().getNumberOfBullets()+" more shots.");
            return;
        }
        shootAtPlayer();
        ApplicationContext.getContext().setFields(this);
        goToAsk();
    }

    private void endGameClear(){
        ApplicationContext.getContext().setFields(null);
        ApplicationContext.getContext().setComputerShipsHit(0);
        ApplicationContext.getContext().setPlayerShipsHit(0);
        ApplicationContext.getContext().setGameHasFinished(false);
        ApplicationContext.getContext().setGameHasStarted(false);
        ApplicationContext.getContext().setWinner(false);
        ApplicationContext.getContext().setNumberOfTurns(1);
        ApplicationContext.getContext().setQuestion(1);
    }

    private void initializeButtons(){
        one = findViewById(R.id.cell_1);
        two = findViewById(R.id.cell_2);
        three = findViewById(R.id.cell_3);
        four = findViewById(R.id.cell_4);
        five = findViewById(R.id.cell_5);
        six = findViewById(R.id.cell_6);
        seven = findViewById(R.id.cell_7);
        eight = findViewById(R.id.cell_8);
        nine = findViewById(R.id.cell_9);

        back = findViewById(R.id.back_fields);
        next = findViewById(R.id.next_question);

        pcOne = findViewById(R.id.comp_cell_1);
        pcTwo = findViewById(R.id.comp_cell_2);
        pcThree = findViewById(R.id.comp_cell_3);
        pcFour = findViewById(R.id.comp_cell_4);
        pcFive = findViewById(R.id.comp_cell_5);
        pcSix = findViewById(R.id.comp_cell_6);
        pcSeven = findViewById(R.id.comp_cell_7);
        pcEight = findViewById(R.id.comp_cell_8);
        pcNine = findViewById(R.id.comp_cell_9);
    }

    private void playerShipMarker(){
        for (int i = 0; i < playerField.length; i++) {
            for (int j = 0; j <playerField[i].length ; j++) {
                if (playerField[i][j].hasShip){
                    playerButtons[i][j].setBackgroundColor(Color.rgb(98, 34, 121));
                }
            }
        }
    }

    private void buttonColourSetter(){
        for (int i = 0; i < computerField.length; i++) {
            for (int j = 0; j < computerField[i].length; j++) {
                computerButtons[i][j].setBackgroundColor(Color.rgb(61,108,180));
                playerButtons[i][j].setBackgroundColor(Color.rgb(61,108,180));
            }
        }
    }

    private void populateComputerField(){
        int counter = 1;
        for (int i = 0; i < computerField.length; i++) {
            for (int j = 0; j < computerField[i].length; j++) {
                computerField[i][j] = new Cell(counter++);
            }
        }
    }

    private void placeFirstShip(){
        populateComputerField();
        int x = randomNum.nextInt(3);
        int y = randomNum.nextInt(3);
        computerField[x][y].hasShip = true;
    }

    private boolean placeSecondShip(){
        int x = randomNum.nextInt(3);
        int y = randomNum.nextInt(3);

        if (!computerField[x][y].hasShip && !computerField[x][y].isHit) {
            if (x + 1 <= 2) {
                if (!computerField[x + 1][y].hasShip && !computerField[x + 1][y].isHit) {
                    computerField[x][y].hasShip= true;
                    computerField[x+1][y].hasShip = true;
                }
            }
            else if (x - 1 >= 0) {
                if (!computerField[x - 1][y].hasShip && !computerField[x - 1][y].isHit) {
                    computerField[x][y].hasShip= true;
                    computerField[x-1][y].hasShip = true;
                }
            }
            else if (y + 1 <= 2) {
                if (!computerField[x][y+1].hasShip && !computerField[x][y+1].isHit) {
                    computerField[x][y].hasShip= true;
                    computerField[x][y+1].hasShip = true;
                }
            }
            else if (y - 1 >= 0) {
                if (!computerField[x][y-1].hasShip && !computerField[x][y-1].isHit) {
                    computerField[x][y].hasShip= true;
                    computerField[x][y-1].hasShip = true;
                }
            }
        }
        return false;
    }

    private void shootAtPlayer(){
        if (ApplicationContext.getContext().getNumberOfTurns() < 3){
            for (int i = 0; i < playerField.length; i++) {
                for (int j = 0; j < playerField[i].length; j++) {
                    if (!playerField[i][j].hasShip && !playerField[i][j].isHit){
                        playerButtons[i][j].setBackgroundColor(Color.rgb(189, 41, 127));
                        playerField[i][j].isHit=true;
                        return;
                    }
                }
            }
        }
        else{
            for (int i = 0; i < playerField.length; i++) {
                for (int j = 0; j < playerField[i].length; j++) {
                    if (playerField[i][j].hasShip && !playerField[i][j].isHit){
                        playerButtons[i][j].setBackgroundColor(Color.RED);
                        ApplicationContext.getContext().setPlayerShipsHit(ApplicationContext.getContext().getPlayerShipsHit()+1);
                        playerField[i][j].isHit=true;
                        if (ApplicationContext.getContext().getPlayerShipsHit() == 3){
                            makeToast("You have lost the game!");
                            System.out.println("DAJKSGDKJASGDKJSADGKAFA");
                            ApplicationContext.getContext().setGameHasFinished(true);
                        }
                        return;
                    }
                }
            }
        }
    }

    private boolean shootAtComputer(int x, int y){
        ApplicationContext.getContext().setNumberOfBullets(ApplicationContext.getContext().getNumberOfBullets()-1);
        computerField[x][y].isHit = true;
        if(computerField[x][y].hasShip){
            ApplicationContext.getContext().setComputerShipsHit(ApplicationContext.getContext().getComputerShipsHit()+1);
            if (ApplicationContext.getContext().getComputerShipsHit() == 3){
                makeToast("Congratulations!\n You have won the game!");
                ApplicationContext.getContext().setGameHasFinished(true);
                return true;
            }
            return true;
        }
        return false;
    }

    private void makePcArr(){
        computerButtons = new Button[][]{
                {pcOne, pcTwo, pcThree},
                {pcFour, pcFive, pcSix},
                {pcSeven, pcEight, pcNine}
        };
    }

    private void makePlayerArr(){
        playerButtons = new Button[][]{
                {one, two, three},
                {four, five, six},
                {seven, eight, nine}
        };
    }

    private boolean checkIfHit(int x, int y){
        if (computerField[x][y].isHit){
            makeToast("You cannot hit the same field twice.");
            return true;
        }
        return false;
    }

    private void backToMain(){
        Intent intent = new Intent(this, Categories.class);
        startActivity(intent);
    }

    private void goToAsk(){
        Intent intent = new Intent(this, Ask.class);
        startActivity(intent);
    }

    private void makeToast(String text){
        Toast.makeText(getApplicationContext(),text, Toast.LENGTH_SHORT).show();
    }

    public Cell[][] getPlayerField() {
        return playerField;
    }

    public Cell[][] getComputerField() {
        return computerField;
    }
}


