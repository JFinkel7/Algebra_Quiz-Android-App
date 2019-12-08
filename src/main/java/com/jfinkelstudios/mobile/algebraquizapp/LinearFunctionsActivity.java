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

public class LinearFunctionsActivity extends AppCompatActivity {
    private static final String DEBUG = "DEBUG";
    private Button btnDragMe, btnDragMe2;
    private String question, viewData1, viewData2;
    private TextView txtViewQuestion;
    private int xDelta, yDelta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear_functions);
        /***VIEW ID'S***/
        btnDragMe = findViewById(R.id.btnToDrag);
        btnDragMe2 = findViewById(R.id.btnToDrag2);
        txtViewQuestion = findViewById(R.id.txtView_Target);
        // SET OTHER METHODS
        setMotionEventListener(btnDragMe);
        setMotionEventListener(btnDragMe2);
        setDragListener(txtViewQuestion);

    }

    //*** Sets Motion Event Listener ***/
    @SuppressLint("ClickableViewAccessibility")
    public void setMotionEventListener(final View eventView) {
        eventView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                final int X = (int) motionEvent.getX();
                final int Y = (int) motionEvent.getY();
                ClipData data = ClipData.newPlainText("", "");
                /*** (1) When clicking The Event A Shadow Version Of It WIll Appear When You Drag Item ***/
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(data, shadowBuilder, view, 0);
                return (true);
            }
        });
    }

    //*** Sets Drag Event Listener ***/
    public void setDragListener(final View view) {
        /***(1) - This is Listening To The View That You Want To Target At When You Drop It At The Location
         EXAMPLE: Button Is Being Dragged To The TextView ***/
        txtViewQuestion.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View view, DragEvent dragEvent) {
                final View targetView = (View) dragEvent.getLocalState();
                switch (dragEvent.getAction()) {
                    case (DragEvent.ACTION_DRAG_STARTED):
                        /***(2) - Checks If The Drag Event Has Started***/
                        Toast.makeText(LinearFunctionsActivity.this, "Drag Even't Has Started", Toast.LENGTH_SHORT).show();
                        break;

                    case (DragEvent.ACTION_DRAG_ENTERED):
                        // Do Something Here
                        //(1) If They Drag 1 Button Do This ↓
                        if (targetView.getId() == R.id.btnToDrag) {
                            question = txtViewQuestion.getText().toString();
                            viewData1 = btnDragMe.getText().toString().trim();
                            if (viewData1.equals(question)) {
                                Toast.makeText(LinearFunctionsActivity.this, "You Are Correct", Toast.LENGTH_SHORT).show();

                            }
                            //(2) If They Drag 2 Button Do This ↓
                        } else if (targetView.getId() == R.id.btnToDrag2) {
                            question = txtViewQuestion.getText().toString();
                            viewData2 = btnDragMe2.getText().toString().trim();
                            if (viewData2.equals(question)) {
                                Toast.makeText(LinearFunctionsActivity.this, "You Are Wrong", Toast.LENGTH_SHORT).show();
                            }
                        }
                        break;
                }
                return true;
            }
        });

    }

}

