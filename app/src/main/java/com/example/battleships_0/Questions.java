package com.example.battleships_0;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.battleships_0.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class Questions extends AppCompatActivity {

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
                ApplicationContext.getContext().setQuestion(1);
                goToAdminEditor();
            }
        });

        questionTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Do stuff
                ApplicationContext.getContext().setQuestion(2);
                goToAdminEditor();
            }
        });

        questionThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Do stuff
                ApplicationContext.getContext().setQuestion(3);
                goToAdminEditor();
            }
        });

        questionFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Do stuff
                ApplicationContext.getContext().setQuestion(4);
                goToAdminEditor();
            }
        });

        questionFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Do stuff
                ApplicationContext.getContext().setQuestion(5);
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
        Intent intent = new Intent(this, AdminCategories.class);
        startActivity(intent);
    }

    public void goToAdminEditor(){
        Intent intent = new Intent(this, AdminEditor.class);
        startActivity(intent);
    }
}
