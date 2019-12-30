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
    private static final String SHARED_PREF_ID = "com.jfinkelstudios.mobile.algebraquizapp";
    protected static final String BACKGROUND_CHANGE = "background_change";
    protected static SharedPreferences.Editor editor;
    private SharedPreferences sharedPreferences;
    private boolean nightModeResult;
    //*****>

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        /***FIND VIEW BY ID'S***/
        ToggleButton toggleNightMode = findViewById(R.id.toggleBtn_NightMode);
        toggleNightMode.setChecked(this.nightModeResult);
        toggleNightMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                // * Saves Switch Button Check Status TO The Shared sharedPreferences *
                sharedPreferences = getSharedPreferences(SHARED_PREF_ID, MODE_PRIVATE);
                editor = sharedPreferences.edit();
                editor.putBoolean(BACKGROUND_CHANGE, checked);
                editor.apply();
            }
        });
    }// END OF CREATE


    /* Loads The Saved Preference Data */
    private boolean loadData() {
        sharedPreferences = getSharedPreferences(SHARED_PREF_ID, MODE_PRIVATE);
        return (sharedPreferences.getBoolean(BACKGROUND_CHANGE, false));
    }

    /* Applies The NightMode Data And Sends The Boolean Result To MainActivity */
    public void btnApply(View view) {
        nightModeResult = loadData();
        Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
        intent.putExtra(BACKGROUND_CHANGE, nightModeResult);
        // Prevents Card Stacking
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivityForResult(intent, 1);
        finish();
    }

}// END OF CLASS
