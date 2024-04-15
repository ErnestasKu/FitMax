package com.example.fitmax.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.fitmax.Database.AppActivity;
import com.example.fitmax.Database.AppDatabase;
import com.example.fitmax.Database.PhysicalActivity;
import com.example.fitmax.Other.SessionManager;
import com.example.fitmax.databinding.FHomeBinding;

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

//        final TextView textView = binding.planDetails;


        db = AppActivity.getDatabase();
        binding.planDetails.setText("");

        long id_user = SessionManager.getLoginSession(getContext());
        List<PhysicalActivity> list = db.userDAO().GetTodaysActivities(id_user);
        String lines = "";
        for (PhysicalActivity activity : list) {
            lines += activity.getActivity_name() + ": " + activity.getDuration() + "\n\n";
        }
        binding.planDetails.setText(lines);


//        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}