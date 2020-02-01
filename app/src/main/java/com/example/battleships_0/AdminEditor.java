package com.example.battleships_0;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import java.io.FileOutputStream;
import java.io.IOException;

public class AdminEditor extends AppCompatActivity {

    private static final String FILE_NAME = "question.txt";

    String question, answerOne, answerTwo, answerThree;

    EditText questionInput, answerOneInput, answerTwoInput, answerThreeInput;

    Button submit, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_editor);

        questionInput = findViewById(R.id.enter_question);
        answerOneInput = findViewById(R.id.enter_answer_1);
        answerTwoInput = findViewById(R.id.enter_answer_2);
        answerThreeInput = findViewById(R.id.enter_answer_3);

        submit = findViewById(R.id.submit_form);
        back = findViewById(R.id.back_admin_editor);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                question = questionInput.getText().toString();
                answerOne = answerOneInput.getText().toString();
                answerTwo = answerTwoInput.getText().toString();
                answerThree = answerThreeInput.getText().toString();
                save();
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

    public void save(){
        String text = question;
        FileOutputStream fos = null;
        try {
            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
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

    public void openQuestions(){
        Intent intent = new Intent(this, Questions.class);
        startActivity(intent);
    }
}
