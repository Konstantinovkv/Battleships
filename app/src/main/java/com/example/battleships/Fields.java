package com.example.battleships;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class Fields extends AppCompatActivity {

    private Random randomNum = new Random();

    static ApplicationContext context = ApplicationContext.getContext();

    private Drawable submar, destro, sea, dedSub, dedBat, dedSea;

    private Button one, two, three, four, five, six, seven, eight, nine,
            back, next,
            pcOne, pcTwo, pcThree, pcFour, pcFive, pcSix, pcSeven, pcEight, pcNine;

    private TextView bullets;

    @Override
    public void onBackPressed(){
        backToMain();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fields);

        ImageView rocket = findViewById(R.id.rockett);
        submar = getResources().getDrawable(R.drawable.submar);
        destro = getResources().getDrawable(R.drawable.bat_ship);
        sea = getResources().getDrawable(R.drawable.sea);
        dedSub = getResources().getDrawable(R.drawable.ded_sub);
        dedBat = getResources().getDrawable(R.drawable.ded_ship);
        dedSea = getResources().getDrawable(R.drawable.miss);
        rocket.setImageResource(R.drawable.rockettt);

        initializeButtons();
        bullets = findViewById(R.id.number_bullets);
        bullets.setText(context.numberOfBullets+"");
        next.setBackgroundResource(R.drawable.android_button_pur);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (context.numberOfBullets > 0){
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

        if (context.gameHasStarted){
            repopulateField();
            if (context.numberOfTurns > 2){
                placeSecondShip();
            }
            if (context.numberOfBullets == 0){
                shootAtPlayer();
            }
            if (context.gameHasFinished){
                Intent intent = new Intent(this, WinLose.class);
                startActivity(intent);
                endGameClear();
                return;
            }
            return;
        }

        makePcArr();
        makePlayerArr();

        pcFieldOnClicker();

        buttonColourSetter();

        playerShipMarker();

        placeFirstShip();
        context.gameHasStarted = true;
        if (context.numberOfBullets == 0){
            shootAtPlayer();
        }
    }

    private void repopulateField(){
        makePcArr();
        makePlayerArr();
        buttonColourSetter();
        pcFieldOnClicker();

        for (int i = 0; i < context.playerField.length; i++) {
            for (int j = 0; j < context.playerField[i].length; j++) {
                if (context.playerField[i][j].hasShip){
                    if (context.playerField[i][j].isSub){
                        subButton(i,j);
                    }
                    else{
                        destroyerButton(i,j);
                    }
                }
                if (context.playerField[i][j].isHit && context.playerField[i][j].hasShip){
                    if (context.playerField[i][j].isSub){
                        dedSubPlayerButtons(i,j);
                    }
                    else{
                        dedDestPlayerButton(i,j);
                    }
                }
                if (context.playerField[i][j].isHit && !context.playerField[i][j].hasShip){
                    dedSeaPlayerButtons(i,j);
                }
                if (context.computerField[i][j].isHit && context.computerField[i][j].hasShip){
                    if (context.computerField[i][j].isSub){
                        dedSubCompButtons(i,j);
                    }
                    else{
                        dedDestCompButtons(i,j);
                    }
                }
                if (context.computerField[i][j].isHit && !context.computerField[i][j].hasShip){
                    dedSeaComputerButtons(i,j);
                }
            }

        }
    }

    private void pcFieldOnClicker(){
        pcOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickMethods(0,0);
            }
        });
        pcTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickMethods(0,1);
            }
        });
        pcThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickMethods(0,2);
            }
        });
        pcFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickMethods(1,0);
            }
        });
        pcFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickMethods(1,1);
            }
        });
        pcSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickMethods(1,2);
            }
        });
        pcSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickMethods(2,0);
            }
        });
        pcEight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickMethods(2,1);
            }
        });
        pcNine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickMethods(2,2);
            }
        });
    }

    private void clickMethods(int x, int y){
        if (context.numberOfBullets == 0 ){
            makeToast("You need to answer questions correctly for bullets.");
            return;
        }
        if(checkIfHit(x,y) || context.gameHasFinished){
            return;
        }
        if (shootAtComputer(x,y)){
            if (context.computerField[x][y].isSub){
                dedSubCompButtons(x,y);
            }
            else {
                dedDestCompButtons(x,y);
            }
            if (context.numberOfTurns == 4 && context.gameHasFinished){
                context.bonus = true;
                context.numberOfTurns = 5;
                goToAsk();
                return;
            }
        }
        else{
            dedSeaComputerButtons(x,y);
        }
        if (context.gameHasFinished){
            Intent intent = new Intent(this, WinLose.class);
            startActivity(intent);
            endGameClear();
            return;
        }
        if (context.numberOfBullets > 0){
            makeToast("You have "+ context.numberOfBullets +" more shots.");
            return;
        }
        shootAtPlayer();
        if (context.gameHasFinished){
            Intent intent = new Intent(this, WinLose.class);
            startActivity(intent);
            endGameClear();
        }
    }

    public static void endGameClear(){
        context.computerShipsHit = 0;
        context.playerShipsHit = 0;
        context.gameHasFinished = false;
        context.gameHasStarted = false;
        context.numberOfTurns = 1;
        context.question = 1;
        context.numberOfBullets = 0;
        context.bonus = false;
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
        for (int i = 0; i < context.playerField.length; i++) {
            for (int j = 0; j <context.playerField[i].length ; j++) {
                if (context.playerField[i][j].hasShip){
                    if (context.playerField[i][j].isSub){
                        subButton(i,j);
                    }
                    else{
                        destroyerButton(i,j);
                    }
                }
            }
        }
    }

    private void buttonColourSetter(){
        for (int i = 0; i < context.computerField.length; i++) {
            for (int j = 0; j < context.computerField[i].length; j++) {
                seaButton(i,j);
            }
        }
    }

    private void populateComputerField(){
        int counter = 1;
        for (int i = 0; i < context.computerField.length; i++) {
            for (int j = 0; j < context.computerField[i].length; j++) {
                context.computerField[i][j] = new Cell(counter++);
            }
        }
    }

    private void placeFirstShip(){
        populateComputerField();
        int x = randomNum.nextInt(3);
        int y = randomNum.nextInt(3);
        context.computerField[x][y].hasShip = true;
        context.computerField[x][y].isSub = true;
    }

    private boolean checkNeighbour (int positionX, int positionY) {
        if (positionX >= 2 || positionY >= 2 || positionX < 0 || positionY < 0) {
            return false;
        }
        if (!context.computerField[positionX][positionY].hasShip &&
            !context.computerField[positionX][positionY].isHit) {
            context.computerField[positionX][positionY].hasShip = true;
            return true;
        }
        return false;
    }

    private boolean assignShip(int positionX, int positionY) {
        boolean hasFreeNeighbour =
            checkNeighbour(positionX + 1, positionY) ||
            checkNeighbour(positionX, positionY + 1) ||
            checkNeighbour(positionX - 1, positionY) ||
            checkNeighbour(positionX, positionY - 1);

        if (hasFreeNeighbour) {
            context.computerField[positionX][positionY].hasShip = true;
            return true;
        }
        return false;
    }

    private boolean placeSecondShip(){
        boolean hasPlacedSecondShip = false;

        for (int row = 0; row < 3 ; row++) {
            for (int col = 0; col < 3 && !hasPlacedSecondShip; col++) {
                if (!context.computerField[row][col].hasShip && !context.computerField[row][col].isHit) {
                    hasPlacedSecondShip = assignShip(row, col);
                }
            }
        }
        return hasPlacedSecondShip;
    }

    private void shootAtPlayer(){
        if (context.numberOfTurns < 3){
            for (int i = 0; i < context.playerField.length; i++) {
                for (int j = 0; j < context.playerField[i].length; j++) {
                    if (!context.playerField[i][j].hasShip && !context.playerField[i][j].isHit){
                        dedSeaPlayerButtons(i,j);
                        context.playerField[i][j].isHit=true;
                        context.numberOfTurns++;
                        return;
                    }
                }
            }
        }
        else{
            for (int i = 0; i < context.playerField.length; i++) {
                for (int j = 0; j < context.playerField[i].length; j++) {
                    if (context.playerField[i][j].hasShip && !context.playerField[i][j].isHit){
                        if (context.playerField[i][j].isSub){
                            dedSubPlayerButtons(i,j);
                        }
                        else {
                            dedDestPlayerButton(i,j);
                        }
                        context.playerShipsHit++;
                        context.playerField[i][j].isHit=true;
                        if (context.playerShipsHit == 3){
                            makeToast("You have lost the game!");
                            context.gameHasFinished = true;
                            context.isWinner = false;
                            context.numberOfTurns++;
                            return;
                        }
                        context.numberOfTurns++;
                        return;
                    }
                }
            }
        }
    }

    private boolean shootAtComputer(int x, int y){
        context.numberOfBullets--;
        bullets.setText(context.numberOfBullets+"");
        context.computerField[x][y].isHit = true;
        if(context.computerField[x][y].hasShip){
            context.computerShipsHit++;
            if (context.computerShipsHit == 3){
                makeToast("Congratulations!\n You have won the game!");
                context.gameHasFinished = true;
                context.isWinner = true;
                return true;
            }
            return true;
        }
        return false;
    }

    private void makePcArr(){
        context.computerButtons = new Button[][]{
                {pcOne, pcTwo, pcThree},
                {pcFour, pcFive, pcSix},
                {pcSeven, pcEight, pcNine}
        };
    }

    private void makePlayerArr(){
        context.playerButtons = new Button[][]{
                {one, two, three},
                {four, five, six},
                {seven, eight, nine}
        };
    }

    private boolean checkIfHit(int x, int y){
        if (context.computerField[x][y].isHit){
            makeToast("You cannot hit the same field twice.");
            return true;
        }
        return false;
    }

    private void backToMain(){
        Intent intent = new Intent(this, Play.class);
        startActivity(intent);
    }

    private void goToAsk(){
        Intent intent = new Intent(this, Ask.class);
        startActivity(intent);
    }

    private void makeToast(String text){
        Toast.makeText(getApplicationContext(),text, Toast.LENGTH_SHORT).show();
    }

    private void seaButton(int x,int y){
        context.playerButtons[x][y].setBackgroundDrawable(sea);
        context.computerButtons[x][y].setBackgroundDrawable(sea);
    }

    private void destroyerButton(int x, int y){
        context.playerButtons[x][y].setBackgroundDrawable(destro);
    }

    private void subButton(int x, int y){
        context.playerButtons[x][y].setBackgroundDrawable(submar);
    }

    private void dedSeaComputerButtons(int x, int y){
        context.computerButtons[x][y].setBackgroundDrawable(dedSea);
    }

    private void dedSeaPlayerButtons(int x, int y){
        context.playerButtons[x][y].setBackgroundDrawable(dedSea);
    }

    private void dedSubPlayerButtons(int x, int y){
        context.playerButtons[x][y].setBackgroundDrawable(dedSub);
    }

    private void dedDestPlayerButton(int x, int y){
        context.playerButtons[x][y].setBackgroundDrawable(dedBat);
    }

    private void dedSubCompButtons(int x, int y){
        context.computerButtons[x][y].setBackgroundDrawable(dedSub);
    }

    private void dedDestCompButtons(int x, int y){
        context.computerButtons[x][y].setBackgroundDrawable(dedBat);
    }
}


