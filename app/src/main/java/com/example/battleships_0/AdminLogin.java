package com.example.battleships_0;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminLogin extends AppCompatActivity {

    Button login, back;
    EditText password;

    private String pass = "12345";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_login);

        password = findViewById(R.id.password);
        login = findViewById(R.id.login_button);
        back = findViewById(R.id.back_admin_login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!password.getText().toString().equals(pass)) {
                    Toast.makeText(getApplicationContext(),"Wrong password.", Toast.LENGTH_SHORT).show();
                }
                else {
                    goToAdminCategories();
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               backToMain();
            }
        });
    }

    public void goToAdminCategories(){
        Intent intent = new Intent(this, AdminCategories.class);
        startActivity(intent);
    }

    public void backToMain(){
        Intent intent = new Intent(this, Categories.class);
        startActivity(intent);
    }

}
