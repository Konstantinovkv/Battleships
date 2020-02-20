package com.example.battleships;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import static com.example.battleships.Fields.endGameClear;

public class Categories extends AppCompatActivity {

    Button dev, devops, qa, ba, logic, generalOne, generalTwo, back;

    ApplicationContext context = ApplicationContext.getContext();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categories);

        context.finalScore = 0;

        dev = findViewById(R.id.category_1);
        devops = findViewById(R.id.category_2);
        qa = findViewById(R.id.category_3);
        ba = findViewById(R.id.category_4);
        logic = findViewById(R.id.category_5);
        generalOne = findViewById(R.id.category_6);
        generalTwo = findViewById(R.id.category_7);
        back = findViewById(R.id.back_categories);

        buttonColorSetter();

        endGameClear();

        dev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Do stuff
                context.category = "dev";
                goToAsk();

            }
        });

        devops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Do stuff
                context.category = "devops";
                goToAsk();
            }
        });

        qa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Do stuff
                context.category = "qa";
                goToAsk();
            }
        });

        ba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Do stuff
                context.category = "ba";
                goToAsk();
            }
        });

        logic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Do stuff
                context.category = "logic";
                goToAsk();
            }
        });

        generalOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Do stuff
                context.category = "generalOne";
                goToAsk();
            }
        });

        generalTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Do stuff
                context.category = "generalTwo";
                goToAsk();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToFront();
            }
        });
    }

    public void buttonColorSetter(){
        dev.setBackgroundResource(R.drawable.buttonround);
        devops.setBackgroundResource(R.drawable.buttonround);
        qa.setBackgroundResource(R.drawable.buttonround);
        ba.setBackgroundResource(R.drawable.buttonround);
        logic.setBackgroundResource(R.drawable.buttonround);
        generalOne.setBackgroundResource(R.drawable.buttonround);
        generalTwo.setBackgroundResource(R.drawable.buttonround);
    }

    public void goToAsk(){
        Intent intent = new Intent(this, PlayerField.class);
        startActivity(intent);
    }

    public void goToFront(){
        Intent intent = new Intent(this, Play.class);
        startActivity(intent);
    }

    public void goToWinLose(){
        Intent intent = new Intent(this, WinLose.class);
        startActivity(intent);
    }
}
