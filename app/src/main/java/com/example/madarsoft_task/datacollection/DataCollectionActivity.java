package com.example.madarsoft_task.datacollection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.example.madarsoft_task.R;

public class DataCollectionActivity extends AppCompatActivity {
    private DataCollectionViewModel dataCollectionViewModel;
    private EditText nameEditText,  jobTitleEditText;
    private NumberPicker ageNumberPicker;
    private RadioGroup genderRadioGroup;
    private RadioButton maleRadioButton,femaleRadioButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_collection);
        initViews();
        initActions();
    }
    private void initViews(){
        dataCollectionViewModel= new ViewModelProvider(this).get(DataCollectionViewModel.class);
        nameEditText = findViewById(R.id.userName);
        ageNumberPicker = findViewById(R.id.userAge);
        ageNumberPicker.setMinValue(10);
        ageNumberPicker.setMaxValue(100);
        jobTitleEditText = findViewById(R.id.userJobTitle);
        genderRadioGroup = findViewById(R.id.gender_radio_group);
        maleRadioButton = findViewById(R.id.male_radio);
        femaleRadioButton = findViewById(R.id.female_radio);
    }
    private void initActions(){

    }
}