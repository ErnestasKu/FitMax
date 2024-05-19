package com.example.fitmax.ui.home;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.fitmax.Database.AppActivity;
import com.example.fitmax.Database.AppDatabase;
import com.example.fitmax.Database.CompletedActivities;
import com.example.fitmax.Database.PhysicalActivity;
import com.example.fitmax.Database.Plan;
import com.example.fitmax.Database.PlansFromActivities;
import com.example.fitmax.Database.PlansFromActivitiesDAO;
import com.example.fitmax.Database.User;
import com.example.fitmax.Other.SessionManager;
import com.example.fitmax.R;
import com.example.fitmax.databinding.FHomeBinding;
import com.example.fitmax.databinding.SignUpBinding;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
        day = day.substring(0, 1).toUpperCase() + day.substring(1).toLowerCase();
        binding.today.setText(day);

        // creates a checklist
        List<PhysicalActivity> list = db.userDAO().GetActivitiesOfDay(id_user, day);
        for (PhysicalActivity activity : list) {


//            View checkBoxItem = inflater.inflate(R.layout.activity_checkbox, binding.planContainer, false);
//            CheckBox checkBox = checkBoxItem.findViewById(R.id.checkbox_item);
//            CheckBox checkBox = (CheckBox) root.findViewById(R.id.activity_checkbox);

            // create checkbox
            CheckBox checkBox = new CheckBox(getContext());
            checkBox.setTextSize(24);
            checkBox.setText(activity.getActivity_name() + ": " + activity.getDuration());

            // assign checked property
            String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            if (db.completedActivitiesDAO().CheckIfCompleted(activity.getId_activity(), currentDate)) {
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

                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        String currentDate = sdf.format(new Date());

                        ca.setCompletion_date(currentDate);

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

    ;
}