package com.example.battleships;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AdminCategories extends AppCompatActivity {

    Button dev, devops, qa, ba, delivery, back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_categories);
        dev = findViewById(R.id.admin_category_1);
        devops = findViewById(R.id.admin_category_2);
        qa = findViewById(R.id.admin_category_3);
        ba = findViewById(R.id.admin_category_4);
        delivery = findViewById(R.id.admin_category_5);
        back = findViewById(R.id.back_admin_categories);

        dev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Do stuff
                ApplicationContext.getContext().setCategory("dev");
                goToQuestions();

            }
        });

        devops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Do stuff
                ApplicationContext.getContext().setCategory("devops");
                goToQuestions();
            }
        });

        qa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Do stuff
                ApplicationContext.getContext().setCategory("qa");
                goToQuestions();
            }
        });

        ba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Do stuff
                ApplicationContext.getContext().setCategory("ba");
                goToQuestions();
            }
        });

        delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Do stuff
                ApplicationContext.getContext().setCategory("delivery");
                goToQuestions();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToPlay();
            }
        });
        buttonColorSetter();
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

    public void goToQuestions(){
        Intent intent = new Intent(this, Questions.class);
        startActivity(intent);
    }

    public void backToPlay(){
        Intent intent = new Intent(this, Play.class);
        startActivity(intent);
    }
}
