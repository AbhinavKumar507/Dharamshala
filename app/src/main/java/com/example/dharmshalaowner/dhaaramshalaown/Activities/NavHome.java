package com.example.dharmshalaowner.dhaaramshalaown.Activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.dharmshalaowner.R;
import com.example.dharmshalaowner.dhaaramshalaown.Fragments.about_hotel;
import com.example.dharmshalaowner.dhaaramshalaown.Fragments.profile;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class NavHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_home);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new profile()).commit();
        }
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            item -> {
                Fragment selectedFragment = null;

                switch (item.getItemId()) {
                    case R.id.Home:
                        selectedFragment = new about_hotel();
                        break;

                    case R.id.Profile:
                        selectedFragment = new profile();
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        selectedFragment).commit();
                return true;
            };
}
