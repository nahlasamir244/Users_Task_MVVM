package com.example.madarsoft_task.data.repo.room;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.madarsoft_task.data.model.User;

import java.util.List;

public class UserRoomProvider {
    private UserDAO userDAO;
    private LiveData<List<User>> users;
    private LiveData<User> user;
    public UserRoomProvider(Context context){
    UserDatabase userDatabase = UserDatabase.getInstance(context);
    userDAO = userDatabase.userDAO();
    }

    public LiveData<List<User>> getAll() {
        users = userDAO.getAll();
        return users;
    }
    public LiveData<User> getByName(String name){
        user = userDAO.getByName(name);
        return user;
    }
    public void insert(User user){
        new InsertUserAsyncTask(userDAO).execute(user);
    }
    public void update(User user){
        new UpdateUserAsyncTask(userDAO).execute(user);
    }
    public void delete(User user){
        new DeleteUserAsyncTask(userDAO).execute(user);
    }
    public void deleteAll(){
        new DeleteAllUsersAsyncTask(userDAO).execute();
    }

    private static class InsertUserAsyncTask extends AsyncTask<User,Void,Void> {
        private UserDAO userDAO;

        private InsertUserAsyncTask(UserDAO userDAO) {
            this.userDAO = userDAO;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDAO.insert(users[0]);
            return null;
        }
    }

    private static class UpdateUserAsyncTask extends AsyncTask<User,Void,Void> {
        private UserDAO userDAO;

        private UpdateUserAsyncTask(UserDAO userDAO) {
            this.userDAO = userDAO;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDAO.update(users[0]);
            return null;
        }
    }

    private static class DeleteUserAsyncTask extends AsyncTask<User,Void,Void> {
        private UserDAO userDAO;

        private DeleteUserAsyncTask(UserDAO userDAO) {
            this.userDAO = userDAO;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDAO.delete(users[0]);
            return null;
        }
    }
    private static class DeleteAllUsersAsyncTask extends AsyncTask<Void,Void,Void> {
        private UserDAO userDAO;

        private DeleteAllUsersAsyncTask(UserDAO userDAO) {
            this.userDAO = userDAO;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            userDAO.deleteAll();
            return null;
        }
    }
}
