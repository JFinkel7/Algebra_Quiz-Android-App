package com.jfinkelstudios.mobile.algebraquizapp;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import Quiz.EquationOfLines;
import Quiz.SoundEffects;

public class EquationLinesActivity extends AppCompatActivity {
    //*****> CLASS INSTANCE VARIABLES
    private ProgressBar progressBar;
    private TextView txtView_Questions;
    private RadioGroup radioGroup;
    private RadioButton selectedRadioBtn, radioBtn_Solution_1, radioBtn_Solution_2, radioBtn_Solution_3, radioBtn_Solution_4;
    //* Classes
    private EquationOfLines equationOfLines;
    private SoundEffects correctSoundEffect, inCorrectSoundEffect;
    //* DATA TYPES
    private static int currentProgress = 0;
    private static final String PROPERTY_NAME = "progress";

    //********>
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equation_lines);
        /***FIND VIEW BY ID'S***/
        radioGroup = findViewById(R.id.radioGroup);
        radioBtn_Solution_1 = findViewById(R.id.radio_A);
        radioBtn_Solution_2 = findViewById(R.id.radio_B);
        radioBtn_Solution_3 = findViewById(R.id.radio_C);
        radioBtn_Solution_4 = findViewById(R.id.radio_D);
        txtView_Questions = findViewById(R.id.txtView_QuestionInstructions);
        progressBar = findViewById(R.id.progressBar);
        /*****Sound Effects*****/
        correctSoundEffect = new SoundEffects(EquationLinesActivity.this, 1);
        correctSoundEffect.setSound(R.raw.sound_small_bell);
        inCorrectSoundEffect = new SoundEffects(EquationLinesActivity.this, 1);
        inCorrectSoundEffect.setSound(R.raw.sound_error_2);
        /***Functions_2_3 Initialization***/
        equationOfLines = new EquationOfLines(EquationLinesActivity.this);
        /***Initialize's The Table EquationOfLines TEXT_VIEW Question When Loading The App***/
        txtView_Questions.setText(equationOfLines.getQuestion());
        /***Sets The Solution Text For The Radio Buttons***/
        radioBtn_Solution_1.setText(equationOfLines.getSolution(1));
        radioBtn_Solution_2.setText(equationOfLines.getSolution(2));
        radioBtn_Solution_3.setText(equationOfLines.getSolution(3));
        radioBtn_Solution_4.setText(equationOfLines.getSolution(4));
    }


    // Checks The Radio Button
    public void checkRadioBtn(View view) {
        int radioID = radioGroup.getCheckedRadioButtonId();
        selectedRadioBtn = findViewById(radioID);
    }

    // Btn To Confirm The Question
    public void btnConfirm(View v) {
        if (selectedRadioBtn != null) {
            boolean isQuestionCorrect = equationOfLines.checkQuestion(selectedRadioBtn.getText().toString());
            if (isQuestionCorrect) {// IF CORRECT ↓
                correctSoundEffect.playSound();
                Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show();
                if (!(currentProgress >= 100)) {
                    correctSoundEffect.playSound();
                    /*****Animates The Progress Bar*****/
                    currentProgress += 10;
                    ObjectAnimator.ofInt(progressBar, PROPERTY_NAME, currentProgress).setDuration(300).start();
                    txtView_Questions.setText(equationOfLines.getRandomQuestion());
                    radioGroup.clearCheck();
                }
            } else {// IF WRONG ↓
                inCorrectSoundEffect.playSound();
                if (!(currentProgress <= 0)) {
                    /*****Animates The Progress Bar*****/
                    currentProgress -= 10;
                    ObjectAnimator.ofInt(progressBar, PROPERTY_NAME, currentProgress).setDuration(300).start();
                }
            }
        } else {
            Toast.makeText(EquationLinesActivity.this, "Please Select An Option", Toast.LENGTH_SHORT).show();
        }
    }

    //***[Shows The Video Tutorial From The VideoView]***/
    public void imgBtnShowVideoHelp(View view) {
        // Passing Resource Data To Another Activity
        Intent intent = new Intent(getBaseContext(), PopUpVideoPlayerActivity.class);
        intent.putExtra("videoID", equationOfLines.getRandomVideoPath());
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        correctSoundEffect.stopSound();
        correctSoundEffect.disposeSound();
        inCorrectSoundEffect.stopSound();
        inCorrectSoundEffect.disposeSound();
    }


}// END OF CLASS 
