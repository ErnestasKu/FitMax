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
import com.example.fitmax.Other.SessionManager;
import com.example.fitmax.databinding.QuestionnaireBinding;

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

                Float weight = Float.parseFloat(binding.weight.getText().toString().trim());
                if (weight.isNaN()) {
                    binding.weightContainer.setHelperText("Weight required");
                    return;
                } else if (weight <= 0) {
                    binding.weightContainer.setHelperText("Weight must be positive number");
                    return;
                } else binding.weightContainer.setHelperTextEnabled(false);


                long id_user = getIntent().getLongExtra("id_user", -1);
                if (id_user < 0) {
                    Toast.makeText(getApplicationContext(),
                            "Error, id: " + id_user + " is incorrect",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                String plan_string = binding.planSpinner.getSelectedItem().toString();
                Plan selectedPlan = GetPlanFromName(plan_string, plans);
                db.userDAO().finishQuestionnaire(id_user, weight, selectedPlan.getId_plan());

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
            if (plan.getPlan_name() == name)
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