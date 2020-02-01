package com.example.battleships_0;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class Questions extends AppCompatActivity {

    List<String> questionsDev = new ArrayList<>();
    List<String> questionsDevOps = new ArrayList<>();
    List<String> questionsQa = new ArrayList<>();
    List<String> questionsBa = new ArrayList<>();
    List<String> questionsHr = new ArrayList<>();

    Button questionOne, questionTwo, questionThree, questionFour, questionFive, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questions);

        questionOne = findViewById(R.id.question_1);
        questionTwo = findViewById(R.id.question_2);
        questionThree = findViewById(R.id.question_3);
        questionFour = findViewById(R.id.question_4);
        questionFive = findViewById(R.id.admin_category_5);
        back = findViewById(R.id.back_questions);

        questionOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Do stuff
                goToAdminEditor();
            }
        });

        questionTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Do stuff
                goToAdminEditor();
            }
        });

        questionThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Do stuff
                goToAdminEditor();
            }
        });

        questionFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Do stuff
                goToAdminEditor();
            }
        });

        questionFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Do stuff
                goToAdminEditor();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToMain();
            }
        });
    }

    public void backToMain(){
        Intent intent = new Intent(this, Categories.class);
        startActivity(intent);
    }

    public void goToAdminEditor(){
        Intent intent = new Intent(this, AdminEditor.class);
        startActivity(intent);
    }
}
