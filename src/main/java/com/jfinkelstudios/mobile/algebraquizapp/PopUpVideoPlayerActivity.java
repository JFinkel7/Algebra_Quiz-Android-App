package com.jfinkelstudios.mobile.algebraquizapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PopUpVideoPlayerActivity extends AppCompatActivity {



    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up_video_player);
        // (1) Sets The Metrics For The Activity
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        // (2) SET WIDTH AND HEIGHT OF THE POP UP
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        getWindow().setLayout((int) (width * .8), (int) (height * .6));
        // (3) SETS THE PARAMETERS OF THE ACTIVITY
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -20;
        getWindow().setAttributes(params);
        // GETTING RESOURCE FROM THE OTHER ACTIVITY


        //
        // VIDEO VIEW
        VideoView videoView = findViewById(R.id.VideoView);//     GETTING VALUE FROM THE INTENT ↓
        String videoPath = "android.resource://" + getPackageName() + "/" + getIntent().getIntExtra("videoID", -1);

        Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);
        // MEDIA CONTROLLER
        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);

    }// END OF CREATE




}//END OF CLASS
