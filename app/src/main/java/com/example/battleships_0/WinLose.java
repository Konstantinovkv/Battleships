package com.example.battleships_0;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.battleships_0.context.ApplicationContext;

public class WinLose extends AppCompatActivity {

    private ApplicationContext context;
    private Button restart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win_lose);

        //Set the restart button of the game
        restart = findViewById(R.id.restart);

        //Set functionality to the button to return to first screen
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToPlayScreen();
            }
        });

        //Get the screen size
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        //Instantiate parameters for width and height
        int width = dm.widthPixels;
        int height = dm.heightPixels;

        //Recalculate width and height to a desired % of the screen
        getWindow().setLayout((int)(width *.8),(int) (height*.8));

        //Set the outcome of the game message
        TextView tv = (TextView) findViewById(R.id.outcome_message);
        String message = getOutcomeMessage();
        tv.setText(message);


    }

    //Get the outcome of the game message*
    //Note if game is not played and this activity appears
    //The message will be by default the loosing one:
    //"You loose! Your ships have been destroyed."
    //(Default boolean is always false)
    private String getOutcomeMessage(){
        context = ApplicationContext.getContext();
        return setOutcomeMessage(context.isWinner());
    }

    //Write winner or loser message
    private String setOutcomeMessage(boolean winner){
        return winner ?  "You win! You destroyed your opponents ships first!":
                "You loose! Your ships have been destroyed.";
    }

    private void goToPlayScreen(){
        Intent intent = new Intent(this, Play.class);
        startActivity(intent);
    }
}
