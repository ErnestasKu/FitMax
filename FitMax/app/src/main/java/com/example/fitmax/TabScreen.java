package com.example.fitmax;

import android.os.Bundle;

import com.example.fitmax.databinding.TabScreenBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.fitmax.databinding.TabScreenBinding;

public class TabScreen extends AppCompatActivity {

    private TabScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = TabScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_profile)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_tab_screen);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

}