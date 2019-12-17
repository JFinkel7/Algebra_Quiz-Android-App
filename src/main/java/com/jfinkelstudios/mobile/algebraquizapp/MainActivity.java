/*
 * Software Developer: Denis J Finkel
 * Project Goal: Create A Functional Algebra Quiz App
 */
package com.jfinkelstudios.mobile.algebraquizapp;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import Quiz.NotificationAlarm;

public class MainActivity extends AppCompatActivity {
    //*****> CLASS INSTANCE VARIABLES
    private static final int NOTIFICATION_ID = 0;
    // * Alarm Time Config *
    private static final long ALARM_REPEATED_INTERVAL = AlarmManager.INTERVAL_FIFTEEN_MINUTES;
    private static final long TRIGGER_TIME = SystemClock.elapsedRealtime() + ALARM_REPEATED_INTERVAL;

    //*****>
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /***FIND VIEW BY ID'S***/
        final DrawerLayout DRAWER_LAYOUT = findViewById(R.id.mainActivityDrawerLayout);
        final NavigationView NAVIGATION_VIEW = findViewById(R.id.mainActivityNavigationView);

        /***TOGGLE BUTTON SWITCH CONFIG***/
        ToggleButton alarmSwitch = findViewById(R.id.switchAlarm);
        alarmSwitch.setChecked(startAlarm());

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

    private boolean startAlarm() {
        //*
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        /***ALARM MANGER CONFIGURATION***/
        // * (1) Set up the broadcast pending intent *
        final Intent NOTIFY_INTENT = new Intent(MainActivity.this, NotificationAlarm.class);


        // * (2) Alarm INTENT Config *
        final PendingIntent NOTIFY_PENDING_INTENT = PendingIntent.getBroadcast(this, NOTIFICATION_ID, NOTIFY_INTENT, PendingIntent.FLAG_UPDATE_CURRENT);
        final AlarmManager ALARM_MANAGER = (AlarmManager) getSystemService(ALARM_SERVICE);

        // * (3) If The Toggle Is Turned On, Set The Repeating Alarm With A 15 Minute Interval *
        if (ALARM_MANAGER != null) {
            ALARM_MANAGER.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, TRIGGER_TIME, ALARM_REPEATED_INTERVAL, NOTIFY_PENDING_INTENT);
            Toast.makeText(this, "Alarm set in", Toast.LENGTH_LONG).show();
        } else {
            if (ALARM_MANAGER != null) {
                ALARM_MANAGER.cancel(NOTIFY_PENDING_INTENT);
            }
        }
        return (true);
    }


}// END OF CLASS
