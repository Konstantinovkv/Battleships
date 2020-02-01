package com.example.battleships_0;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminCategories extends AppCompatActivity {

    Button dev, devops, qa, ba, hr, back;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_categories);

        dev = findViewById(R.id.admin_category_1);
        devops = findViewById(R.id.admin_category_2);
        qa = findViewById(R.id.admin_category_3);
        ba = findViewById(R.id.admin_category_4);
        hr = findViewById(R.id.admin_category_5);
        back = findViewById(R.id.back_admin_categories);

        dev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Do stuff

                goToQuestions();

            }
        });

        devops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Do stuff

                goToQuestions();
            }
        });

        qa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Do stuff

                goToQuestions();
            }
        });

        ba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Do stuff

                goToQuestions();
            }
        });

        hr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Do stuff

                goToQuestions();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToMain();
            }
        });
    }

    public void goToQuestions(){
        Intent intent = new Intent(this, Questions.class);
        startActivity(intent);
    }

    public void backToMain(){
        Intent intent = new Intent(this, Categories.class);
        startActivity(intent);
    }
}
