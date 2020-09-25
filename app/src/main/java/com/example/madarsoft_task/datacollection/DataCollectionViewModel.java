package com.example.madarsoft_task.datacollection;

import android.app.Application;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import com.example.madarsoft_task.data.model.User;
import com.example.madarsoft_task.data.repo.UserRepo;
import com.example.madarsoft_task.usersPreview.UsersPreviewActivity;

public class DataCollectionViewModel extends AndroidViewModel {
    private UserRepo userRepo;
    public DataCollectionViewModel(@NonNull Application application) {
        super(application);
        userRepo = new UserRepo(application);
    }
    public void insertUser(User user){
        userRepo.insert(user);
    }
    public void displayUsers(){
        Intent intent = new Intent(getApplication(), UsersPreviewActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getApplication().startActivity(intent);
    }
}
