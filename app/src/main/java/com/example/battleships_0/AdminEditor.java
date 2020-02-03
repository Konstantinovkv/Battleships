package com.example.battleships_0;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.battleships_0.context.ApplicationContext;
import com.example.battleships_0.pojos.Question;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class AdminEditor extends AppCompatActivity {

    private String fileName = ".txt";

    private int questionID;

    private String question, answerOne, answerTwo, answerThree, category;

    private List<String> answers = new ArrayList<>();

    private Integer correctAnswer;

    private EditText questionInput, answerOneInput, answerTwoInput, answerThreeInput;

    private Button submit, back;

    private RadioGroup radioGroup;

    private RadioButton correct, correctOne, correctTwo, correctThree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_editor);

        category = ApplicationContext.getContext().getCategory();
        questionID = ApplicationContext.getContext().getQuestion();

        fileName = category+questionID+fileName;

        radioGroup = findViewById(R.id.radioGroup);

        correctOne = findViewById(R.id.c1);
        correctTwo = findViewById(R.id.c2);
        correctThree = findViewById(R.id.c3);

        questionInput = findViewById(R.id.enter_question);
        answerOneInput = findViewById(R.id.enter_answer_1);
        answerTwoInput = findViewById(R.id.enter_answer_2);
        answerThreeInput = findViewById(R.id.enter_answer_3);

        load();

        submit = findViewById(R.id.submit_form);
        back = findViewById(R.id.back_admin_editor);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkButton() == 0){
                    return;
                }
                correctAnswer = checkButton();
                question = questionInput.getText().toString();
                answerOne = answerOneInput.getText().toString();
                answerTwo = answerTwoInput.getText().toString();
                answerThree = answerThreeInput.getText().toString();

                packAnswers();

                try {
                    save();
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }


                openQuestions();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openQuestions();
            }
        });
    }

    public void save() throws JsonProcessingException {

        Question quObj = new Question(question,answers,correctAnswer,category);

        ObjectMapper objectMapper = new ObjectMapper();

        String jsonStr = objectMapper.writeValueAsString(quObj);

        String text = jsonStr;
        FileOutputStream fos = null;
        try {
            fos = openFileOutput(fileName, MODE_PRIVATE);
            fos.write(text.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (fos != null){
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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

            questionInput.setText(quObj.getQuestion());
            System.out.println(quObj.getQuestion());
            answerOneInput.setText(quObj.getAnswers().get(0));
            answerTwoInput.setText(quObj.getAnswers().get(1));
            answerThreeInput.setText(quObj.getAnswers().get(2));
            switch (quObj.getCorrectAnswer()){
                case 1: correctOne.setChecked(true);
                    break;
                case 2: correctTwo.setChecked(true);
                    break;
                case 3: correctThree.setChecked(true);
                    break;
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

    private void openQuestions() {
        Intent intent = new Intent(this, Questions.class);
        startActivity(intent);
    }

    private int checkButton(){
        char value = '1';
        try {
            int radioId = radioGroup.getCheckedRadioButtonId();
            correct = findViewById(radioId);
            value = correct.getText().charAt(8);
        }catch (NullPointerException e){
            makeToast("You must choose a correct answer.");
            return 0;
        }
        return Character.getNumericValue(value);
    }

    private void makeToast(String text){
        Toast.makeText(getApplicationContext(),text, Toast.LENGTH_SHORT).show();
    }

    private void packAnswers(){
        answers.add(answerOne);
        answers.add(answerTwo);
        answers.add(answerThree);
    }

    private Question jsonToObject(String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, Question.class);
    }
}
