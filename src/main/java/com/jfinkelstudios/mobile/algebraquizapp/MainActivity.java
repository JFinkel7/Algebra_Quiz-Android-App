package com.jfinkelstudios.mobile.algebraquizapp;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.alimuzaffar.lib.widgets.AnimatedEditText;

import java.util.Objects;

import Quiz.Functions_2_3;

public class MainActivity extends AppCompatActivity {
    //*****
    //* VIEWS
    private TextView txtView_Questions;
    private AnimatedEditText editText;
    private ProgressBar progressBar;
    private ImageButton imgBtnHint;
    //* Classes
    private Functions_2_3 functions_2_3;
    private SoundEffects correctSoundEffect, inCorrectSoundEffect;
    //* Data Types
    private static final String PROPERTY_NAME = "progress";
    private static final String INFO = "INFO:";
    private static final String SOUND_IS_DESTROYED = "Sound Is Destroyed";
    private int currentProgress = 0;
    private int incorrectProgress = 0;

    //*****
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /***VIEW ID'S***/
        editText = findViewById(R.id.editTxt_UserInput);
        txtView_Questions = findViewById(R.id.txtView_QuestionInstructions);
        progressBar = findViewById(R.id.progressBar);
        imgBtnHint = findViewById(R.id.imgBtnHint);
        /*****Sound Effects*****/
        correctSoundEffect = new SoundEffects(MainActivity.this, 1);
        correctSoundEffect.setSound(R.raw.single_small_bell);
        inCorrectSoundEffect = new SoundEffects(MainActivity.this, 1);
        inCorrectSoundEffect.setSound(R.raw.sound_error_2);
        /***Functions_2_3 Initialization***/
        functions_2_3 = new Functions_2_3(MainActivity.this);
        //*** Initialize's The Question When Loading The App ***
        txtView_Questions.setText(functions_2_3.getQuestion());
    }

    public void showHint(View view) {
        Toast.makeText(MainActivity.this, functions_2_3.getSolution(), Toast.LENGTH_SHORT).show();
    }

    public void btn_Next(View view) {
        //* Classes
        String input = Objects.requireNonNull(editText.getText()).toString().trim();
        boolean questionIsCorrect = functions_2_3.checkQuestion(input);
        if (questionIsCorrect) {//  // IF CORRECT ↓
            if (!(currentProgress >= 100)) {
                correctSoundEffect.playSound();
                currentProgress += 10;
                /*****Animates The Progress Bar*****/
                ObjectAnimator.ofInt(progressBar, PROPERTY_NAME, currentProgress).setDuration(300).start();
                txtView_Questions.setText(functions_2_3.getRandomQuestion());
            }
        } else {// IF WRONG ↓
            inCorrectSoundEffect.playSound();
            txtView_Questions.setText(functions_2_3.getRandomQuestion());
            if (!(currentProgress <= 0)) {
                /*****Animates The Progress Bar*****/
                ObjectAnimator.ofInt(progressBar, PROPERTY_NAME, currentProgress).setDuration(300).start();
                currentProgress -= 10;
                progressBar.setProgress(currentProgress);
            }
        }

    }


    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        correctSoundEffect.stopSound();
        correctSoundEffect.disposeSound();
        inCorrectSoundEffect.stopSound();
        inCorrectSoundEffect.disposeSound();
        Log.i(INFO, SOUND_IS_DESTROYED);
    }


}// END OF CLASS
