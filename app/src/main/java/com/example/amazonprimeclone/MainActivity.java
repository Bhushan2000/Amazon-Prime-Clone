package com.example.amazonprimeclone;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.ActionBar;
import android.app.Activity;
import android.app.PictureInPictureParams;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Rational;
import android.view.Display;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.amazonprimeclone.HomeData.fragmets.HomeTabFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    ActionBar actionBar;
    BottomNavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        actionBar = getActionBar();


        //loading the default fragment
        loadFragment(new HomeFragment());

        //getting bottom navigation view and attaching the listener
        navigation = findViewById(R.id.navigation);

        navigation.setOnNavigationItemSelectedListener(this);


        navigation.setItemIconTintList(null);


    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.navigation_home:
                fragment = new HomeFragment();
                break;

            case R.id.navigation_find:
                fragment = new FindFragment();
                break;

            case R.id.navigation_downloads:
                fragment = new DownloadsFragment();

                break;

            case R.id.navigation_profile:
                fragment = new ProfileFragment();
                break;
        }

        return loadFragment(fragment);
    }

    public boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }





    @Override
    public void onBackPressed() {


        if (navigation.getSelectedItemId() == R.id.navigation_home) {
            super.onBackPressed();
        } else {
            loadFragment(new HomeFragment());
            navigation.setSelectedItemId(R.id.navigation_home);

        }

    }
}