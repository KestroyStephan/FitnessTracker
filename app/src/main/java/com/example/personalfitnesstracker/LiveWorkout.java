package com.example.personalfitnesstracker;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

public class LiveWorkout extends AppCompatActivity {
    private TextView timerTextView;
    private Button startButton, resetButton;
    private CountDownTimer workoutTimer;
    private boolean isTimerRunning = false;
    private long timeLeftInMillis = 60 * 60 * 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_live_workout);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        timerTextView = findViewById(R.id.timerTextView);
        startButton = findViewById(R.id.startButton);
        resetButton = findViewById(R.id.resetButton);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ImageView backbtn = findViewById(R.id.backbtn);
        backbtn.setOnClickListener(v->startActivity(new Intent(LiveWorkout.this, Home.class)));
        startButton.setOnClickListener(v -> {
            if (isTimerRunning) {
                pauseTimer();
            } else {
                startTimer();
            }
        });
        resetButton.setOnClickListener(v -> resetTimer());
        updateCountDownText();
    }
    private void startTimer() {
        workoutTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                isTimerRunning = false;
                startButton.setText("Start");
                timeLeftInMillis = 0;
                updateCountDownText();
            }
        }.start();

        isTimerRunning = true;
        startButton.setText("Pause");
    }

    private void pauseTimer() {
        workoutTimer.cancel();
        isTimerRunning = false;
        startButton.setText("Start");
    }

    private void resetTimer() {
        timeLeftInMillis = 60 * 60 * 1000;
        updateCountDownText();
        if (isTimerRunning) {
            workoutTimer.cancel();
            isTimerRunning = false;
            startButton.setText("Start");
        }
    }

    private void updateCountDownText() {
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;
        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        timerTextView.setText(timeLeftFormatted);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (workoutTimer != null) {
            workoutTimer.cancel();
        }
    }
}