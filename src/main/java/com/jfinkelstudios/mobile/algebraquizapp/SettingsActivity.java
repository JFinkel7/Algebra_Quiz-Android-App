package com.jfinkelstudios.mobile.algebraquizapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {
    //*****>
    // Views
    // Class Objects
    private static final String SHARED_PREF_ID = "com.jfinkelstudios.mobile.algebraquizapp";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private boolean isChecked;
    //*****>

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        /***FIND VIEW BY ID'S***/
        ToggleButton toggleNightMode = findViewById(R.id.toggleBtn_NightMode);
        toggleNightMode.setChecked(loadData());
        toggleNightMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                // * Saves Switch Button Check Status TO The Shared sharedPreferences *
                sharedPreferences = getSharedPreferences(SHARED_PREF_ID, MODE_PRIVATE);
                editor = sharedPreferences.edit();
                editor.putBoolean("SWITCH", checked);
                editor.apply();
            }
        });
    }// END OF CREATE


    private boolean loadData() {
        sharedPreferences = getSharedPreferences(SHARED_PREF_ID, MODE_PRIVATE);
        return (sharedPreferences.getBoolean("SWITCH", false));
    }


    public void btnApply(View view) {
        Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
        intent.putExtra("background_change", loadData());
        // Prevents Card Stacking
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivityForResult(intent, 1);
        finish();
    }


}// END OF CLASS
