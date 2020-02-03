package com.example.battleships_0;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.battleships_0.context.ApplicationContext;
import com.example.battleships_0.pojos.Question;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ask extends AppCompatActivity {

    private  String fileName = ".txt";

    Button answerOne, answerTwo, answerThree;

    TextView question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ask);

        String category = ApplicationContext.getContext().getCategory();
        int questionID = ApplicationContext.getContext().getQuestion();

        fileName = category + questionID+fileName;

        question = findViewById(R.id.ask_question);

        answerOne = findViewById(R.id.ask_answer_1);
        answerTwo = findViewById(R.id.ask_answer_2);
        answerThree = findViewById(R.id.ask_answer_3);

        answerOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Do stuff
                goToPlayerField();

            }
        });

        answerTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Do stuff
                goToPlayerField();

            }
        });

        answerThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Do stuff
                goToPlayerField();

            }
        });

        load();
    }

    public void load(){
        FileInputStream fis = null;
        try {
            fis = openFileInput(fileName);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;

            while((text = br.readLine()) != null){
                sb.append(text).append("\n");
            }

            Question quObj = jsonToObject(sb.toString());

            question.setText(quObj.getQuestion());
            System.out.println(quObj.getQuestion());
            answerOne.setText(quObj.getAnswers().get(0));
            answerTwo.setText(quObj.getAnswers().get(1));
            answerThree.setText(quObj.getAnswers().get(2));
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private Question jsonToObject(String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, Question.class);
    }

    public void goToPlayerField(){
        Intent intent = new Intent(this, Fields.class);
        startActivity(intent);
    }
}
