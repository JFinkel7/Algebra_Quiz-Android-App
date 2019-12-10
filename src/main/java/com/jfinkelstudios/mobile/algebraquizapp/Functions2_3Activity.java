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

public class Functions2_3Activity extends AppCompatActivity {
    //* Data Types
    private static final String PROPERTY_NAME = "progress";
    private static final String INFO = "INFO:";
    private static final String SOUND_IS_DESTROYED = "Sound Is Destroyed";
    //*****
    //* VIEWS
    private TextView txtView_Questions;
    private AnimatedEditText editText;
    private ProgressBar progressBar;
    //* Classes
    private Functions_2_3 functions_2_3;
    private SoundEffects correctSoundEffect, inCorrectSoundEffect;
    private static int currentProgress = 0;


    //*****
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_functions2_3);
        /***VIEW ID'S***/
        editText = findViewById(R.id.editTxt_UserInput);
        txtView_Questions = findViewById(R.id.txtView_QuestionInstructions);
        progressBar = findViewById(R.id.progressBar);
        ImageButton imgBtnHint = findViewById(R.id.imgBtnHint);
        /*****Sound Effects*****/
        correctSoundEffect = new SoundEffects(Functions2_3Activity.this, 1);
        correctSoundEffect.setSound(R.raw.single_small_bell);
        inCorrectSoundEffect = new SoundEffects(Functions2_3Activity.this, 1);
        inCorrectSoundEffect.setSound(R.raw.sound_error_2);
        /***Functions_2_3 Initialization***/
        functions_2_3 = new Functions_2_3(Functions2_3Activity.this);
        /***Initialize's The Table Functions_2_3 Question When Loading The App***/
        txtView_Questions.setText(functions_2_3.getQuestion());
    }


    public void btn_Next(View view) {
        String input = Objects.requireNonNull(editText.getText()).toString().trim();
        boolean questionIsCorrect = functions_2_3.checkQuestion(input);
        if (questionIsCorrect) {// IF CORRECT ↓
            if (!(currentProgress >= 100)) {
                correctSoundEffect.playSound();
                /*****Animates The Progress Bar*****/
                currentProgress += 10;
                ObjectAnimator.ofInt(progressBar, "progress", currentProgress).setDuration(300).start();
                txtView_Questions.setText(functions_2_3.getRandomQuestion());
            }

        } else {// IF WRONG ↓
            inCorrectSoundEffect.playSound();
            if (!(currentProgress <= 0)) {
                /*****Animates The Progress Bar*****/
                currentProgress -= 10;
                ObjectAnimator.ofInt(progressBar, "progress", currentProgress).setDuration(300).start();
            }
        }

    }

    public void showHint(View view) {
        Toast.makeText(Functions2_3Activity.this, functions_2_3.getRandomSolution(), Toast.LENGTH_SHORT).show();
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
