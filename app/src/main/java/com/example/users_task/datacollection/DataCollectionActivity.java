package com.example.users_task.datacollection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.users_task.R;
import com.example.users_task.data.model.User;

public class DataCollectionActivity extends AppCompatActivity {
    private DataCollectionViewModel dataCollectionViewModel;
    private EditText nameEditText, jobTitleEditText;
    private NumberPicker ageNumberPicker;
    private RadioGroup genderRadioGroup;
    private RadioButton maleRadioButton, femaleRadioButton;
    private Button saveButton, displayAllButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_collection);
        initViews();
        initActions();
    }

    private void initViews() {
        dataCollectionViewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication()))
                .get(DataCollectionViewModel.class);
        nameEditText = findViewById(R.id.userName);
        ageNumberPicker = findViewById(R.id.userAge);
        ageNumberPicker.setMinValue(10);
        ageNumberPicker.setMaxValue(100);
        jobTitleEditText = findViewById(R.id.userJobTitle);
        genderRadioGroup = findViewById(R.id.gender_radio_group);
        maleRadioButton = findViewById(R.id.male_radio);
        femaleRadioButton = findViewById(R.id.female_radio);
        saveButton = findViewById(R.id.save);
        displayAllButton = findViewById(R.id.displayAll);
    }

    private void initActions() {
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = nameEditText.getText().toString();
                int userAge = ageNumberPicker.getValue();
                String userJobTitle = jobTitleEditText.getText().toString();
                int userGender = (maleRadioButton.isChecked()) ? 1 : 2;
                if (userName.trim().isEmpty() || userJobTitle.trim().isEmpty()) {
                    Toast.makeText(getApplicationContext(), R.string.insertCorrectValues, Toast.LENGTH_SHORT).show();
                } else {
                    User user = new User(userName, userAge, userJobTitle, userGender);
                    saveUser(user);
                    resetFields();
                }
            }
        });

        displayAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showUsers();
            }
        });
    }

    private void resetFields() {
        nameEditText.setText("");
        jobTitleEditText.setText("");
        ageNumberPicker.setValue(10);
        maleRadioButton.setChecked(true);
    }

    private void saveUser(User user) {
        dataCollectionViewModel.insertUser(user);
        Toast.makeText(this, R.string.userAdded, Toast.LENGTH_SHORT).show();
    }

    private void showUsers() {
        dataCollectionViewModel.displayUsers();
    }
}