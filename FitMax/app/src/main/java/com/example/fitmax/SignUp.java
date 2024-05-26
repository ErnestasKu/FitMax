package com.example.fitmax;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fitmax.Database.AppActivity;
import com.example.fitmax.Database.AppDatabase;
import com.example.fitmax.Database.User;
import com.example.fitmax.databinding.SignUpBinding;

import java.util.List;
import java.util.Objects;

public class SignUp extends AppCompatActivity {
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        SignUpBinding binding;
        super.onCreate(savedInstanceState);
        binding = SignUpBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        db = AppActivity.getDatabase();

        // tool bar --------------------------------------------------------------------------------
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(binding.toolbar.toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        setTitle(getTitle());
        // -----------------------------------------------------------------------------------------

        // sign up function
        binding.signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean isValid = true;

                // username ------------------------------------------------------------------------
                String username_string = binding.username.getText().toString().trim();
                if (username_string.isEmpty()) {
                    isValid = false;
                    binding.usernameContainer.setHelperText("Username invalid");
                } else binding.usernameContainer.setHelperTextEnabled(false);

                // email ---------------------------------------------------------------------------
                String email_string = binding.email.getText().toString().trim();
                if (email_string.isEmpty()
                        || !Patterns.EMAIL_ADDRESS.matcher(email_string).matches()) {
                    isValid = false;
                    binding.emailContainer.setHelperText("Email invalid");
                } else if (db.userDAO().checkIfEmailAvailable(email_string)) {
                    isValid = false;
                    binding.emailContainer.setHelperText("Email already in use");
                } else binding.emailContainer.setHelperTextEnabled(false);

                // password ------------------------------------------------------------------------
                String password_string = binding.password.getText().toString().trim();
                if (password_string.isEmpty()) {
                    isValid = false;
                    binding.passwordContainer.setHelperText("Password invalid");
                } else binding.passwordContainer.setHelperTextEnabled(false);

                // password confirm ----------------------------------------------------------------
                String password_confirm_string = binding.passwordConfirm.getText().toString().trim();
                if (password_confirm_string.isEmpty()) {
                    isValid = false;
                    binding.passwordConfirmContainer.setHelperText("Password invalid");
                } else if (!password_string.equals(password_confirm_string)) {
                    isValid = false;
                    binding.passwordConfirmContainer.setHelperText("Password does not match");
                } else binding.passwordConfirmContainer.setHelperTextEnabled(false);

                // sign up -------------------------------------------------------------------------
                if (isValid) {
                    User user = new User();
                    user.setUsername(username_string);
                    user.setEmail(email_string);
                    user.setPassword(password_string);
                    user.setWeight(-1);
                    db.userDAO().insert(user);

                    successfulCreation();
                    openLogin();
                }
            }
        });
    }

    private void successfulCreation() {
        CharSequence message = "Account successfully created!";
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void openLogin() {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
}