/*
 * Software Developer: Denis J Finkel
 * Project Goal: Create A Functional Algebra Quiz App
 */
package com.jfinkelstudios.mobile.algebraquizapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.material.navigation.NavigationView;

import Quiz.NotificationAlarm;

public class MainActivity extends AppCompatActivity {
    //*****> CLASS INSTANCE VARIABLES
    private static final int NOTIFICATION_ID = 0;
    // * Alarm Time Config *
    private static final long ALARM_REPEATED_INTERVAL = AlarmManager.INTERVAL_DAY;
    private static final long TRIGGER_TIME = SystemClock.elapsedRealtime() + (AlarmManager.INTERVAL_FIFTEEN_MINUTES);
    private static final String INFO = "INFO:";
    private static final String ALARM_ACTIVITY_DEBUG_INFO = "Alarm Has Started";
    protected static boolean nightModeResult;


    //*****>
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /***FIND VIEW BY ID'S***/
        final NavigationView NAVIGATION_VIEW = findViewById(R.id.mainActivityNavigationView);
        final GridLayout GRID_LAYOUT = findViewById(R.id.mainActivityGridLayout);
        final CardView CARD_VIEW_1 = findViewById(R.id.cardView1);
        final CardView CARD_VIEW_2 = findViewById(R.id.cardView2);
        final CardView CARD_VIEW_3 = findViewById(R.id.cardView3);
        final CardView CARD_VIEW_4 = findViewById(R.id.cardView4);
        /***STARTS THE ALARM TO REMIND USER TO STUDY DAILY***/
        /* ALARM MANGER CONFIGURATION */
        final AlarmManager ALARM_MANAGER = (AlarmManager) getSystemService(ALARM_SERVICE);

        /* INTENT & PENDING CONFIGURATION */
        final Intent NOTIFY_INTENT = new Intent(MainActivity.this, NotificationAlarm.class);
        final PendingIntent NOTIFY_PENDING_INTENT = PendingIntent.getBroadcast(this, NOTIFICATION_ID, NOTIFY_INTENT, PendingIntent.FLAG_UPDATE_CURRENT);

        /* SETS THE TIME INITIATION CONFIGURATION */
        // *  Set The Repeating Alarm In 5secs With A 1 Day Interval *
        if (ALARM_MANAGER != null) {
            ALARM_MANAGER.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, TRIGGER_TIME, ALARM_REPEATED_INTERVAL, NOTIFY_PENDING_INTENT);
            Log.i(INFO, ALARM_ACTIVITY_DEBUG_INFO);
        } else {
            if (ALARM_MANAGER != null) {
                ALARM_MANAGER.cancel(NOTIFY_PENDING_INTENT);
            }
        }

        /***NAVIGATION_VIEW ITEM SELECTION***/
        NAVIGATION_VIEW.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    // * (Starts FUNCTIONS_2_3 ACTIVITY) *
                    case (R.id.menuFunctions_2_3):
                        startActivity(new Intent(MainActivity.this, Functions2_3Activity.class));
                        break;

                    // * (Starts LinearModels ACTIVITY) *
                    case (R.id.menuLinearModels):
                        startActivity(new Intent(MainActivity.this, LinearModelsActivity.class));
                        break;

                    // * (Starts EquationLines ACTIVITY) *
                    case (R.id.menuEquationLines):
                        startActivity(new Intent(MainActivity.this, EquationLinesActivity.class));
                        break;
                }
                return (false);
            }
        });

        // GETS BOOLEAN VALUE FROM SETTINGS ACTIVITY
        nightModeResult = getIntent().getBooleanExtra("background_change", false);
        if (nightModeResult) {
            GRID_LAYOUT.setBackgroundColor(Color.BLACK);
            CARD_VIEW_1.setCardBackgroundColor(Color.WHITE);
            CARD_VIEW_2.setCardBackgroundColor(Color.WHITE);
            CARD_VIEW_3.setCardBackgroundColor(Color.WHITE);
            CARD_VIEW_4.setCardBackgroundColor(Color.WHITE);
        }
    }// END OF ON CREATE

    // STARTS THE ProgressActivity
    public void btn_ProgressActivity(View view) {
        startActivity(new Intent(MainActivity.this, ProgressActivity.class));
    }

    public void btn_SettingsActivity(View view) {
        startActivity(new Intent(MainActivity.this, SettingsActivity.class));
    }


}// END OF CLASS
