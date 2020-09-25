package com.example.madarsoft_task.data.repo.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.madarsoft_task.data.model.User;

@Database(entities = {User.class},version = 1)
public abstract class UserDatabase extends RoomDatabase {
    private static UserDatabase instance;
    public  abstract UserDAO userDAO();
    public static synchronized UserDatabase getInstance(Context context){
        if(instance ==null){
            instance= Room.databaseBuilder(context.getApplicationContext(),
                    UserDatabase.class, "users_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
