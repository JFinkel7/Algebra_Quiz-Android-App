package com.jfinkelstudios.mobile.algebraquizapp;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import Quiz.LinearModels;

public class LinearModelsActivity extends AppCompatActivity {
    // DataTypes
    private static final String DEBUG = "DEBUG";
    //*******>
    // View
    private Button btnQuestion_1, btnQuestion_2;
    private TextView txtViewQuestion, txtViewTarget;
    private String question, chosenAnswer;
    private int xDelta, yDelta;
    private int solutionStartIndex = 1;
    // Classes
    private LinearModels linearModels;

    //*******>
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear_models);
        /***VIEW ID'S***/
        btnQuestion_1 = findViewById(R.id.btnQuestion1);
        btnQuestion_2 = findViewById(R.id.btnQuestion2);
        txtViewQuestion = findViewById(R.id.txtView_Question);
        txtViewTarget = findViewById(R.id.txtView_Target);
        /***CLASS Object Construction***/
        linearModels = new LinearModels(LinearModelsActivity.this);
        txtViewQuestion.setText(linearModels.getRandomQuestion());
        btnQuestion_1.setText(linearModels.getSolution(1));
        btnQuestion_2.setText(linearModels.getSolution(2));
        // SET OTHER METHODS
        setMotionEventListener(btnQuestion_1);
        setMotionEventListener(btnQuestion_2);
        setDragListener(txtViewQuestion);
    }

    //***[Sets Motion Event Listener]***/
    @SuppressLint("ClickableViewAccessibility")
    public void setMotionEventListener(final View eventView) {
        eventView.setOnTouchListener(new View.OnTouchListener() {
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
    public void setDragListener(final View view) {
        /***(1) - This is Listening To The View That You Want To Target At When You Drop It At The Location
         EXAMPLE: Button Is Being Dragged To The TextView ***/
        txtViewQuestion.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View view, DragEvent dragEvent) {
                final View targetView = (View) dragEvent.getLocalState();
                switch (dragEvent.getAction()) {
                    /***(2) - Checks If The Drag Event Has Started***/
                    case (DragEvent.ACTION_DRAG_STARTED):
                        Toast.makeText(LinearModelsActivity.this, "Drag Even't Has Started", Toast.LENGTH_SHORT).show();
                        break;
                    /***(3) - Checks If The Drag Event Has Entered An Area***/
                    case (DragEvent.ACTION_DRAG_ENTERED):

                        //(1) If They Drag (1st) Button Do This ↓
                        if (targetView.getId() == R.id.btnQuestion1) {
                            chosenAnswer = btnQuestion_1.getText().toString();
                            if (linearModels.checkQuestion(chosenAnswer)) {
                                Toast.makeText(LinearModelsActivity.this, "You Are Correct", Toast.LENGTH_SHORT).show();
                            }
                            //(2) If They Drag (2nd) Button Do This ↓
                        } else if (targetView.getId() == R.id.btnQuestion2) {
                            chosenAnswer = btnQuestion_2.getText().toString();
                            if (linearModels.checkQuestion(chosenAnswer)) {
                                Toast.makeText(LinearModelsActivity.this, "You Ar Correct", Toast.LENGTH_SHORT).show();
                            }
                        }
                        break;
                }
                return true;
            }
        });

    }

}

