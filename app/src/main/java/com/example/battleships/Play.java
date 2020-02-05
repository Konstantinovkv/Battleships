package com.example.battleships;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.battleships.Fields.endGameClear;

public class Play extends AppCompatActivity {

    Button play, login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play);

        endGameClear();

        play = findViewById(R.id.play);
        login = findViewById(R.id.login_front);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToCategories();

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToAdminLogin();

            }
        });
        login.setBackgroundResource(R.drawable.android_button_pur);
    }

    public void goToCategories(){
        Intent intent = new Intent(this, Categories.class);
        startActivity(intent);
    }

    public void goToAdminLogin(){
        Intent intent = new Intent(this, AdminLogin.class);
        startActivity(intent);
    }
}
