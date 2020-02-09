package com.example.battleships;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import static com.example.battleships.Fields.endGameClear;

public class Categories extends AppCompatActivity {

    Button dev, devops, qa, ba, delivery, back;

    ApplicationContext context = ApplicationContext.getContext();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categories);

        dev = findViewById(R.id.category_1);
        devops = findViewById(R.id.category_2);
        qa = findViewById(R.id.category_3);
        ba = findViewById(R.id.category_4);
        delivery = findViewById(R.id.category_5);
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

        delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Do stuff
                context.category = "delivery";
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
        delivery.setBackgroundResource(R.drawable.buttonround);
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
