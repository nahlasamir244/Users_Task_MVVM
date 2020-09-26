package com.example.madarsoft_task.data.repo;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.madarsoft_task.data.model.User;
import com.example.madarsoft_task.data.repo.network.UserAPIProvider;
import com.example.madarsoft_task.data.repo.room.UserRoomProvider;

import java.util.List;

public class UserRepo {
    //provides the VM with data from data source online webservic or offline room
    private UserRoomProvider userRoomProvider;
    private UserAPIProvider userAPIProvider;

    public UserRepo(Context context) {
        userRoomProvider = new UserRoomProvider(context);
    }

    public LiveData<List<User>> getAll() {
        return userRoomProvider.getAll();
    }

    public LiveData<User> getByName(String name) {
        return userRoomProvider.getByName(name);
    }

    public void insert(User user) {
        userRoomProvider.insert(user);
    }

    public void update(User user) {
        userRoomProvider.update(user);
    }

    public void delete(User user) {
        userRoomProvider.delete(user);
    }

    public void deleteAll() {
        userRoomProvider.deleteAll();
    }
}
