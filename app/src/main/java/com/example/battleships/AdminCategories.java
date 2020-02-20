package com.example.battleships;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class AdminCategories extends AppCompatActivity {

    Button dev, devops, qa, ba, logic, generalOne, generalTwo, back;

    static ApplicationContext context = ApplicationContext.getContext();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_categories);

        dev = findViewById(R.id.admin_category_1);
        devops = findViewById(R.id.admin_category_2);
        qa = findViewById(R.id.admin_category_3);
        ba = findViewById(R.id.admin_category_4);
        logic = findViewById(R.id.admin_category_5);
        generalOne = findViewById(R.id.admin_category_6);
        generalTwo = findViewById(R.id.admin_category_7);
        back = findViewById(R.id.back_admin_categories);

        dev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Do stuff
                context.category = "dev";
                goToQuestions();

            }
        });

        devops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Do stuff
                context.category = "devops";
                goToQuestions();
            }
        });

        qa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Do stuff
                context.category = "qa";
                goToQuestions();
            }
        });

        ba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Do stuff
                context.category = "ba";
                goToQuestions();
            }
        });

        logic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Do stuff
                context.category = "logic";
                goToQuestions();
            }
        });

        generalOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Do stuff
                context.category = "generalOne";
                goToQuestions();
            }
        });

        generalTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Do stuff
                context.category = "generalTwo";
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
        dev.setBackgroundResource(R.drawable.buttonround);
        devops.setBackgroundResource(R.drawable.buttonround);
        qa.setBackgroundResource(R.drawable.buttonround);
        ba.setBackgroundResource(R.drawable.buttonround);
        logic.setBackgroundResource(R.drawable.buttonround);
        generalOne.setBackgroundResource(R.drawable.buttonround);
        generalTwo.setBackgroundResource(R.drawable.buttonround);
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
