package com.example.battleships_0;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;

public class WinLose extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win_lose);

        //Get the screen size
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        //Instantiate parameters for width and height
        int width = dm.widthPixels;
        int height = dm.heightPixels;

        //Recalculate width and height to a desired % of the screen
        getWindow().setLayout((int)(width *.8),(int) (height*.8));
    }
}
