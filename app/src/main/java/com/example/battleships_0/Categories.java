package com.example.battleships_0;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Categories extends AppCompatActivity {

    Button dev, devops, qa, ba, delivery, back;

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

        dev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Do stuff
                goToPlayerField();

            }
        });

        devops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Do stuff
                goToPlayerField();
            }
        });

        qa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Do stuff
                goToPlayerField();
            }
        });

        ba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Do stuff
                goToPlayerField();
            }
        });

        delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Do stuff
                goToPlayerField();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToFront();
            }
        });
    }

    public void goToPlayerField(){
        Intent intent = new Intent(this, PlayerField.class);
        startActivity(intent);
    }

    public void goToFront(){
        Intent intent = new Intent(this, Play.class);
        startActivity(intent);
    }
}
