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
    int bullets;
    int finalScore;
    private Random randomNum = new Random();

    private Cell[][] playerField;
    private Cell[][] computerField = new Cell[3][3];

    private Button one, two, three, four, five, six, seven, eight, nine,
            back,
            pcOne, pcTwo, pcThree, pcFour, pcFive, pcSix, pcSeven, pcEight, pcNine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fields);

        initializeButtons();

        buttonColourSetter();

        playerField = ApplicationContext.getContext().getPlayerField();

        playerShipMarker();

        placeComputerShips();

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
            }
        });

        pcTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        pcThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        pcFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        pcFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        pcSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        pcSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        pcEight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        pcNine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
        int counter = 1;
        for (int i = 0; i < playerField.length; i++) {
            for (int j = 0; j <playerField[i].length ; j++) {
                if (playerField[i][j].hasShip){
                    switch (counter){
                        case 1: one.setBackgroundColor(Color.rgb(98, 34, 121));
                            break;
                        case 2: two.setBackgroundColor(Color.rgb(98, 34, 121));
                            break;
                        case 3: three.setBackgroundColor(Color.rgb(98, 34, 121));
                            break;
                        case 4: four.setBackgroundColor(Color.rgb(98, 34, 121));
                            break;
                        case 5: five.setBackgroundColor(Color.rgb(98, 34, 121));
                            break;
                        case 6: six.setBackgroundColor(Color.rgb(98, 34, 121));
                            break;
                        case 7: seven.setBackgroundColor(Color.rgb(98, 34, 121));
                            break;
                        case 8: eight.setBackgroundColor(Color.rgb(98, 34, 121));
                            break;
                        case 9: nine.setBackgroundColor(Color.rgb(98, 34, 121));
                            break;
                    }
                }
                counter++;
            }
        }
    }

    private void buttonColourSetter(){
        one.setBackgroundColor(Color.rgb(61,108,180));
        two.setBackgroundColor(Color.rgb(61,108,180));
        three.setBackgroundColor(Color.rgb(61,108,180));
        four.setBackgroundColor(Color.rgb(61,108,180));
        five.setBackgroundColor(Color.rgb(61,108,180));
        six.setBackgroundColor(Color.rgb(61,108,180));
        seven.setBackgroundColor(Color.rgb(61,108,180));
        eight.setBackgroundColor(Color.rgb(61,108,180));
        nine.setBackgroundColor(Color.rgb(61,108,180));

        pcOne.setBackgroundColor(Color.rgb(61,108,180));
        pcTwo.setBackgroundColor(Color.rgb(61,108,180));
        pcThree.setBackgroundColor(Color.rgb(61,108,180));
        pcFour.setBackgroundColor(Color.rgb(61,108,180));
        pcFive.setBackgroundColor(Color.rgb(61,108,180));
        pcSix.setBackgroundColor(Color.rgb(61,108,180));
        pcSeven.setBackgroundColor(Color.rgb(61,108,180));
        pcEight.setBackgroundColor(Color.rgb(61,108,180));
        pcNine.setBackgroundColor(Color.rgb(61,108,180));
    }

    private void populateComputerField(){
        int counter = 1;
        for (int i = 0; i < computerField.length; i++) {
            for (int j = 0; j < computerField[i].length; j++) {
                computerField[i][j] = new Cell(counter++);
            }
        }
    }

    private void placeComputerShips(){
        populateComputerField();
        int x = randomNum.nextInt(3);
        int y = randomNum.nextInt(3);
        computerField[x][y].hasShip = true;
        placeSecondShip();
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

}


