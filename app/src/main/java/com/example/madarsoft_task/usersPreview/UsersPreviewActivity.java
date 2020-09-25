package com.example.madarsoft_task.usersPreview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.madarsoft_task.R;
import com.example.madarsoft_task.adapters.UsersAdapter;
import com.example.madarsoft_task.data.model.User;

import java.util.List;

public class UsersPreviewActivity extends AppCompatActivity {
    private UsersPreviewViewModel usersPreviewViewModel;
    private RecyclerView usersRecyclerView;
    private UsersAdapter usersAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_preview);
        initViews();
        initObservers();
    }
    private void initViews(){
        usersRecyclerView= findViewById(R.id.usersRecyclerView);
        usersRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        usersRecyclerView.hasFixedSize();
        usersAdapter = new UsersAdapter();
        usersRecyclerView.setAdapter(usersAdapter);
    }

    private void initObservers(){
        usersPreviewViewModel= new ViewModelProvider(this).get(UsersPreviewViewModel.class);
        usersPreviewViewModel.getUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                usersAdapter.setUsers(users);
                Log.i("Users", "onChanged: ");
            }
        });
    }

}