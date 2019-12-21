package com.jfinkelstudios.mobile.algebraquizapp;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

public class SettingsActivity extends AppCompatActivity {
    private ToggleButton toggleNightMode;
    private DrawerLayout mainActivityDrawer;
    private LinearLayout settingsActivityLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        //*
        toggleNightMode = findViewById(R.id.toggleBtn_NightMode);
        mainActivityDrawer = findViewById(R.id.mainActivityDrawerLayout);
        settingsActivityLinearLayout = findViewById(R.id.settingActivityLinearLayout);

        toggleNightMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                if (checked) {
                    settingsActivityLinearLayout.setBackgroundColor(Color.BLACK);
                    compoundButton.setTextColor(Color.WHITE);
                } else {
                    settingsActivityLinearLayout.setBackgroundColor(Color.WHITE);
                    Toast.makeText(SettingsActivity.this, "Stats Are Off", Toast.LENGTH_SHORT).show();
                    compoundButton.setTextColor(Color.BLACK);

                }
            }
        });
        //*


    }
}
