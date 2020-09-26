package com.example.madarsoft_task.signleuserpreview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.madarsoft_task.R;
import com.example.madarsoft_task.data.model.User;
import com.example.madarsoft_task.usersPreview.UsersPreviewViewModel;

import java.util.List;

public class SingleUserPreviewActivity extends AppCompatActivity {
    private SingleUserPreviewViewModel singleUserPreviewViewModel;
    private TextView userNameTextView, userAgeTextView , userJobTextView, userGenderTextView;
    String userName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_user_preview);
        initViews();
        initDataAndObservers();
    }

    private void initViews() {
        userNameTextView = findViewById(R.id.userName);
        userAgeTextView = findViewById(R.id.userAge);
        userJobTextView = findViewById(R.id.userJob);
        userGenderTextView = findViewById(R.id.userGender);
    }

    private void initDataAndObservers() {
        userName = getIntent().getStringExtra("name");
        singleUserPreviewViewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication()))
                .get(SingleUserPreviewViewModel.class);
        singleUserPreviewViewModel.getUser(userName).observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                userNameTextView.setText(user.getName());
                userAgeTextView.setText(String.valueOf(user.getAge()));
                userJobTextView.setText(user.getJobTitle());
                userGenderTextView.setText(user.getGender()==1?"male":"female");
            }
        });
    }
}