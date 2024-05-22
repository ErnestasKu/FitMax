package com.example.fitmax;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.fitmax.Database.AppActivity;
import com.example.fitmax.Database.AppDatabase;
import com.example.fitmax.Database.Plan;
import com.example.fitmax.Database.PlanHistory;
import com.example.fitmax.Database.StepHistory;
import com.example.fitmax.Other.SessionManager;
import com.example.fitmax.databinding.QuestionnaireBinding;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Questionnaire extends AppCompatActivity {
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // binding ---------------------------------------------------------------------------------
        QuestionnaireBinding binding;
        binding = QuestionnaireBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        db = AppActivity.getDatabase();
        // tool bar --------------------------------------------------------------------------------
        setSupportActionBar(binding.toolbar.toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        setTitle(getTitle());
        // -----------------------------------------------------------------------------------------

        List<Plan> plans = db.planDAO().getAll();
        populateSpinner(binding.planSpinner, plans);


        // sign up function
        binding.confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isValid = true;

                float weight = 0;
                int steps = 0;

                try {
                    if (binding.weight.getText().toString().isEmpty()) {
                        binding.weightContainer.setHelperText("Weight required");
                        isValid = false;
                    } else if ((weight = Float.parseFloat(binding.weight.getText().toString())) <= 0) {
                        binding.weightContainer.setHelperText("Weight must be positive number");
                        isValid = false;
                    } else binding.weightContainer.setHelperTextEnabled(false);

                } catch (NumberFormatException e) {
                    binding.weightContainer.setHelperText("Invalid weight format");
                    isValid = false;
                }


                long id_user = getIntent().getLongExtra("id_user", -1);
                if (id_user < 0) {
                    Toast.makeText(getApplicationContext(),
                            "Error, id: " + id_user + " is incorrect",
                            Toast.LENGTH_SHORT).show();
                    isValid = false;
                }

                try {
                    if (binding.steps.getText().toString().isEmpty()) {
                        binding.stepContainer.setHelperText("Step count required");
                        isValid = false;
                    } else if ((steps = Integer.parseInt(binding.steps.getText().toString())) <= 0) {
                        binding.stepContainer.setHelperText("Step count must be positive number");
                        isValid = false;
                    } else binding.stepContainer.setHelperTextEnabled(false);

                } catch (NumberFormatException e) {
                    binding.stepContainer.setHelperText("Invalid step count format");
                    isValid = false;
                }


                if (!isValid)
                    return;

                String plan_string = binding.planSpinner.getSelectedItem().toString();
                Plan selectedPlan = GetPlanFromName(plan_string, plans);

                // plan choice
                PlanHistory planHistory = new PlanHistory();
                planHistory.setId_user(id_user);
                planHistory.setId_plan(selectedPlan.getId_plan());
                planHistory.setPlan_date(LocalDate.now().toString());

                // daily step count choice
                StepHistory stepHistory = new StepHistory();
                stepHistory.setId_user(id_user);
                stepHistory.setSteps(steps);
                stepHistory.setStep_date(LocalDate.now().toString());

                db.userDAO().updateUserWeight(id_user, weight);
                db.planHistoryDAO().insert(planHistory);
                db.stepHistoryDAO().insert(stepHistory);

                Toast.makeText(getApplicationContext(), "Account updated successfully",
                        Toast.LENGTH_SHORT).show();

                SessionManager.storeLoginSession(getApplicationContext(), id_user);
                SessionManager.GoToMain(view.getContext());
            }
        });
    }

    private Plan GetPlanFromName(String name, List<Plan> list) {
        Plan foundPlan = null;
        for (Plan plan : list) {
            if (plan.getPlan_name().equals(name))
                foundPlan = plan;
        }
        return foundPlan;
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
}