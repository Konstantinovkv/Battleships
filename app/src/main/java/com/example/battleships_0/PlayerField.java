package com.example.battleships_0;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.example.battleships_0.context.ApplicationContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlayerField extends AppCompatActivity {

    private Random randomNum = new Random();

    private Button one, two, three, four, five, six, seven, eight, nine, back, random, sub, destroyer, submit;

    private Cell[][] field = new Cell[3][3];

    private boolean randomized;

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

        makeToast("Choose a ship and place it on the field.");

        initializeField();

        initializeButtons();

        buttonColourSetter();

        fieldClicker();

        shipClicker();

        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                backToMain();
            }
        });

        random.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                randomize();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hasSub == true && hasDestOne == true && hasDestTwo == true || randomized == true) {
                    goToFields();
                }
                else{
                    makeToast("You cannot submit without ships.");
                }
            }
        });
    }

    private void shipClicker(){
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subClicked = true;
                destClicked = false;
                checkRandomized();
            }
        });

        destroyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                destClicked = true;
                subClicked = false;
                checkRandomized();
            }
        });
    }

    private void checkRandomized(){
        if (randomized){
            buttonColourSetter();
            possibleButtons.clear();
            for (int i = 0; i < field.length; i++) {
                for (int j = 0; j < field[i].length; j++) {
                    field[i][j].hasShip = false;
                }
            }
            hasSub = false;
            hasDestOne = false;
            hasDestTwo = false;
            randomized = false;
        }
    }

    private void fieldClicker(){
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (placeSub(0,0) || placeDestroyer(0,0)){
                    one.setBackgroundColor(Color.rgb(98, 34, 121));
                }
            }
        });

        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (placeSub(0,1) || placeDestroyer(0,1)){
                    two.setBackgroundColor(Color.rgb(98, 34, 121));
                }
            }
        });

        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (placeSub(0,2) || placeDestroyer(0,2)){
                    three.setBackgroundColor(Color.rgb(98, 34, 121));
                }
            }
        });

        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (placeSub(1,0) || placeDestroyer(1,0)){
                    four.setBackgroundColor(Color.rgb(98, 34, 121));
                }
            }
        });

        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (placeSub(1,1) || placeDestroyer(1,1)){
                    five.setBackgroundColor(Color.rgb(98, 34, 121));
                }
            }
        });

        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (placeSub(1,2) || placeDestroyer(1,2)){
                    six.setBackgroundColor(Color.rgb(98, 34, 121));
                }
            }
        });

        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (placeSub(2,0) || placeDestroyer(2,0)){
                    seven.setBackgroundColor(Color.rgb(98, 34, 121));
                }
            }
        });

        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (placeSub(2,1) || placeDestroyer(2,1)){
                    eight.setBackgroundColor(Color.rgb(98, 34, 121));
                }
            }
        });

        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (placeSub(2,2) || placeDestroyer(2,2)){
                    nine.setBackgroundColor(Color.rgb(98, 34, 121));
                }
            }
        });
    }

    private void backToMain(){
        Intent intent = new Intent(this, Categories.class);
        startActivity(intent);
    }

    private void goToFields(){
        Intent intent = new Intent(this, Fields.class);
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

        submit = findViewById(R.id.submit_ships);

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
            ApplicationContext.getContext().setPlayerField(field);
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
            coloring(224, 241, 242);
            ApplicationContext.getContext().setPlayerField(field);
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
            ApplicationContext.getContext().setPlayerField(field);
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

    private void destPossibilitiesRevert(){
        int x;
        int y;

        for (int i = 0; i < possibleButtons.size(); i++) {
            x = possibleButtons.get(i).x;
            y = possibleButtons.get(i).y;
            switch (x){
                case 0: switch (y){
                    case 0: one.setBackgroundColor(Color.rgb(61,108,180));
                        break;
                    case 1: two.setBackgroundColor(Color.rgb(61,108,180));
                        break;
                    case 2: three.setBackgroundColor(Color.rgb(61,108,180));
                        break;
                }
                    break;
                case 1: switch (y){
                    case 0: four.setBackgroundColor(Color.rgb(61,108,180));
                        break;
                    case 1: five.setBackgroundColor(Color.rgb(61,108,180));
                        break;
                    case 2: six.setBackgroundColor(Color.rgb(61,108,180));
                        break;
                }
                    break;
                case 2: switch (y){
                    case 0: seven.setBackgroundColor(Color.rgb(61,108,180));
                        break;
                    case 1: eight.setBackgroundColor(Color.rgb(61,108,180));
                        break;
                    case 2: nine.setBackgroundColor(Color.rgb(61,108,180));
                        break;
                }
                    break;
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

    }

    private void randomize(){
        randomized = true;
        buttonColourSetter();
        possibleButtons.clear();
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                field[i][j].hasShip = false;
            }
        }
        int x = randomNum.nextInt(3);
        int y = randomNum.nextInt(3);
        field[x][y].hasShip = true;
        hasSub = true;
        ApplicationContext.getContext().setPlayerField(field);
        possibleButtons.add(new Point(x,y));
        placeSecondShip();
    }

    private boolean placeSecondShip(){
        int x = randomNum.nextInt(3);
        int y = randomNum.nextInt(3);
        if (!field[x][y].hasShip) {
            field[x][y].hasShip = true;
            possibleButtons.add(new Point(x,y));
        }
        else{
            return placeSecondShip();
        }
        if (x + 1 <= 2 && !field[x+1][y].hasShip){
            field[x+1][y].hasShip = true;
            possibleButtons.add(new Point(x+1,y));
        }
        else if (x - 1 >= 0 && !field[x-1][y].hasShip){
            field[x-1][y].hasShip = true;
            possibleButtons.add(new Point(x-1,y));
        }
        else if (y + 1 <= 2 && !field[x][y+1].hasShip){
            field[x][y+1].hasShip = true;
            possibleButtons.add(new Point(x,y+1));
        }
        else if (y - 1 >= 0 && !field[x][y-1].hasShip){
            field[x][y-1].hasShip = true;
            possibleButtons.add(new Point(x,y-1));
        }
        coloring(98,34,121);
        hasDestOne = true;
        hasDestTwo = false;
        ApplicationContext.getContext().setPlayerField(field);
        return false;
    }

    private void coloring(int r, int g, int b){
        int x;
        int y;

        for (int i = 0; i < possibleButtons.size(); i++) {
            x = possibleButtons.get(i).x;
            y = possibleButtons.get(i).y;
            switch (x){
                case 0: switch (y){
                    case 0: one.setBackgroundColor(Color.rgb(r, g, b));
                        break;
                    case 1: two.setBackgroundColor(Color.rgb(r, g, b));
                        break;
                    case 2: three.setBackgroundColor(Color.rgb(r, g, b));
                        break;
                }
                    break;
                case 1: switch (y){
                    case 0: four.setBackgroundColor(Color.rgb(r, g, b));
                        break;
                    case 1: five.setBackgroundColor(Color.rgb(r, g, b));
                        break;
                    case 2: six.setBackgroundColor(Color.rgb(r, g, b));
                        break;
                }
                    break;
                case 2: switch (y){
                    case 0: seven.setBackgroundColor(Color.rgb(r, g, b));
                        break;
                    case 1: eight.setBackgroundColor(Color.rgb(r, g, b));
                        break;
                    case 2: nine.setBackgroundColor(Color.rgb(r, g, b));
                        break;
                }
                    break;
            }
        }
    }
}
