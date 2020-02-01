package com.example.battleships_0;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class PlayerField extends AppCompatActivity {

    private Button one, two, three, four, five, six, seven, eight, nine, back, random, sub, destroyer;

    private Cell[][] field = new Cell[3][3];

    private boolean hasSub;
    private boolean hasDestOne;
    private boolean hasDestTwo;

    private boolean subClicked;
    private boolean destClicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_field);

        initializeField();

        initializeButtons();

        fieldClicker();

        shipClicker();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToMain();
            }
        });
    }

    private void shipClicker(){
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subClicked = true;
                destClicked = false;
            }
        });

        destroyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                destClicked = true;
                subClicked = false;
            }
        });
    }

    private void fieldClicker(){
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (placeSub(0,0) || placeDestroyer(0,0)){
                    one.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                }
            }
        });

        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (placeSub(0,1) || placeDestroyer(0,1)){
                    two.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                }
            }
        });

        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (placeSub(0,2) || placeDestroyer(0,2)){
                    three.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                }
            }
        });

        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (placeSub(1,0) || placeDestroyer(1,0)){
                    four.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                }
            }
        });

        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (placeSub(1,1) || placeDestroyer(1,1)){
                    five.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                }
            }
        });

        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (placeSub(1,2) || placeDestroyer(1,2)){
                    six.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                }
            }
        });

        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (placeSub(2,0) || placeDestroyer(2,0)){
                    seven.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                }
            }
        });

        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (placeSub(2,1) || placeDestroyer(2,1)){
                    eight.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                }
            }
        });

        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (placeSub(2,2) || placeDestroyer(2,2)){
                    nine.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                }
            }
        });
    }

    private void backToMain(){
        Intent intent = new Intent(this, Categories.class);
        startActivity(intent);
    }

    private void initializeButtons(){
        one = findViewById(R.id.cell_p_1);
        two = findViewById(R.id.cell_p_2);
        three = findViewById(R.id.cell_p_3);
        four = findViewById(R.id.cell_p_4);
        five = findViewById(R.id.cell_p_5);
        six = findViewById(R.id.cell_p_6);
        seven = findViewById(R.id.cell_p_7);
        eight = findViewById(R.id.cell_p_8);
        nine = findViewById(R.id.cell_p_9);

        back = findViewById(R.id.back_player_field);

        random = findViewById(R.id.random);

        sub = findViewById(R.id.sub);
        destroyer = findViewById(R.id.destroyer);
    }

    private void initializeField(){
        int counter = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = new Cell(counter++);
            }
        }
    }

    private void makeToast(String text){
        Toast.makeText(getApplicationContext(),text, Toast.LENGTH_SHORT).show();
    }

    private boolean placeSub(int x, int y){
        if (checkOverlap(x,y)){
            return false;
        }
        if (subClicked && !hasSub){
            field[x][y].hasShip = true;
            hasSub = true;
            makeToast("You have placed a sub.");
            return true;
        }
        else if(subClicked && hasSub){
            makeToast("You cannot have any more submarines.");
            return false;
        }
        else if (!subClicked && !destClicked){
            makeToast("Choose a ship to place.");
            return false;
        }
        return false;
    }

    private boolean placeDestroyer(int x, int y){
        if (checkOverlap(x,y)){
            return false;
        }
        if (destClicked && !hasDestOne){
            field[x][y].hasShip = true;
            hasDestOne = true;
            makeToast("Choose next coordinate for your destroyer");
            return true;
        }
        else if (destClicked && hasDestOne && !hasDestTwo){
            field[x][y].hasShip = true;
            hasDestTwo = true;
            makeToast("You have placed a destroyer.");
            return true;
        }
        else if(destClicked && hasDestOne && hasDestTwo){
            makeToast("You cannot have any more destroyers.");
            return false;
        }
        else if (!subClicked && !destClicked){
            makeToast("Choose a ship to place.");
            return false;
        }
        return false;
    }

    public boolean checkOverlap(int x, int y){
        if (field[x][y].hasShip){
            makeToast("Ships cannot overlap.");
            return true;
        }
        return false;
    }


}
