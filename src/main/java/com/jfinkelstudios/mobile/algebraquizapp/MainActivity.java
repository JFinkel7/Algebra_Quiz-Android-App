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
        startActivity(new Intent(MainActivity.this, Functions2_3Activity.class));
    }
}// END OF CLASS
