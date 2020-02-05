package com.example.battleships;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ask extends AppCompatActivity {

    private  String fileName = ".txt";
    private Integer correctAnswer;

    Button answerOne, answerTwo, answerThree;

    TextView question, bonus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ask);

        if (ApplicationContext.getContext().isBonus()){
            bonus.setText("bonus question");
        }

        fileName = ApplicationContext.getContext().getCategory() + ApplicationContext.getContext().getNumberOfTurns()+fileName;

        question = findViewById(R.id.ask_question);

        answerOne = findViewById(R.id.ask_answer_1);
        answerTwo = findViewById(R.id.ask_answer_2);
        answerThree = findViewById(R.id.ask_answer_3);

        bonus = findViewById(R.id.bonus_question);

        answerOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answerClicker(1);
            }
        });

        answerTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answerClicker(2);
            }
        });

        answerThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answerClicker(3);
            }
        });

        buttonColorSetter();

        load();
    }

    public void buttonColorSetter(){
        answerOne.setBackgroundColor(Color.rgb(61, 108, 180));
        answerOne.setBackgroundResource(R.drawable.buttonround);
        answerTwo.setBackgroundColor(Color.rgb(61, 108, 180));
        answerTwo.setBackgroundResource(R.drawable.buttonround);
        answerThree.setBackgroundColor(Color.rgb(61, 108, 180));
        answerThree.setBackgroundResource(R.drawable.buttonround);
    }

    private void answerClicker(int correct){
        try {
            if (correctAnswer == correct) {
                int finalScore = ApplicationContext.getContext().getFinalScore();
                if (ApplicationContext.getContext().getNumberOfTurns() < 4) {
                    bulletAdder(1);
                } else {
                    bulletAdder(3);
                }

                System.out.println("turns: " + ApplicationContext.getContext().getNumberOfTurns());
                switch(ApplicationContext.getContext().getNumberOfTurns()) {
                    case 1:
                    case 2:{
                        ApplicationContext.getContext().setFinalScore(finalScore + 1);
                        break;
                    }
                    case 3:
                    case 4: {
                        ApplicationContext.getContext().setFinalScore(finalScore + 2); break;
                    }
                    case 5: {
                        ApplicationContext.getContext().setFinalScore(finalScore + 3);
                        break;
                    }
                }
                // Make it glow green
                makeToast("Correct answer.");
            } else {
                //make it glow red
                makeToast("Wrong answer.");
            }
            goToFields();
        }
        catch (NullPointerException e){
            makeToast("There are no loaded questions, tell staff.");
            goToAdminLogin();
        }
    }

    private void bulletAdder(int x){
        ApplicationContext.getContext().setNumberOfBullets(ApplicationContext.getContext().getNumberOfBullets()+x);
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
            correctAnswer = quObj.getCorrectAnswer();

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

    public void goToFields(){
        Intent intent = new Intent(this, Fields.class);
        startActivity(intent);
    }

    public void goToAdminLogin(){
        Intent intent = new Intent(this, AdminLogin.class);
        startActivity(intent);
    }


    private void makeToast(String text){
        Toast.makeText(getApplicationContext(),text, Toast.LENGTH_SHORT).show();
    }
}
