package com.example.personalfitnesstracker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Profile extends AppCompatActivity {
    EditText inputField1, inputField3, inputField4, inputField5;
    Button myButton;
    TextView nametext;
    RadioGroup radioGroupGender;
    RadioButton radioButtonMale, radioButtonFemale;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);

        nametext = findViewById(R.id.nameText);
        inputField1 = findViewById(R.id.editTextEmail);
        inputField3 = findViewById(R.id.editTextAge);
        inputField4 = findViewById(R.id.editTextWeight);
        inputField5 = findViewById(R.id.editTextHeight);

        radioGroupGender = findViewById(R.id.radioGroupGender);
        radioButtonMale = findViewById(R.id.radioMale);
        radioButtonFemale = findViewById(R.id.radioFemale);

        // Retrieve stored values from SharedPreferences
        SharedPreferences sharedpreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String storedUsername = sharedpreferences.getString("username", "");
        String storedEmail = sharedpreferences.getString("email", "");
        String storedGender = sharedpreferences.getString("gender", "");
        String storedAge = sharedpreferences.getString("age", "");
        String storedWeight = sharedpreferences.getString("Weight", "");
        String storedHeight = sharedpreferences.getString("Height", "");

        // Set the retrieved values in the views
        nametext.setText(storedUsername);
        inputField1.setText(storedEmail);
        inputField3.setText(storedAge);
        inputField4.setText(storedWeight);
        inputField5.setText(storedHeight);

        // Set the gender radio button selection based on the stored value
        if (storedGender.equals("Male")) {
            radioButtonMale.setChecked(true);
        } else if (storedGender.equals("Female")) {
            radioButtonFemale.setChecked(true);
        }

        // ImageView back button
        ImageView back = findViewById(R.id.backbtn);
        back.setOnClickListener(view -> startActivity(new Intent(Profile.this, Home.class)));

        myButton = findViewById(R.id.editProfileButton);
        myButton.setEnabled(false);  // Initially disable the button

        // TextWatcher to check if all the fields are filled
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable editable) {
                checkFields();  // Check if the fields are properly filled
            }
        };

        // Add TextWatchers to input fields
        inputField1.addTextChangedListener(textWatcher);
        inputField3.addTextChangedListener(textWatcher);
        inputField4.addTextChangedListener(textWatcher);
        inputField5.addTextChangedListener(textWatcher);

        // Handle the button click
        myButton.setOnClickListener(v -> {
            String email = inputField1.getText().toString();
            String age = inputField3.getText().toString();
            String weight = inputField4.getText().toString();
            String height = inputField5.getText().toString();

            // Get the selected gender from RadioButtons
            String gender = "select"; // Default value
            int selectedGenderId = radioGroupGender.getCheckedRadioButtonId();
            if (selectedGenderId == radioButtonMale.getId()) {
                gender = "Male";
            } else if (selectedGenderId == radioButtonFemale.getId()) {
                gender = "Female";
            }

            // Save updated values in SharedPreferences
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString("email", email);
            editor.putString("gender", gender);
            editor.putString("age", age);
            editor.putString("Weight", weight);
            editor.putString("Height", height);
            editor.apply();

            // Show confirmation toast
            Toast.makeText(Profile.this, "Profile Updated", Toast.LENGTH_SHORT).show();
        });
    }

    // Method to check if all fields are filled and enable the button
    private void checkFields() {
        // Check if all fields are filled and gender is selected
        if (!inputField1.getText().toString().trim().isEmpty() &&
                radioGroupGender.getCheckedRadioButtonId() != -1 &&
                !inputField3.getText().toString().trim().isEmpty() &&
                !inputField4.getText().toString().trim().isEmpty() &&
                !inputField5.getText().toString().trim().isEmpty()) {
            myButton.setEnabled(true);  // Enable the button
        } else {
            myButton.setEnabled(false); // Disable the button
        }
    }
}
