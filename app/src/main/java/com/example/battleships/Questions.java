package com.example.battleships;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

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

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToMain();
            }
        });
        buttonColorSetter();
    }

    public void buttonColorSetter(){
        questionOne.setBackgroundColor(Color.rgb(61, 108, 180));
        questionOne.setBackgroundResource(R.drawable.buttonround);
        questionTwo.setBackgroundColor(Color.rgb(61, 108, 180));
        questionTwo.setBackgroundResource(R.drawable.buttonround);
        questionThree.setBackgroundColor(Color.rgb(61, 108, 180));
        questionThree.setBackgroundResource(R.drawable.buttonround);
        questionFour.setBackgroundColor(Color.rgb(61, 108, 180));
        questionFour.setBackgroundResource(R.drawable.buttonround);
        questionFive.setBackgroundColor(Color.rgb(61, 108, 180));
        questionFive.setBackgroundResource(R.drawable.buttonround);
    }

    private void ClickMethods(int question){
        ApplicationContext.getContext().setQuestion(question);
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
