package com.example.fitmax.ui.home;

import android.os.Bundle;
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
import com.example.fitmax.Other.SessionManager;
import com.example.fitmax.R;
import com.example.fitmax.databinding.FHomeBinding;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class HomeFragment extends Fragment {

    private AppDatabase db;
    private FHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        db = AppActivity.getDatabase();

        long id_user = SessionManager.getLoginSession(getContext());
        List<PhysicalActivity> list = db.userDAO().GetTodaysActivities(id_user);
        for (PhysicalActivity activity : list) {

            CheckBox checkBox = new CheckBox(getContext());
            checkBox.setTextSize(20);
            checkBox.setText(activity.getActivity_name() + ": " + activity.getDuration());
            binding.planContainer.addView(checkBox);
            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (checkBox.isChecked()) {
                        CompletedActivities ca = new CompletedActivities();
                        ca.setCompleted(true);
                        ca.setId_activity(activity.getId_activity());
                        ca.setId_user(id_user);

                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        String currentDate = sdf.format(new Date());

                        ca.setCompletion_date(currentDate);

                        db.completedActivitiesDAO().insert(ca);
                        checkBox.setEnabled(false);
                    }
                }
            });
        }

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}