package com.example.battleships;

import android.content.Intent;
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

    ApplicationContext context = ApplicationContext.getContext();

    Button answerOne, answerTwo, answerThree;

    TextView question, bonus, bulletAward;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ask);

        fileName = context.category + context.numberOfTurns + fileName;

        question = findViewById(R.id.ask_question);

        answerOne = findViewById(R.id.ask_answer_1);
        answerTwo = findViewById(R.id.ask_answer_2);
        answerThree = findViewById(R.id.ask_answer_3);

        bonus = findViewById(R.id.bonus_question);
        bulletAward = findViewById(R.id.bullet_award);

        if (context.bonus){
            bonus.setText("bonus question");
        }

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
        answerOne.setBackgroundResource(R.drawable.buttonround);
        answerTwo.setBackgroundResource(R.drawable.buttonround);
        answerThree.setBackgroundResource(R.drawable.buttonround);
    }

    private void answerClicker(int correct){
        try {
            if (correctAnswer == correct) {
                if (context.numberOfTurns < 4) {
                    bulletAdder(1);
                } else {
                    bulletAdder(3);
                }

                switch(context.numberOfTurns) {
                    case 1:
                    case 2:
                        context.finalScore++;
                        break;
                    case 3:
                    case 4:
                        context.finalScore+=2;
                        break;
                    case 5:
                        context.finalScore+=3;
                        break;
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
        context.numberOfBullets+=x;
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
            answerOne.setText(quObj.getAnswers().get(0));
            answerTwo.setText(quObj.getAnswers().get(1));
            answerThree.setText(quObj.getAnswers().get(2));
            correctAnswer = quObj.getCorrectAnswer();

            if (context.numberOfTurns < 4) {
                bulletAward.setText("A question for one rocket.");
            } else {
                bulletAward.setText("A question for three rockets.");
            }

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
