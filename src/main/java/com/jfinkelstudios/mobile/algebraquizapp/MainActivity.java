/*
 * Software Developer: Denis J Finkel
 * Project Goal: Create A Functional Algebra Quiz App
 */
package com.jfinkelstudios.mobile.algebraquizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    //*****
    //*****
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final DrawerLayout DRAWER_LAYOUT = findViewById(R.id.mainActivityDrawerLayout);
        final NavigationView NAVIGATION_VIEW = findViewById(R.id.mainActivityNavigationView);
        NAVIGATION_VIEW.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    // * (Starts FUNCTIONS_2_3 ACTIVITY) *
                    case (R.id.menuFunctions_2_3):
                        startActivity(new Intent(MainActivity.this, Functions2_3Activity.class));

                        break;

                    // * (Starts LinearModels ACTIVITY) *
                    case (R.id.menuLinearModels):
                        startActivity(new Intent(MainActivity.this, LinearModelsActivity.class));

                        break;

                    // * (Starts EquationLines ACTIVITY) *
                    case (R.id.menuEquationLines):
                        startActivity(new Intent(MainActivity.this, EquationLinesActivity.class));

                        break;
                }
                return (false);
            }
        });
    }

}// END OF CLASS
