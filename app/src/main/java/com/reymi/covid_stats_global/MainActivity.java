package com.reymi.covid_stats_global;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.reymi.covid_stats_global.Fragments.CountryFragment;
import com.reymi.covid_stats_global.Fragments.HomeFragment;
import com.reymi.covid_stats_global.Fragments.InfoFragment;

public class MainActivity extends AppCompatActivity {

    HomeFragment homeFragment = new HomeFragment();
    CountryFragment countryFragment = new CountryFragment();
    InfoFragment infoFragment = new InfoFragment();
    BottomNavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MaterialToolbar toolbar = findViewById(R.id.topAppBar);
        setSupportActionBar(toolbar);

        navigation = findViewById(R.id.btnNavigation);
        navigation.setOnNavigationItemSelectedListener(interfaceBottonNavigation);

        getSupportFragmentManager().beginTransaction().add(R.id.containerFragment, homeFragment).commit();
    }

    private final BottomNavigationView.OnNavigationItemSelectedListener interfaceBottonNavigation = item -> {
        switch (item.getItemId()) {
            case R.id.menuHome:
                loadFragment(homeFragment);
                return true;
            case R.id.menuCountry:
                loadFragment(countryFragment);
                return true;
            case R.id.menuAbout:
                loadFragment(infoFragment);
                return true;
        }
        return false;
    };

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.containerFragment, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}