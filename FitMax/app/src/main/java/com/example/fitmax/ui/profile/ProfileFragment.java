package com.example.fitmax.ui.profile;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
import com.example.fitmax.Other.CommonMethods;
import com.example.fitmax.Other.SessionManager;
import com.example.fitmax.Settings;
import com.example.fitmax.TabScreen;
import com.example.fitmax.databinding.FProfileBinding;

import java.util.List;

public class ProfileFragment extends Fragment {

    private AppDatabase db;
    private FProfileBinding binding;
    SharedPreferences sharedPrefs;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        db = AppActivity.getDatabase();

        long id_user = SessionManager.getLoginSession(getContext());

        binding.textView3.setText(db.userDAO().getUsernameById(id_user));


        binding.editData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToEditSettings();
            }
        });

        binding.resetDaily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetDailyProgress();
            }
        });

        binding.buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SessionManager.endSession(root.getContext());
                SessionManager.GoToLogin(root.getContext());
            }
        });
        return root;
    }

    private void resetDailyProgress() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Confirm reset");
        builder.setMessage("Are you sure you want to reset today's progress?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                long id_user = SessionManager.getLoginSession(getContext());
                db.completedStepsDAO().deleteSteps(id_user, CommonMethods.getToday());
                db.completedActivitiesDAO().deleteActivities(id_user, CommonMethods.getToday());
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

    private void goToEditSettings(){
        Intent intent = new Intent(getContext(), Settings.class);
        getContext().startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}