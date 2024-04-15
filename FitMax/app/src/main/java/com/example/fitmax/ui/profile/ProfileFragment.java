package com.example.fitmax.ui.profile;

import android.content.Context;
import android.content.SharedPreferences;
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
import com.example.fitmax.Other.SessionManager;
import com.example.fitmax.databinding.FProfileBinding;

import java.util.List;

public class ProfileFragment extends Fragment {

    private AppDatabase db;
    private FProfileBinding binding;
    SharedPreferences sharedPrefs;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ProfileViewModel profileViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);

        binding = FProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        db = AppActivity.getDatabase();

        sharedPrefs = getContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        long id = sharedPrefs.getLong("user", -1);

        String username = db.userDAO().getUsernameById(id);
        binding.textView3.setText(String.valueOf(username));

        binding.buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SessionManager.endSession(root.getContext());
                SessionManager.GoToLogin(root.getContext());
            }
        });

//        List<String> dates = db.loginDateDAO().getAllLoginsById(id);
//        String full_dates = "";
//        for (String date : dates) {
//            full_dates += date + "\n";
//        }
//        binding.logindDates.setText(full_dates);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}