package com.example.personalfitnesstracker;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Workout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_workout);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ImageView back = findViewById(R.id.backbtn);
        back.setOnClickListener(v-> startActivity(new Intent(Workout.this, Home.class)));

        ImageView backBtn = findViewById(R.id.backbtn);
        Button startWorkoutButton = findViewById(R.id.startWorkoutButton);

        // Checkboxes
        CheckBox mondayCheckBox = findViewById(R.id.mondayCheckBox);
        CheckBox tuesdayCheckBox = findViewById(R.id.tuesdayCheckBox);
        CheckBox wednesdayCheckBox = findViewById(R.id.wednesdayCheckBox);
        CheckBox thursdayCheckBox = findViewById(R.id.thursdayCheckBox);
        CheckBox fridayCheckBox = findViewById(R.id.fridayCheckBox);
        CheckBox saturdayCheckBox = findViewById(R.id.saturdayCheckBox);
        CheckBox sundayCheckBox = findViewById(R.id.sundayCheckBox);

        // Handle back button click
        backBtn.setOnClickListener(v -> finish()); // Closes the activity

        // Handle Start Workout button click
        startWorkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder selectedDays = new StringBuilder("Selected days:\n");

                if (mondayCheckBox.isChecked()) selectedDays.append("Monday\n");
                if (tuesdayCheckBox.isChecked()) selectedDays.append("Tuesday\n");
                if (wednesdayCheckBox.isChecked()) selectedDays.append("Wednesday\n");
                if (thursdayCheckBox.isChecked()) selectedDays.append("Thursday\n");
                if (fridayCheckBox.isChecked()) selectedDays.append("Friday\n");
                if (saturdayCheckBox.isChecked()) selectedDays.append("Saturday\n");
                if (sundayCheckBox.isChecked()) selectedDays.append("Sunday\n");

                if (selectedDays.toString().equals("Selected days:\n")) {
                    Toast.makeText(Workout.this, "Please select at least one day!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Workout.this, selectedDays.toString(), Toast.LENGTH_LONG).show();

                }
            }
        });
    }
}