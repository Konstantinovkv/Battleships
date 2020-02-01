package com.example.battleships_0;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class PlayerField extends AppCompatActivity {

    Button one, two, three, four, five, six, seven, eight, nine, back, random, sub, destroyer;

    Cell[][] field = new Cell[3][3];

    boolean hasSub;
    boolean hasDestOne;
    boolean hasDestTwo;

    boolean subClicked;
    boolean destclicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_field);

        initializeField();

        initializeButtons();

        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                placeSub(0,0);
                placeDestroyer(0,0);
            }
        });

        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                placeSub(0,1);
                placeDestroyer(0,1);
            }
        });

        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                placeSub(0,2);
                placeDestroyer(0,2);
            }
        });

        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                placeSub(1,0);
                placeDestroyer(1,0);
            }
        });

        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                placeSub(1,1);
                placeDestroyer(1,1);
            }
        });

        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                placeSub(1,2);
                placeDestroyer(1,2);
            }
        });

        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                placeSub(2,0);
                placeDestroyer(2,0);
            }
        });

        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                placeSub(2,1);
                placeDestroyer(2,1);
            }
        });

        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                placeSub(2,2);
                placeDestroyer(2,2);
            }
        });

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subClicked = true;
                destclicked = false;
            }
        });

        destroyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                destclicked = true;
                subClicked = false;
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToMain();
            }
        });
    }

    public void backToMain(){
        Intent intent = new Intent(this, Categories.class);
        startActivity(intent);
    }

    public void initializeButtons(){
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

    public void initializeField(){
        int counter = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = new Cell(counter++);
            }
        }
    }

    public void makeToast(String text){
        Toast.makeText(getApplicationContext(),text, Toast.LENGTH_SHORT).show();
    }

    public void placeSub(int x, int y){
        if (subClicked && !hasSub){
            field[x][y].hasShip = true;
            hasSub = true;
            makeToast("You have placed a sub at coordinates "+x+" : "+y+".");
        }
        else if(subClicked && hasSub){
            makeToast("You cannot have any more submarines.");
        }
        else if (!subClicked && !destclicked){
            makeToast("Choose a ship to place.");
        }
    }

    public void placeDestroyer(int x, int y){
        if (destclicked && !hasDestOne){
            field[x][y].hasShip = true;
            hasDestOne = true;
            makeToast("You have placed a destroyer at coordinates "+x+" : "+y+".");
        }
        else if (destclicked && hasDestOne && !hasDestTwo){
            field[x][y].hasShip = true;
            hasDestTwo = true;
            makeToast("You have placed a destroyer at coordinates "+x+" : "+y+".");
        }
        else if(destclicked && hasDestOne && hasDestTwo){
            makeToast("You cannot have any more destroyers.");
        }
        else if (!subClicked && !destclicked){
            makeToast("Choose a ship to place.");
        }
    }
}
