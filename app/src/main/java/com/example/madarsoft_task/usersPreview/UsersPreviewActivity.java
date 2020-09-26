package com.example.madarsoft_task.usersPreview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.example.madarsoft_task.Helper;
import com.example.madarsoft_task.R;
import com.example.madarsoft_task.adapters.UsersAdapter;
import com.example.madarsoft_task.data.model.User;

import java.util.List;

public class UsersPreviewActivity extends AppCompatActivity {
    private UsersPreviewViewModel usersPreviewViewModel;
    private RecyclerView usersRecyclerView;
    private UsersAdapter usersAdapter;
    private Helper helper = new Helper();
    private LinearLayout noData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_preview);
        initViews();
        initObservers();
    }

    private void initViews() {
        noData = findViewById(R.id.noDataLayout);
        usersRecyclerView = findViewById(R.id.usersRecyclerView);
        usersRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        usersRecyclerView.hasFixedSize();
        usersAdapter = new UsersAdapter();
        usersRecyclerView.setAdapter(usersAdapter);

    }

    private void initObservers() {
        usersPreviewViewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication()))
                .get(UsersPreviewViewModel.class);
        usersPreviewViewModel.getUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                usersAdapter.setUsers(users);
                if (usersAdapter.getItemCount() == 0) {
                    noData.setVisibility(View.VISIBLE);
                }
                Log.i("Users", "onChanged: ");
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.users_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_all_users:
                deleteUsers();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void deleteUsers() {
        if (usersAdapter.getItemCount() > 0) {
            helper.showConfirmDeleteAlert(R.string.confirmationTitle,
                    usersAdapter.getItemCount() + " " + getResources().getString(R.string.willBeDeleted),
                    this, null, usersPreviewViewModel);
        } else {
            helper.showOkDialogue(R.string.error, R.string.noData, this);
        }
    }
}