/*
 * Software Developer: Denis J Finkel
 * Project Goal: Create A Functional Algebra Quiz App
 */
package com.jfinkelstudios.mobile.algebraquizapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.navigation.NavigationView;

import Quiz.NotificationAlarm;

public class MainActivity extends AppCompatActivity {
    //*****> CLASS INSTANCE VARIABLES
    private static final int NOTIFICATION_ID = 0;
    // * Alarm Time Config *
    private static final long ALARM_REPEATED_INTERVAL = AlarmManager.INTERVAL_DAY;
    private static final long TRIGGER_TIME = SystemClock.elapsedRealtime() + (ALARM_REPEATED_INTERVAL);
    private static final String INFO = "INFO:";
    private static final String ALARM_ACTIVITY_DEBUG_INFO = "Alarm Has Started";


    //*****>
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /***FIND VIEW BY ID'S***/
        final NavigationView NAVIGATION_VIEW = findViewById(R.id.mainActivityNavigationView);
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

        }// END OF CREATE

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


    }// END OF ON CREATE

    // STARTS THE ProgressActivity
    public void btn_ImgViewProgress(View view) {
        startActivity(new Intent(MainActivity.this, ProgressActivity.class));
    }


    //

}// END OF CLASS
