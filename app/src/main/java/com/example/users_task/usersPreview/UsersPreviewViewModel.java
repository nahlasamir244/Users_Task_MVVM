package com.example.users_task.usersPreview;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.users_task.data.model.User;
import com.example.users_task.data.repo.UserRepo;
import com.example.users_task.signleuserpreview.SingleUserPreviewActivity;

import java.util.List;

public class UsersPreviewViewModel extends AndroidViewModel {
    private UserRepo userRepo;
    private LiveData<List<User>> users;

    public UsersPreviewViewModel(@NonNull Application application) {
        super(application);
        userRepo = new UserRepo(application);
    }

    public LiveData<List<User>> getUsers() {
        users = userRepo.getAll();
        return users;
    }

    public void deleteUsers() {
        userRepo.deleteAll();
    }

    public void deleteUser(User user) {
        userRepo.delete(user);
    }

    public void insertUser(User user) {
        userRepo.insert(user);
    }

    public void viewUser(User swipedUser) {
        Intent intent = new Intent(getApplication(),
                SingleUserPreviewActivity.class);
        intent.putExtra("name", swipedUser.getName());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getApplication().startActivity(intent);
        Log.i("swiped right", "onSwiped: view user");
    }
}
