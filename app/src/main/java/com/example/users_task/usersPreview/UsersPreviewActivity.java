package com.example.users_task.usersPreview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.example.users_task.utils.Helper;
import com.example.users_task.R;
import com.example.users_task.adapters.UsersAdapter;
import com.example.users_task.data.model.User;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class UsersPreviewActivity extends AppCompatActivity {
    private UsersPreviewViewModel usersPreviewViewModel;
    private RecyclerView usersRecyclerView;
    private UsersAdapter usersAdapter;
    private Helper helper = new Helper();
    private LinearLayout noData;
    private User swipedUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_preview);
        initViews();
    }

    @Override
    protected void onStart() {
        super.onStart();
        initObservers();
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

    private void initViews() {
        noData = findViewById(R.id.noDataLayout);
        usersRecyclerView = findViewById(R.id.usersRecyclerView);
        usersRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        usersRecyclerView.hasFixedSize();
        usersAdapter = new UsersAdapter();
        usersRecyclerView.setAdapter(usersAdapter);
        UserTouchHelper userTouchHelper = new UserTouchHelper(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(userTouchHelper);
        itemTouchhelper.attachToRecyclerView(usersRecyclerView);
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


    private void deleteUsers() {
        if (usersAdapter.getItemCount() > 0) {
            helper.showConfirmDeleteAlert(R.string.confirmationTitle,
                    usersAdapter.getItemCount() + " " + getResources().getString(R.string.willBeDeleted),
                    this, null, usersPreviewViewModel);
        } else {
            helper.showOkDialogue(R.string.error, R.string.noData, this);
        }
    }

    private void deleteUser() {
        usersPreviewViewModel.deleteUser(swipedUser);
        Snackbar.make(usersRecyclerView, R.string.confirmationTitle, Snackbar.LENGTH_SHORT)
                .setAction(R.string.undo, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.i("undo", "onSwiped: ");
                        noData.setVisibility(View.GONE);
                        usersPreviewViewModel.insertUser(swipedUser);
                    }
                }).show();
    }
    private void viewUser() {
        usersPreviewViewModel.viewUser(swipedUser);
    }

    class UserTouchHelper extends ItemTouchHelper.SimpleCallback {

        public UserTouchHelper(int dragDirections, int swipeDirections) {
            super(dragDirections, swipeDirections);
        }

        @Override
        public void onChildDraw(@NonNull Canvas canvas, @NonNull RecyclerView recyclerView,
                                @NonNull RecyclerView.ViewHolder viewHolder, float directionX, float directionY,
                                int actionState, boolean isCurrentlyActive) {
            new RecyclerViewSwipeDecorator.Builder(canvas,
                    recyclerView, viewHolder, directionX, directionY, actionState, isCurrentlyActive)
                    .addSwipeLeftBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white))
                    .addSwipeLeftActionIcon(R.drawable.ic_delete)
                    .addSwipeRightBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white))
                    .addSwipeRightActionIcon(R.drawable.ic_show)
                    .create()
                    .decorate();
            super.onChildDraw(canvas, recyclerView, viewHolder, directionX, directionY, actionState,
                    isCurrentlyActive);
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            swipedUser = usersAdapter.getUsers().get(viewHolder.getAdapterPosition());
            switch (direction) {
                case ItemTouchHelper.LEFT: deleteUser();
                    break;
                case ItemTouchHelper.RIGHT: viewUser();
                    break;
            }
        }

        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder,
                              @NonNull RecyclerView.ViewHolder target) {
            return false;
        }
    }



}