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

    int numberOfTurns;
    int bullets = 1;
    int finalScore;
    private Random randomNum = new Random();

    private Cell[][] playerField;
    private Cell[][] computerField = new Cell[3][3];

    private Button[][] computerButtons;
    private Button[][] playerButtons;

    private Button one, two, three, four, five, six, seven, eight, nine,
            back,
            pcOne, pcTwo, pcThree, pcFour, pcFive, pcSix, pcSeven, pcEight, pcNine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fields);

        initializeButtons();

        makePcArr();
        makePlayerArr();

        buttonColourSetter();

        playerField = ApplicationContext.getContext().getPlayerField();

        playerShipMarker();

        placeFirstShip();
        placeSecondShip();

        pcFieldOnClicker();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToMain();
            }
        });
    }

    private void pcFieldOnClicker(){
        pcOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shootAtComputer(0,0)){
                    pcOne.setBackgroundColor(Color.RED);
                }
            }
        });

        pcTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shootAtComputer(0,1)){
                    pcTwo.setBackgroundColor(Color.RED);
                }
            }
        });

        pcThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shootAtComputer(0,2)){
                    pcThree.setBackgroundColor(Color.RED);
                }
            }
        });

        pcFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shootAtComputer(1,0)){
                    pcFour.setBackgroundColor(Color.RED);
                }
            }
        });

        pcFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shootAtComputer(1,1)){
                    pcFive.setBackgroundColor(Color.RED);
                }
            }
        });

        pcSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shootAtComputer(1,2)){
                    pcSix.setBackgroundColor(Color.RED);
                }
            }
        });

        pcSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shootAtComputer(2,0)){
                    pcSeven.setBackgroundColor(Color.RED);
                }
            }
        });

        pcEight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shootAtComputer(2,1)){
                    pcEight.setBackgroundColor(Color.RED);
                }
            }
        });

        pcNine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shootAtComputer(2,2)){
                    pcNine.setBackgroundColor(Color.RED);
                }
            }
        });
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
        if (!computerField[x][y].hasShip) {
            computerField[x][y].hasShip = true;
        }
        else{
            return placeSecondShip();
        }
        if (x + 1 <= 2 && !computerField[x+1][y].hasShip){
            computerField[x+1][y].hasShip = true;
        }
        else if (x - 1 >= 0 && !computerField[x-1][y].hasShip){
            computerField[x-1][y].hasShip = true;
        }
        else if (y + 1 <= 2 && !computerField[x][y+1].hasShip){
            computerField[x][y+1].hasShip = true;
        }
        else if (y - 1 >= 0 && !computerField[x][y-1].hasShip){
            computerField[x][y-1].hasShip = true;
        }
        return false;
    }

    private void shootAtPlayer(){
        int x = randomNum.nextInt(3);
        int y = randomNum.nextInt(3);
        if (playerField[x][y].isHit){
            shootAtPlayer();
        }
        if (playerField[x][y].hasShip) {
            computerButtons[x][y].setBackgroundColor(Color.RED);
        }
    }

    private boolean shootAtComputer(int x, int y){
        computerField[x][y].isHit = true;
        if(computerField[x][y].hasShip){
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

}


