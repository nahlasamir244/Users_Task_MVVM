package com.example.madarsoft_task.usersPreview;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.madarsoft_task.data.model.User;
import com.example.madarsoft_task.data.repo.UserRepo;

import java.util.List;

public class UsersPreviewViewModel extends AndroidViewModel {
    private UserRepo userRepo;
    private LiveData<List<User>> users;
    public UsersPreviewViewModel(@NonNull Application application) {
        super(application);
        userRepo = new UserRepo(application);
    }
    public LiveData<List<User>> getUsers(){
        users = userRepo.getAll();
        return users;
    }
    public void deleteUsers(){
        userRepo.deleteAll();
    }
    public void deleteUser(User user){
        userRepo.delete(user);
    }
}
