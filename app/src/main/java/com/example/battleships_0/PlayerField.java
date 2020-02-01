package com.example.battleships_0;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class PlayerField extends AppCompatActivity {

    private Button one, two, three, four, five, six, seven, eight, nine, back, random, sub, destroyer;

    private Cell[][] field = new Cell[3][3];

    private boolean hasSub;
    private boolean hasDestOne;
    private boolean hasDestTwo;

    private boolean subClicked;
    private boolean destClicked;

    private List<Point> possibleButtons = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_field);

        initializeField();

        initializeButtons();

        buttonColourSetter();

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
                    one.setBackgroundColor(Color.rgb(66, 104, 124));
                }
            }
        });

        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (placeSub(0,1) || placeDestroyer(0,1)){
                    two.setBackgroundColor(Color.rgb(66, 104, 124));
                }
            }
        });

        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (placeSub(0,2) || placeDestroyer(0,2)){
                    three.setBackgroundColor(Color.rgb(66, 104, 124));
                }
            }
        });

        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (placeSub(1,0) || placeDestroyer(1,0)){
                    four.setBackgroundColor(Color.rgb(66, 104, 124));
                }
            }
        });

        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (placeSub(1,1) || placeDestroyer(1,1)){
                    five.setBackgroundColor(Color.rgb(66, 104, 124));
                }
            }
        });

        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (placeSub(1,2) || placeDestroyer(1,2)){
                    six.setBackgroundColor(Color.rgb(66, 104, 124));
                }
            }
        });

        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (placeSub(2,0) || placeDestroyer(2,0)){
                    seven.setBackgroundColor(Color.rgb(66, 104, 124));
                }
            }
        });

        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (placeSub(2,1) || placeDestroyer(2,1)){
                    eight.setBackgroundColor(Color.rgb(66, 104, 124));
                }
            }
        });

        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (placeSub(2,2) || placeDestroyer(2,2)){
                    nine.setBackgroundColor(Color.rgb(66, 104, 124));
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
        else if (subClicked && !hasSub){
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
        else if (destClicked && !hasDestOne){
            field[x][y].hasShip = true;
            hasDestOne = true;
            positionChecker(x,y);
            destPossibilities();
            makeToast("Choose second coordinate from the highlight");
            return true;
        }
        else if (destClicked && hasDestOne && !hasDestTwo){
            if(!checkPositionValidity(x,y)){
                return false;
            }
            destPossibilitiesRevert();
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

    private boolean checkOverlap(int x, int y){
        if (field[x][y].hasShip){
            makeToast("Ships cannot overlap.");
            return true;
        }
        return false;
    }

    private boolean checkPositionValidity(int x, int y){
        for (int i = 0; i < possibleButtons.size(); i++) {
            if (possibleButtons.get(i).x == x && possibleButtons.get(i).y == y){
                return true;
            }
        }
        makeToast("Ships cannot be placed diagonally.");
        return false;
    }

    private void positionChecker(int x, int y){
        if (x+1<=2) {
            if (!field[x+1][y].hasShip) {
                possibleButtons.add(new Point(x + 1, y));
            }
        }
        if (x-1>=0) {
            if (!field[x-1][y].hasShip) {
                possibleButtons.add(new Point(x - 1, y));
            }
        }
        if (y+1<=2) {
            if (!field[x][y+1].hasShip) {
                possibleButtons.add(new Point(x, y + 1));
            }
        }
        if (y-1>=0) {
            if (!field[x][y-1].hasShip) {
                possibleButtons.add(new Point(x, y - 1));
            }
        }
    }

    private void destPossibilities(){
        int x;
        int y;

        for (int i = 0; i < possibleButtons.size(); i++) {
            x = possibleButtons.get(i).x;
            y = possibleButtons.get(i).y;
            switch (x){
                case 0: switch (y){
                    case 0: one.setBackgroundColor(Color.rgb(179, 218, 241));
                        break;
                    case 1: two.setBackgroundColor(Color.rgb(179, 218, 241));
                        break;
                    case 2: three.setBackgroundColor(Color.rgb(179, 218, 241));
                        break;
                }
                    break;
                case 1: switch (y){
                    case 0: four.setBackgroundColor(Color.rgb(179, 218, 241));
                        break;
                    case 1: five.setBackgroundColor(Color.rgb(179, 218, 241));
                        break;
                    case 2: six.setBackgroundColor(Color.rgb(179, 218, 241));
                        break;
                }
                    break;
                case 2: switch (y){
                    case 0: seven.setBackgroundColor(Color.rgb(179, 218, 241));
                        break;
                    case 1: eight.setBackgroundColor(Color.rgb(179, 218, 241));
                        break;
                    case 2: nine.setBackgroundColor(Color.rgb(179, 218, 241));
                        break;
                }
                    break;
            }
        }
    }

    private void destPossibilitiesRevert(){
        int x;
        int y;

        for (int i = 0; i < possibleButtons.size(); i++) {
            x = possibleButtons.get(i).x;
            y = possibleButtons.get(i).y;
            switch (x){
                case 0: switch (y){
                    case 0: one.setBackgroundColor(Color.rgb(51,171,249));
                        break;
                    case 1: two.setBackgroundColor(Color.rgb(51,171,249));
                        break;
                    case 2: three.setBackgroundColor(Color.rgb(51,171,249));
                        break;
                }
                    break;
                case 1: switch (y){
                    case 0: four.setBackgroundColor(Color.rgb(51,171,249));
                        break;
                    case 1: five.setBackgroundColor(Color.rgb(51,171,249));
                        break;
                    case 2: six.setBackgroundColor(Color.rgb(51,171,249));
                        break;
                }
                    break;
                case 2: switch (y){
                    case 0: seven.setBackgroundColor(Color.rgb(51,171,249));
                        break;
                    case 1: eight.setBackgroundColor(Color.rgb(51,171,249));
                        break;
                    case 2: nine.setBackgroundColor(Color.rgb(51,171,249));
                        break;
                }
                    break;
            }
        }
    }

    private void buttonColourSetter(){
        one.setBackgroundColor(Color.rgb(51,171,249));
        two.setBackgroundColor(Color.rgb(51,171,249));
        three.setBackgroundColor(Color.rgb(51,171,249));
        four.setBackgroundColor(Color.rgb(51,171,249));
        five.setBackgroundColor(Color.rgb(51,171,249));
        six.setBackgroundColor(Color.rgb(51,171,249));
        seven.setBackgroundColor(Color.rgb(51,171,249));
        eight.setBackgroundColor(Color.rgb(51,171,249));
        nine.setBackgroundColor(Color.rgb(51,171,249));

    }

}
