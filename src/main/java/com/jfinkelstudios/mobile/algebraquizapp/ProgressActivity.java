package com.jfinkelstudios.mobile.algebraquizapp;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import Quiz.DataSaver;

public class ProgressActivity extends AppCompatActivity {
    //*******>
    private BarChart barChart;
    private BarData barData;
    private BarDataSet barDataSet;
    private ArrayList<BarEntry> barEntries;
    // Score Keeper
    private DataSaver dataSaver;
    private int data = 1;

    //*******>
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);
        // FIND VIEW BY'ID
        barChart = findViewById(R.id.BarChart);
        // SCORE KEEPER
        dataSaver = new DataSaver(ProgressActivity.this);
        dataSaver.saveData(data);
        // GETS THE ENTRY
        getEntries();
        // SETS THE BAR DATA SET
        barDataSet = new BarDataSet(barEntries, "");
        barData = new BarData(barDataSet);
        // SETS THE CHART
        barChart.setData(barData);
        barDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        barDataSet.setValueTextSize(20f);
        barDataSet.setValueTextColor(Color.WHITE);

    }

    private void getEntries() {
        barEntries = new ArrayList<>();
        // * DAILY STREAKS *
        barEntries.add(new BarEntry(1f, dataSaver.loadData()));
        // * Daily Study Time Spent *
        barEntries.add(new BarEntry(2f, 2));
        // * # Of Quiz Completed *
        barEntries.add(new BarEntry(3f, 3));

    }


}// END OF CLASS
