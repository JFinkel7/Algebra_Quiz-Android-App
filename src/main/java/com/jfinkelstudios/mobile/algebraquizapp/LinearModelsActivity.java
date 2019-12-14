package com.jfinkelstudios.mobile.algebraquizapp;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import Quiz.LinearModels;
import Quiz.SoundEffects;

public class LinearModelsActivity extends AppCompatActivity {
    //* Data Types
    private static final String PROPERTY_NAME = "progress";
    private static final String DEBUG = "DEBUG";
    private static int currentProgress = 0;
    ProgressBar progressBar;
    private String question, chosenAnswer;
    private TextView txtViewQuestion, txtViewTarget;
    //*****
    //* VIEWS
    private Button btnQuestion_1, btnQuestion_2, btnQuestion_3;
    //* Classes
    private LinearModels linearModels;
    private SoundEffects correctSound, inCorrectSound, dragStartSound, dragEndSound;

    //********>
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear_models);
        /***VIEW ID'S***/
        btnQuestion_1 = findViewById(R.id.btnQuestion1);
        btnQuestion_2 = findViewById(R.id.btnQuestion2);
        btnQuestion_3 = findViewById(R.id.btnQuestion3);
        txtViewQuestion = findViewById(R.id.txtView_Question);
        txtViewTarget = findViewById(R.id.txtView_Target);
        progressBar = findViewById(R.id.progressBar);
        /***CLASS Object Construction***/
        linearModels = new LinearModels(LinearModelsActivity.this);
        // correctSound
        correctSound = new SoundEffects(this, 1);
        correctSound.setSound(R.raw.single_small_bell);
        // inCorrectSound
        inCorrectSound = new SoundEffects(this, 1);
        inCorrectSound.setSound(R.raw.sound_error_2);
        // Drag Start Sound
        dragStartSound = new SoundEffects(this, 1);
        dragStartSound.setSound(R.raw.sound_tap_hard);
        // Drag End Sound
        dragEndSound = new SoundEffects(this, 1);
        dragEndSound.setSound(R.raw.sound_fast_whoosh1);
        // VIEW CONFIGURATIONS
        btnQuestion_1.setText(linearModels.getSolution(1));
        btnQuestion_2.setText(linearModels.getSolution(2));
        btnQuestion_3.setText(linearModels.getSolution(3));
        txtViewQuestion.setText(linearModels.getRandomQuestion());
        // SET OTHER METHODS
        setMotionEventListener(btnQuestion_1);
        setMotionEventListener(btnQuestion_2);
        setMotionEventListener(btnQuestion_3);
        setDragListener(txtViewTarget);
    }

    //***[Sets PopUp Video Activity]***/
    public void imgBtnShowVideoHelp(View view) {
        Intent intent = new Intent(getBaseContext(), PopUpVideoPlayerActivity.class);
        intent.putExtra("videoID", linearModels.getRandomVideoPath());
        startActivity(intent);
    }

    //***[Sets Motion Event Listener]***/
    @SuppressLint("ClickableViewAccessibility")
    public void setMotionEventListener(final View EVENT_VIEW) {
        EVENT_VIEW.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                ClipData data = ClipData.newPlainText("", "");
                /*** (1) When clicking The Event A Shadow Version Of It WIll Appear When You Drag Item ***/
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(data, shadowBuilder, view, 0);
                return (true);
            }
        });
    }

    //***[Sets Drag Event Listener]***/
    public void setDragListener(final View VIEW) {
        /***(1) - This is Listening To The View That You Want To Target At When You Drop It At The Location***/
        VIEW.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View view, final DragEvent DRAG_EVENT) {
                final View TARGET_VIEW = (View) DRAG_EVENT.getLocalState();
                /***(2) - Checks If The Drag Event Has Started***/
                if (DRAG_EVENT.getAction() == DragEvent.ACTION_DRAG_STARTED) {
                    dragStartSound.playSound();
                }
                //
                /***(3) - Checks If The Drag Event Has Entered An Area***/
                else if (DRAG_EVENT.getAction() == DragEvent.ACTION_DROP) {
                    switch (TARGET_VIEW.getId()) {
                        // <**** BTN QUESTION 1 ****>
                        case (R.id.btnQuestion1):
                            chosenAnswer = btnQuestion_1.getText().toString();
                            /*****Animates The Progress Bar If Correct*****/
                            if (linearModels.checkQuestion(chosenAnswer)) {// * CORRECT * ↓
                                if (!(currentProgress >= 100)) {
                                    correctSound.playSound();
                                    currentProgress += 10;
                                    ObjectAnimator.ofInt(progressBar, PROPERTY_NAME, currentProgress).setDuration(300).start();
                                    // Sets A New Random Question
                                    txtViewQuestion.setText(linearModels.getRandomQuestion());
                                }

                            } else {// * WRONG * ↓
                                /*****Animates The Progress Bar If Wrong*****/
                                if (!(currentProgress <= 0)) {
                                    currentProgress -= 10;
                                    ObjectAnimator.ofInt(progressBar, PROPERTY_NAME, currentProgress).setDuration(300).start();
                                }
                                inCorrectSound.playSound();
                            }
                            break;

                        // <**** BTN QUESTION 2 ****>
                        case (R.id.btnQuestion2):
                            chosenAnswer = btnQuestion_2.getText().toString();
                            if (linearModels.checkQuestion(chosenAnswer)) {// * CORRECT * ↓
                                /*****Animates The Progress Bar If Correct*****/
                                if (!(currentProgress >= 100)) {
                                    correctSound.playSound();
                                    currentProgress += 10;
                                    ObjectAnimator.ofInt(progressBar, PROPERTY_NAME, currentProgress).setDuration(300).start();
                                    // Sets A New Random Question
                                    txtViewQuestion.setText(linearModels.getRandomQuestion());
                                }
                            } else {// * WRONG * ↓
                                /*****Animates The Progress Bar If Wrong*****/
                                if (!(currentProgress <= 0)) {
                                    currentProgress -= 10;
                                    ObjectAnimator.ofInt(progressBar, PROPERTY_NAME, currentProgress).setDuration(300).start();
                                }
                                inCorrectSound.playSound();
                            }
                            break;

                        // <**** BTN QUESTION 3 ****>
                        case (R.id.btnQuestion3):
                            chosenAnswer = btnQuestion_3.getText().toString();
                            if (linearModels.checkQuestion(chosenAnswer)) {// * CORRECT * ↓
                                /*****Animates The Progress Bar If Correct*****/
                                if (!(currentProgress >= 100)) {
                                    correctSound.playSound();
                                    currentProgress += 10;
                                    ObjectAnimator.ofInt(progressBar, PROPERTY_NAME, currentProgress).setDuration(300).start();
                                    // Sets A New Random Question
                                    txtViewQuestion.setText(linearModels.getRandomQuestion());
                                }
                            } else {// * WRONG * ↓
                                /*****Animates The Progress Bar If Wrong*****/
                                if (!(currentProgress <= 0)) {
                                    currentProgress -= 10;
                                    ObjectAnimator.ofInt(progressBar, PROPERTY_NAME, currentProgress).setDuration(300).start();
                                }
                                inCorrectSound.playSound();
                            }
                            break;
                    }
                }
                return true;
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        correctSound.stopSound();
        correctSound.disposeSound();
        inCorrectSound.stopSound();
        inCorrectSound.disposeSound();
        dragStartSound.stopSound();
        dragStartSound.disposeSound();
        dragEndSound.stopSound();
        dragEndSound.disposeSound();
    }


}// END OF CLASS
