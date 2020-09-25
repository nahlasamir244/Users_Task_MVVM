package com.example.madarsoft_task.signleuserpreview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.madarsoft_task.R;

public class SingleUserPreviewActivity extends AppCompatActivity {
    private SingleUserPreviewViewModel singleUserPreviewViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_user_preview);
    }
}