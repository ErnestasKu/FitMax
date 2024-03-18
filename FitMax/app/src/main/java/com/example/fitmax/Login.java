package com.example.fitmax;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.fitmax.Database.AppActivity;
import com.example.fitmax.Database.AppDatabase;

public class Login extends AppCompatActivity {
    private AppDatabase db;

    private Button loginButton;
    private Button signupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = AppActivity.getDatabase();
        loginButton = findViewById(R.id.log_in);
        signupButton = findViewById(R.id.sing_up_link);

        db.userDAO().getAllUsers();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTabActivity();
            }
        });
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSingUp();
            }
        });
    }
    public void openTabActivity(){
        Intent intent = new Intent(this, TabScreen.class);
        startActivity(intent);
    }
    public void openSingUp(){
        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);
    }
}