package com.example.battleships;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class Questions extends AppCompatActivity {

    Button questionOne, questionTwo, questionThree, questionFour, questionFive, questionSix, questionSeven, back;

    ApplicationContext context = ApplicationContext.getContext();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questions);

        questionOne = findViewById(R.id.question_1);
        questionTwo = findViewById(R.id.question_2);
        questionThree = findViewById(R.id.question_3);
        questionFour = findViewById(R.id.question_4);
        questionFive = findViewById(R.id.question_5);
        questionSix = findViewById(R.id.question_6);
        questionSeven = findViewById(R.id.question_7);
        back = findViewById(R.id.back_questions);

        questionOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Do stuff
                ClickMethods(1);
            }
        });

        questionTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Do stuff
                ClickMethods(2);
            }
        });

        questionThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Do stuff
                ClickMethods(3);
            }
        });

        questionFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Do stuff
                ClickMethods(4);
            }
        });

        questionFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Do stuff
                ClickMethods(5);
            }
        });

        questionSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Do stuff
                ClickMethods(6);
            }
        });

        questionSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Do stuff
                ClickMethods(7);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToMain();
            }
        });
        buttonColorSetter();
    }

    public void buttonColorSetter(){
        questionOne.setBackgroundResource(R.drawable.buttonround);
        questionTwo.setBackgroundResource(R.drawable.buttonround);
        questionThree.setBackgroundResource(R.drawable.buttonround);
        questionFour.setBackgroundResource(R.drawable.buttonround);
        questionFive.setBackgroundResource(R.drawable.buttonround);
        questionSix.setBackgroundResource(R.drawable.buttonround);
        questionSeven.setBackgroundResource(R.drawable.buttonround);
    }

    private void ClickMethods(int question){
        context.question = question;
        goToAdminEditor();
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
