package com.example.battleships_0;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.battleships_0.context.ApplicationContext;

import java.io.FileOutputStream;
import java.io.IOException;

public class AdminEditor extends AppCompatActivity {

    ApplicationContext context;

    DatabaseHelper db;

    private static final String FILE_NAME = "question.txt";

    String question, answerOne, answerTwo, answerThree, correctAnswer, category;

    EditText questionInput, answerOneInput, answerTwoInput, answerThreeInput, correctAnswerInput,
            categoryInput;

    Button submit, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_editor);
        //Get the current context
        this.context = ApplicationContext.getContext();

        db = new DatabaseHelper(this);


        questionInput = findViewById(R.id.enter_question);
        questionInput.setText(context.getQuestion());
//        answerOneInput = findViewById(R.id.enter_answer_1);
//        answerTwoInput = findViewById(R.id.enter_answer_2);
//        answerThreeInput = findViewById(R.id.enter_answer_3);

        submit = findViewById(R.id.submit_form);
        back = findViewById(R.id.back_admin_editor);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                question = questionInput.getText().toString();
                answerOne = answerOneInput.getText().toString();
                answerTwo = answerTwoInput.getText().toString();
                answerThree = answerThreeInput.getText().toString();
                openQuestions();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openQuestions();
            }
        });
        AddData();
    }

    public void AddData() {
        submit.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        long isInserted = db.insertNote(question);
                        if (isInserted > 0) {
                            Toast.makeText(AdminEditor.this, "Data Inserted", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(AdminEditor.this, "Data not Inserted", Toast.LENGTH_LONG).show();
                        }
                    }

                }
        );
    }

    public void openQuestions() {
        Intent intent = new Intent(this, Questions.class);
        startActivity(intent);
    }
}
