package com.example.madarsoft_task.signleuserpreview;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.madarsoft_task.data.model.User;
import com.example.madarsoft_task.data.repo.UserRepo;

public class SingleUserPreviewViewModel extends AndroidViewModel {
    private UserRepo userRepo;
    private LiveData<User> user;
    public SingleUserPreviewViewModel(@NonNull Application application) {
        super(application);
        userRepo = new UserRepo(application);
    }
    public LiveData<User> getUser(String name){
        return userRepo.getByName(name);
    }
}
