package com.example.battleships;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlayerField extends AppCompatActivity {

    private Random randomNum = new Random();

    private Button one, two, three, four, five, six, seven, eight, nine, back, random, sub, destroyer, submit;

    private Drawable submar, destro, sea, potential, glow;

    private boolean randomized;

    ApplicationContext context = ApplicationContext.getContext();

    private boolean hasSub;
    private boolean hasDestOne;
    private boolean hasDestTwo;

    private boolean subClicked;
    private boolean destClicked;

    private TextView legend;

    private List<Point> possibleButtons = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_field);

        legend = findViewById(R.id.legend_pf);

        legend.setText("Place 1 sub and 2 destroyers on the field or click RANDOM. Destroyers must be next to each other.");

        context.finalScore = 0;

        submar = getResources().getDrawable(R.drawable.submar);
        destro = getResources().getDrawable(R.drawable.bat_ship);
        sea = getResources().getDrawable(R.drawable.sea);
        potential = getResources().getDrawable(R.drawable.potental);
        glow = getResources().getDrawable(R.drawable.new_purple_button);


        context = ApplicationContext.getContext();

        initializeField();

        initializeButtons();

        makePlayerArr();

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
                    goToAsk();
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
                if (hasSub && !randomized){
                    makeToast("You cannot place any more subs.");
                    return;
                }
                if (subClicked && !randomized){
                    return;
                }
                legend.setText("Click on a square to place your sub.");
                subClicked = true;
                destClicked = false;
                checkRandomized();
                if (!hasSub){
                    sub.setBackgroundDrawable(glow);
                    destroyer.setBackgroundResource(R.drawable.android_button_pur);
                }
            }
        });
        destroyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hasDestOne && hasDestTwo && !randomized){
                    makeToast("You cannot place any more destroyers.");
                    return;
                }
                if (destClicked && !randomized){
                    return;
                }
                legend.setText("Click on a square to place your destroyers.");
                destClicked = true;
                subClicked = false;
                checkRandomized();
                if (!hasDestTwo){
                    destroyer.setBackgroundDrawable(glow);
                    sub.setBackgroundResource(R.drawable.android_button_pur);
                }
            }
        });
    }

    private void checkRandomized(){
        if (randomized){
            buttonColourSetter();
            possibleButtons.clear();
            for (int i = 0; i < context.playerField.length; i++) {
                for (int j = 0; j < context.playerField[i].length; j++) {
                    context.playerField[i][j].hasShip = false;
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
                fieldClickMethods(0,0);
            }
        });
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fieldClickMethods(0,1);
            }
        });
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fieldClickMethods(0,2);
            }
        });
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fieldClickMethods(1,0);
            }
        });
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fieldClickMethods(1,1);
            }
        });
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fieldClickMethods(1,2);
            }
        });
        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fieldClickMethods(2,0);
            }
        });
        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fieldClickMethods(2,1);
            }
        });
        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fieldClickMethods(2,2);
            }
        });
    }

    private void fieldClickMethods(int x, int y){
        placeSub(x,y);
        placeDestroyer(x,y);
    }

    private void backToMain(){
        Intent intent = new Intent(this, Play.class);
        startActivity(intent);
    }

    private void goToAsk(){
        Intent intent = new Intent(this, Ask.class);
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
        random.setBackgroundResource(R.drawable.android_button_pur);

        submit = findViewById(R.id.submit_ships);
        submit.setBackgroundResource(R.drawable.android_button_pur);

        sub = findViewById(R.id.sub);
        sub.setBackgroundResource(R.drawable.android_button_pur);

        destroyer = findViewById(R.id.destroyer);
        destroyer.setBackgroundResource(R.drawable.android_button_pur);
    }

    private void initializeField(){
        int counter = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                context.playerField[i][j] = new Cell(counter++);
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
            context.playerField[x][y].hasShip = true;
            context.playerField[x][y].isSub = true;
            hasSub = true;
            if (!hasDestOne && !hasDestTwo){
                legend.setText("You have placed a sub, now place your destroyers.");
            }
            if(hasDestOne && !hasDestTwo){
                legend.setText("You have placed a sub, now place your second destroyer on one of the highlighted squares.");
            }
            if(hasDestOne && hasDestTwo){
                legend.setText("You have placed all of your ships. Click SUBMIT to play.");
            }
            subButton(x,y);
            sub.setBackgroundResource(R.drawable.android_button_pur);
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
            context.playerField[x][y].hasShip = true;
            hasDestOne = true;
            positionChecker(x,y);
            coloring(224, 241, 242);
            legend.setText("You have placed a destroyer, now place your second destroyer on one of the highlighted squares.");
            destroyerButton(x,y);
            return true;
        }
        else if (destClicked && hasDestOne && !hasDestTwo){
            if(!checkPositionValidity(x,y)){
                return false;
            }
            destPossibilitiesRevert();
            context.playerField[x][y].hasShip = true;
            hasDestTwo = true;
            if(hasSub && hasDestOne){
                legend.setText("You have placed all of your ships. Click SUBMIT to play.");
            }
            if(!hasSub && hasDestOne){
                legend.setText("You have placed both destroyers. Now place your sub");
            }
            destroyerButton(x,y);
            destroyer.setBackgroundResource(R.drawable.android_button_pur);
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
        if (context.playerField[x][y].hasShip){
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
            if (!context.playerField[x+1][y].hasShip) {
                possibleButtons.add(new Point(x + 1, y));
            }
        }
        if (x-1>=0) {
            if (!context.playerField[x-1][y].hasShip) {
                possibleButtons.add(new Point(x - 1, y));
            }
        }
        if (y+1<=2) {
            if (!context.playerField[x][y+1].hasShip) {
                possibleButtons.add(new Point(x, y + 1));
            }
        }
        if (y-1>=0) {
            if (!context.playerField[x][y-1].hasShip) {
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
            if (context.playerField[x][y].hasShip){
                continue;
            }
            seaButton(x,y);
        }
    }

    private void buttonColourSetter(){
        for (int i = 0; i < context.playerButtons.length; i++) {
            for (int j = 0; j < context.playerButtons[i].length; j++) {
                seaButton(i,j);
            }
        }
    }

    private void randomize(){
        destroyer.setBackgroundResource(R.drawable.android_button_pur);
        sub.setBackgroundResource(R.drawable.android_button_pur);
        randomized = true;
        legend.setText("You have placed your ships at random. Click SUBMIT to play.");
        buttonColourSetter();
        possibleButtons.clear();
        for (int i = 0; i < context.playerField.length; i++) {
            for (int j = 0; j < context.playerField[i].length; j++) {
                context.playerField[i][j].hasShip = false;
            }
        }
        int x = randomNum.nextInt(3);
        int y = randomNum.nextInt(3);
        context.playerField[x][y].hasShip = true;
        context.playerField[x][y].isSub = true;
        subButton(x,y);
        possibleButtons.add(new Point(x,y));
        placeSecondShip();
    }

    private boolean placeSecondShip(){
        int x = randomNum.nextInt(3);
        int y = randomNum.nextInt(3);
        if (!context.playerField[x][y].hasShip) {
            context.playerField[x][y].hasShip = true;
            destroyerButton(x,y);
            possibleButtons.add(new Point(x,y));
        }
        else{
            return placeSecondShip();
        }
        if (x + 1 <= 2 && !context.playerField[x+1][y].hasShip){
            context.playerField[x+1][y].hasShip = true;
            destroyerButton(x+1,y);
            possibleButtons.add(new Point(x+1,y));
        }
        else if (x - 1 >= 0 && !context.playerField[x-1][y].hasShip){
            context.playerField[x-1][y].hasShip = true;
            destroyerButton(x-1,y);
            possibleButtons.add(new Point(x-1,y));
        }
        else if (y + 1 <= 2 && !context.playerField[x][y+1].hasShip){
            context.playerField[x][y+1].hasShip = true;
            destroyerButton(x,y+1);
            possibleButtons.add(new Point(x,y+1));
        }
        else if (y - 1 >= 0 && !context.playerField[x][y-1].hasShip){
            context.playerField[x][y-1].hasShip = true;
            destroyerButton(x,y-1);
            possibleButtons.add(new Point(x,y-1));
        }
        //coloring(98,34,121);
        hasDestOne = true;
        hasDestTwo = false;
        return false;
    }

    private void coloring(int r, int g, int b){
        int x;
        int y;
        for (int i = 0; i < possibleButtons.size(); i++) {
            x = possibleButtons.get(i).x;
            y = possibleButtons.get(i).y;
            potentButton(x,y);
        }
    }

    private void makePlayerArr(){
        context.playerButtons = new Button[][]{
                {one, two, three},
                {four, five, six},
                {seven, eight, nine}
        };
    }

    private void seaButton(int x, int y){
        context.playerButtons[x][y].setBackgroundDrawable(sea);
    }

    private void destroyerButton(int x, int y){
        context.playerButtons[x][y].setBackgroundDrawable(destro);
    }

    private void subButton(int x, int y){
        context.playerButtons[x][y].setBackgroundDrawable(submar);
    }

    private void potentButton(int x, int y){
        context.playerButtons[x][y].setBackgroundDrawable(potential);
    }
}
