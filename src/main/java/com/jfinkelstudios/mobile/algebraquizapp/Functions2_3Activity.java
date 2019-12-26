package com.jfinkelstudios.mobile.algebraquizapp;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.alimuzaffar.lib.widgets.AnimatedEditText;

import java.util.Objects;

import Quiz.Functions_2_3;
import Quiz.SoundEffects;

public class Functions2_3Activity extends AppCompatActivity {
    //*****> CLASS INSTANCE VARIABLES
    //* Data Types
    private static final String PROPERTY_NAME = "progress";
    private static final String INFO = "INFO:";
    private static final String SOUND_IS_DESTROYED = "Sound Is Destroyed";
    private static int currentProgress = 0;
    //*****
    //* VIEWS
    private TextView txtView_Questions;
    private AnimatedEditText editText;
    private ProgressBar progressBar;
    private Button btnNext;
    private RelativeLayout mainRelativeLayout;
    //* Classes
    private Functions_2_3 functions_2_3;
    private SoundEffects correctSoundEffect, inCorrectSoundEffect;

    //********>
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_functions2_3);
        /***VIEW ID'S***/
        editText = findViewById(R.id.editTxt_UserInput);
        txtView_Questions = findViewById(R.id.txtView_QuestionInstructions);
        progressBar = findViewById(R.id.progressBar);
        btnNext = findViewById(R.id.btnNext);
        mainRelativeLayout = findViewById(R.id.functions_2_3MainRelativeLayout);
        /*****Sound Effects*****/
        correctSoundEffect = new SoundEffects(Functions2_3Activity.this, 1);
        correctSoundEffect.setSound(R.raw.sound_small_bell);
        inCorrectSoundEffect = new SoundEffects(Functions2_3Activity.this, 1);
        inCorrectSoundEffect.setSound(R.raw.sound_error_2);
        /***Functions_2_3 Initialization***/
        functions_2_3 = new Functions_2_3(Functions2_3Activity.this);
        /***Initialize's The Table Functions_2_3 Question When Loading The App***/
        txtView_Questions.setText(functions_2_3.getQuestion());
        if (MainActivity.nightModeResult) {
            mainRelativeLayout.setBackgroundColor(Color.BLACK);
            btnNext.setBackgroundColor(Color.WHITE);
            btnNext.setTextColor(Color.BLACK);
            editText.setTextColor(Color.WHITE);
            txtView_Questions.setTextColor(Color.WHITE);
        }

    }

    //***[Checks The Question]***/
    public void btn_Next(View view) {
        String input = Objects.requireNonNull(editText.getText()).toString().trim();
        boolean questionIsCorrect = functions_2_3.checkQuestion(input);
        if (questionIsCorrect) {// IF CORRECT ↓
            if (!(currentProgress >= 100)) {
                correctSoundEffect.playSound();
                /*****Animates The Progress Bar*****/
                currentProgress += 10;
                ObjectAnimator.ofInt(progressBar, PROPERTY_NAME, currentProgress).setDuration(300).start();
                txtView_Questions.setText(functions_2_3.getRandomQuestion());
            }

        } else {// IF WRONG ↓
            inCorrectSoundEffect.playSound();
            if (!(currentProgress <= 0)) {
                /*****Animates The Progress Bar*****/
                currentProgress -= 10;
                ObjectAnimator.ofInt(progressBar, PROPERTY_NAME, currentProgress).setDuration(300).start();
            }
        }

    }

    //***[Shows The Solution]***/
    public void imgBtnShowHelp(View view) {
        Toast.makeText(Functions2_3Activity.this, functions_2_3.getRandomSolution(), Toast.LENGTH_SHORT).show();
    }

    //***[Shows The Video Tutorial From The VideoView]***/
    public void imgBtnShowVideoHelp(View view) {
        // Passing Resource Data To Another Activity
        Intent intent = new Intent(getBaseContext(), PopUpVideoPlayerActivity.class);
        intent.putExtra("videoID", functions_2_3.getRandomVideoPath());
        startActivity(intent);
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


}
