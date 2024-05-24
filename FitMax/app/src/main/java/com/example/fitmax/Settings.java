package com.example.fitmax;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import com.example.fitmax.Database.AppActivity;
import com.example.fitmax.Database.AppDatabase;
import com.example.fitmax.Database.Plan;
import com.example.fitmax.Database.PlanHistory;
import com.example.fitmax.Database.StepHistory;
import com.example.fitmax.Database.User;
import com.example.fitmax.Other.CommonMethods;
import com.example.fitmax.Other.SessionManager;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.fitmax.databinding.SettingsBinding;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Settings extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private SettingsBinding binding;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = SettingsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = AppActivity.getDatabase();
        long id_user = SessionManager.getLoginSession(getBaseContext());

        List<Plan> plans = db.planDAO().getAll();
        populateSpinner(binding.planSpinner, plans);

        displayWeight();
        displaySteps();
        displayPlan();


        // tool bar --------------------------------------------------------------------------------
        setSupportActionBar(binding.toolbar.toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        setTitle(getTitle());
        // -----------------------------------------------------------------------------------------
        binding.saveWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setWeight();
            }
        });

        binding.saveSteps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSteps();
            }
        });

        binding.savePlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setPlan();
            }
        });

        binding.deleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteUser();
            }
        });
    }

    private void setWeight() {
        if (binding.weightInput.getText().toString().isEmpty())
            return;

        float weight;
        try {
            weight = Float.parseFloat(binding.weightInput.getText().toString());
        } catch (NumberFormatException e) {
            binding.weightContainer.setHelperText("Invalid weight");
            return;
        }

        if (weight <= 0) {
            binding.weightContainer.setHelperText("Weight must be positive number");
            return;
        }

        // valid
        db.userDAO().updateUserWeight(SessionManager.getLoginSession(getBaseContext()), weight);
        binding.weightContainer.setHelperTextEnabled(false);
        displayWeight();
    }

    private void setSteps() {
        if (binding.stepInput.getText().toString().isEmpty())
            return;

        int steps;
        try {
            steps = Integer.parseInt(binding.stepInput.getText().toString());
        } catch (NumberFormatException e) {
            binding.stepContainer.setHelperText("Invalid step count format");
            return;
        }

        if (steps <= 0) {
            binding.stepContainer.setHelperText("Step count must be positive number");
            return;
        }

        // valid
        StepHistory stepHistory = new StepHistory();
        stepHistory.setSteps(steps);
        stepHistory.setStep_date(CommonMethods.getToday());
        stepHistory.setId_user(SessionManager.getLoginSession(getBaseContext()));

        db.stepHistoryDAO().insert(stepHistory);
        binding.stepContainer.setHelperTextEnabled(false);
        displaySteps();
    }

    private void setPlan() {

        long id_user = SessionManager.getLoginSession(getBaseContext());
        long id_plan = binding.planSpinner.getSelectedItemId() + 1;

        if (id_plan == db.planHistoryDAO().getUserPlan(id_user, CommonMethods.getToday()))
            return;

        // valid
        PlanHistory planHistory = new PlanHistory();
        planHistory.setId_user(SessionManager.getLoginSession(getBaseContext()));
        planHistory.setId_plan(id_plan);
        planHistory.setPlan_date(CommonMethods.getToday());

        db.completedActivitiesDAO().deleteActivities(id_user, CommonMethods.getToday());
        db.planHistoryDAO().insert(planHistory);
        displayPlan();
    }

    public void displayWeight() {
        long id_user = SessionManager.getLoginSession(getBaseContext());
        User user = db.userDAO().getUser(id_user);
        binding.weightDisplay.setText("Weight: " + user.getWeight() + " kg");
    }

    public void displaySteps() {
        long id_user = SessionManager.getLoginSession(getBaseContext());
        int steps = db.stepHistoryDAO().getUserSteps(id_user, CommonMethods.getToday());
        binding.stepDisplay.setText("Daily step count: " + steps);
    }

    public void displayPlan() {
        long id_user = SessionManager.getLoginSession(getBaseContext());
        long id_plan = db.planHistoryDAO().getUserPlan(id_user, CommonMethods.getToday());
        binding.planDisplay.setText("Plan: " + db.planDAO().getPlan(id_plan).getPlan_name());
        binding.planSpinner.setSelection((int) id_plan - 1);
    }

    private void populateSpinner(Spinner spinner, List<Plan> list) {

        List<String> spinner_list = new ArrayList<>();
        for (Plan plan : list) {
            spinner_list.add(plan.getPlan_name());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                R.layout.s_plan_layout, spinner_list);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void deleteUser() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm deletion");
        builder.setMessage("Are you sure you want to delete your account?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                long id_user = SessionManager.getLoginSession(getBaseContext());

                db.completedActivitiesDAO().deleteAll(id_user);
                db.completedStepsDAO().deleteAll(id_user);
                db.planHistoryDAO().deleteAll(id_user);
                db.stepHistoryDAO().deleteAll(id_user);
                db.userDAO().deleteAll(id_user);

                SessionManager.endSession(getBaseContext());
                SessionManager.GoToStartingActivity(Settings.this);
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}