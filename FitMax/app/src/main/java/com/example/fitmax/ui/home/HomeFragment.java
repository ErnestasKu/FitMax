package com.example.fitmax.ui.home;

import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StrikethroughSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.fitmax.Database.AppActivity;
import com.example.fitmax.Database.AppDatabase;
import com.example.fitmax.Database.CompletedActivities;
import com.example.fitmax.Database.PhysicalActivity;
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

        db = AppActivity.getDatabase();
        long id_user = SessionManager.getLoginSession(getContext());


        // gets current weekday
        String day = LocalDate.now().getDayOfWeek().name();
        binding.today.setText(day);

        // creates a checklist
        List<PhysicalActivity> list = db.userDAO().getActivitiesOfDay(id_user, day);
        for (PhysicalActivity activity : list) {

            // create checkbox
            CheckBox checkBox = new CheckBox(getContext());
            checkBox.setTextSize(24);
            checkBox.setText(activity.getActivity_name() + ": " + activity.getDuration());

            // assign checked property
            String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
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

        return root;
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