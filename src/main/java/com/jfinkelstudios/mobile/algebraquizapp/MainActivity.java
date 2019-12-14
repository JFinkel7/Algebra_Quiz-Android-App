/*
 * Software Developer: Denis J Finkel
 * Project Goal: Create A Functional Algebra Quiz App
 */
package com.jfinkelstudios.mobile.algebraquizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    //*****
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnFunctions2_3(View view) {
        startActivity(new Intent(this, Functions2_3Activity.class));
    }

    public void btnLinearFunctions(View view) {
        startActivity(new Intent(this, LinearModelsActivity.class));
    }

    public void btnEquationsLines(View view) {
        startActivity(new Intent(this, EquationLinesActivity.class));
    }
}// END OF CLASS
