package com.example.madarsoft_task.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.madarsoft_task.R;
import com.example.madarsoft_task.data.model.User;

import java.util.ArrayList;
import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UsersViewHolder> {
    List<User> users = new ArrayList<>();
    @NonNull
    @Override
    public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_user_row, parent, false);
        return new UsersViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersViewHolder holder, int position) {
            User currentUser = users.get(position);
            holder.nameTextView.setText(currentUser.getName());
            holder.jobTextView.setText(currentUser.getJobTitle());
    }
    public void setUsers(List<User> users){
        this.users=users;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class UsersViewHolder extends RecyclerView.ViewHolder{
        private TextView nameTextView;
        private  TextView jobTextView;

        public UsersViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.textViewName);
            jobTextView = itemView.findViewById(R.id.textViewJob);
        }
    }
}
