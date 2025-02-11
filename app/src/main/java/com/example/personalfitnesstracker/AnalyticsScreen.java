package com.example.personalfitnesstracker;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.Random;

public class AnalyticsScreen extends AppCompatActivity {
    private GraphView graphView, graphViewWorkout;
    private LineGraphSeries<DataPoint> series, seriesWorkout;
    private ArrayList<StepData> stepDataList;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_analytics_screen);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageView backbtn = findViewById(R.id.backbtn);
        backbtn.setOnClickListener(v -> startActivity(new Intent(AnalyticsScreen.this, Home.class)));

        graphView = findViewById(R.id.graphView);
        graphViewWorkout = findViewById(R.id.graphViewworkout);
        setupGraphs();
        loadDummyData();
    }

    private void setupGraphs() {
        // Step Count Graph
        series = new LineGraphSeries<>();
        graphView.addSeries(series);

        series.setColor(Color.RED); // Change color to red for steps graph

        // Customize Step Count Graph appearance
        graphView.getViewport().setXAxisBoundsManual(true);
        graphView.getViewport().setMinX(0);
        graphView.getViewport().setMaxX(7); // Show a week of data
        graphView.getViewport().setScrollable(true);

        // Labels for Step Count Graph
        graphView.getGridLabelRenderer().setHorizontalAxisTitle("Days");
        graphView.getGridLabelRenderer().setVerticalAxisTitle("Steps");
        graphView.getGridLabelRenderer().setHorizontalAxisTitleColor(Color.YELLOW);
        graphView.getGridLabelRenderer().setVerticalAxisTitleColor(Color.YELLOW);
        graphView.getGridLabelRenderer().setHorizontalLabelsColor(Color.WHITE);
        graphView.getGridLabelRenderer().setVerticalLabelsColor(Color.WHITE);
        graphView.getGridLabelRenderer().setGridColor(Color.GRAY);

        // Workout Graph
        seriesWorkout = new LineGraphSeries<>();
        graphViewWorkout.addSeries(seriesWorkout);

        seriesWorkout.setColor(Color.RED); // Change color to blue for workout graph

        // Customize Workout Graph appearance
        graphViewWorkout.getViewport().setXAxisBoundsManual(true);
        graphViewWorkout.getViewport().setMinX(0);
        graphViewWorkout.getViewport().setMaxX(7); // Show a week of data
        graphViewWorkout.getViewport().setScrollable(true);

        // Labels for Workout Graph
        graphViewWorkout.getGridLabelRenderer().setHorizontalAxisTitle("Days");
        graphViewWorkout.getGridLabelRenderer().setVerticalAxisTitle("Workout (min)");
        graphViewWorkout.getGridLabelRenderer().setHorizontalAxisTitleColor(Color.YELLOW);
        graphViewWorkout.getGridLabelRenderer().setVerticalAxisTitleColor(Color.YELLOW);
        graphViewWorkout.getGridLabelRenderer().setHorizontalLabelsColor(Color.WHITE);
        graphViewWorkout.getGridLabelRenderer().setVerticalLabelsColor(Color.WHITE);
        graphViewWorkout.getGridLabelRenderer().setGridColor(Color.GRAY);
    }

    private void loadDummyData() {
        stepDataList = new ArrayList<>();

        // Add dummy data points for the step graph
        for (int i = 0; i < 7; i++) {
            series.appendData(
                    new DataPoint(i, new Random().nextInt(10000)),
                    true,
                    7
            );
        }

        // Add dummy data points for the workout graph
        for (int i = 0; i < 7; i++) {
            seriesWorkout.appendData(
                    new DataPoint(i, new Random().nextInt(120)), // Random workout time (in minutes)
                    true,
                    7
            );
        }
    }
}
