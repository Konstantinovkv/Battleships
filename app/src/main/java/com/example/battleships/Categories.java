package com.example.battleships;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.battleships.Fields.endGameClear;

public class Categories extends AppCompatActivity {

    Button dev, devops, qa, ba, delivery, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categories);

        dev = findViewById(R.id.category_1);
        devops = findViewById(R.id.category_2);
        qa = findViewById(R.id.category_3);
        ba = findViewById(R.id.category_4);
        delivery = findViewById(R.id.category_5);
        back = findViewById(R.id.back_categories);

        buttonColorSetter();

        endGameClear();

        dev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Do stuff
                ApplicationContext.getContext().setCategory("dev");
                goToAsk();

            }
        });

        devops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Do stuff
                ApplicationContext.getContext().setCategory("devops");
                goToAsk();
            }
        });

        qa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Do stuff
                ApplicationContext.getContext().setCategory("qa");
                goToAsk();
            }
        });

        ba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Do stuff
                ApplicationContext.getContext().setCategory("ba");
                goToAsk();
            }
        });

        delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Do stuff
                ApplicationContext.getContext().setCategory("delivery");
                goToAsk();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToFront();
            }
        });
        //REMOVE - for testing purposes
//        this.goToWinLose();
    }

    public void buttonColorSetter(){
        dev.setBackgroundColor(Color.rgb(61, 108, 180));
        dev.setBackgroundResource(R.drawable.buttonround);
        devops.setBackgroundColor(Color.rgb(61, 108, 180));
        devops.setBackgroundResource(R.drawable.buttonround);
        qa.setBackgroundColor(Color.rgb(61, 108, 180));
        qa.setBackgroundResource(R.drawable.buttonround);
        ba.setBackgroundColor(Color.rgb(61, 108, 180));
        ba.setBackgroundResource(R.drawable.buttonround);
        delivery.setBackgroundColor(Color.rgb(61, 108, 180));
        delivery.setBackgroundResource(R.drawable.buttonround);
    }

    public void goToAsk(){
        Intent intent = new Intent(this, PlayerField.class);
        startActivity(intent);
    }

    public void goToFront(){
        Intent intent = new Intent(this, Play.class);
        startActivity(intent);
    }

    public void goToWinLose(){
        Intent intent = new Intent(this, WinLose.class);
        startActivity(intent);
    }
}
