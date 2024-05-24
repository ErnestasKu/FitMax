package com.example.fitmax.ui.home;

import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StrikethroughSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.fitmax.Database.AppActivity;
import com.example.fitmax.Database.AppDatabase;
import com.example.fitmax.Database.CompletedActivities;
import com.example.fitmax.Database.CompletedSteps;
import com.example.fitmax.Database.PhysicalActivity;
import com.example.fitmax.Other.CommonMethods;
import com.example.fitmax.Other.SessionManager;
import com.example.fitmax.databinding.FHomeBinding;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class HomeFragment extends Fragment {

    private AppDatabase db;
    private FHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        setStepCountDisplay();
        setActivityDisplay();

        binding.addSteps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (addSteps())
                    setStepCountDisplay();
            }
        });

        return root;
    }

    private boolean addSteps() {
        if (binding.stepInput.getText().toString().isEmpty())
            return false;

        long id_user = SessionManager.getLoginSession(getContext());
        int steps = Integer.parseInt(binding.stepInput.getText().toString());
        String today = LocalDate.now().toString();

        if (db.completedStepsDAO().userEntriesAdded(id_user, today) > 0)
            db.completedStepsDAO().addSteps(id_user, today, steps);
        else {
            CompletedSteps completedSteps = new CompletedSteps();
            completedSteps.setId_user(id_user);
            completedSteps.setCompletion_date(today);
            completedSteps.setStep_count(steps);
            db.completedStepsDAO().insert(completedSteps);
        }
        return true;
    }

    private void setStepCountDisplay() {

        db = AppActivity.getDatabase();
        long id_user = SessionManager.getLoginSession(getContext());

        // progress bar
        String today = CommonMethods.getToday();
        int maxSteps = db.stepHistoryDAO().getUserSteps(id_user, today);
        int currentSteps = db.completedStepsDAO().getUserSteps(id_user, today);
        binding.stepProgress.setProgressMax(maxSteps);
        binding.stepProgress.setProgressWithAnimation(currentSteps, 500L);

        binding.stepsTotal.setText(currentSteps + "/" + maxSteps);
        int percentage = (int) ((float) currentSteps / (float) maxSteps * 100);
        Log.d("EEEEEEE", "Percentage is: " + percentage);
        binding.stepPercentage.setText(percentage + "%");
    }

    public void setActivityDisplay() {
        long id_user = SessionManager.getLoginSession(getContext());
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        // gets current weekday
        String day = LocalDate.now().getDayOfWeek().name();
        binding.today.setText(day);

        // creates a checklist
        long id_plan = db.planHistoryDAO().getUserPlan(id_user, LocalDate.now().toString());
        List<PhysicalActivity> list = db.plansFromActivitiesDAO().getActivitiesOfDay(id_plan, day);
        for (PhysicalActivity activity : list) {

            // create checkbox
            CheckBox checkBox = new CheckBox(getContext());
            checkBox.setTextSize(24);
            checkBox.setText(activity.getActivity_name() + ": " + activity.getDuration());

            // assign checked property
            if (db.completedActivitiesDAO().checkIfCompleted(id_user, activity.getId_activity(), today)) {
                checkBox.setChecked(true);
                checkBox.setText(crossOutText(checkBox.getText().toString()));
                checkBox.setEnabled(false);
            } else {
                checkBox.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        CompletedActivities ca = new CompletedActivities();
                        ca.setId_activity(activity.getId_activity());
                        ca.setId_user(id_user);
                        ca.setCompletion_date(today);
                        db.completedActivitiesDAO().insert(ca);

                        checkBox.setText(crossOutText(checkBox.getText().toString()));
                        checkBox.setEnabled(false);
                    }
                });
            }
            binding.planContainer.addView(checkBox);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public Spannable crossOutText(String text) {
        Spannable crossedText = new SpannableString(text);
        crossedText.setSpan(new StrikethroughSpan(), 0, crossedText.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return crossedText;
    }
}