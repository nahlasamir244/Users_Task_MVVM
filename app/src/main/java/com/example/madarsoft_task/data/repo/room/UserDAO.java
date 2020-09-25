package com.example.madarsoft_task.data.repo.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.madarsoft_task.data.model.User;

import java.util.List;

@Dao
public interface UserDAO {
    @Insert
    void insert(User user);
    @Update
    void update(User user);
    @Delete
    void delete(User user);
    @Query("DELETE FROM user_table")
    void deleteAll();
    @Query("SELECT * FROM user_table WHERE name = :name LIMIT 1")
    LiveData<User> getByName(String name);
    @Query("SELECT * FROM user_table")
    LiveData<List<User>> getAll();

}
