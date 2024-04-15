package com.example.fitmax;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.fitmax.Database.AppActivity;
import com.example.fitmax.Database.AppDatabase;
import com.example.fitmax.Other.SessionManager;
import com.example.fitmax.databinding.LoginBinding;

import java.util.Objects;

public class Login extends AppCompatActivity {
    private AppDatabase db;
    SharedPreferences sharedPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoginBinding binding;
        binding = LoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        db = AppActivity.getDatabase();
        // tool bar --------------------------------------------------------------------------------
        setSupportActionBar(binding.toolbar.toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        setTitle(getTitle());
        // -----------------------------------------------------------------------------------------

        binding.logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = binding.email.getText().toString().trim();
                String password = binding.password.getText().toString().trim();

                boolean isValid = true;

                // in case user doesn't exist
                if (email.isEmpty()) {
                    binding.emailContainer.setHelperText("Email is empty");
                    isValid = false;
                } else if (!db.userDAO().checkIfEmailAvailable(email)) {
                    binding.emailContainer.setHelperText("No such email in use");
                    isValid = false;
                } else binding.emailContainer.setHelperTextEnabled(false);


                long id = -1;
                if (password.isEmpty()) {
                    binding.passwordContainer.setHelperText("Password is empty");
                    isValid = false;
                } else if ((id = db.userDAO().getIdByLogin(email, password)) <= 0) {
                    binding.passwordContainer.setHelperText("Incorrect password");
                    isValid = false;
                } else binding.passwordContainer.setHelperTextEnabled(false);

                // check if login info correct
                if (!isValid)
                    return;


                // ---------------------------------------------------------------------------------
//                // date setup
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                String currentDateAndTime = sdf.format(new Date());
//                LoginDate loginDate = new LoginDate();
//                loginDate.setLogin_date(String.valueOf(currentDateAndTime));
//                loginDate.setId_user(id);
//
//                db.loginDateDAO().insert(loginDate);
                // ---------------------------------------------------------------------------------

                if (!db.userDAO().checkIfQuestionnaireComplete(id)){
                    openQuestionnaire(id);
                }
                else {
                    SessionManager.storeLoginSession(getApplicationContext(), id);// storeLoginSession(id);
                    SessionManager.GoToMain(view.getContext());
                }

                // store user id for session
            }
        });
        binding.singUpLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSingUp();
            }
        });
    }

//    public void storeLoginSession(long id){
//        SharedPreferences sharedPrefs = getSharedPreferences("user", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPrefs.edit();
//        editor.putLong("user", id);
//        editor.apply();
//        openTabActivity();
//    }

//    public void openTabActivity() {
//        Intent intent = new Intent(this, TabScreen.class);
//        startActivity(intent);
//    }

    public void openQuestionnaire(long id_user) {
        Intent intent = new Intent(this, Questionnaire.class);
        intent.putExtra("id_user", id_user);
        startActivity(intent);
    }

    public void openSingUp() {
        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);
    }
}