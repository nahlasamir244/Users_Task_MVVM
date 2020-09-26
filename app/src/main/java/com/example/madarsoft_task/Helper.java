package com.example.madarsoft_task;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.example.madarsoft_task.data.model.User;
import com.example.madarsoft_task.usersPreview.UsersPreviewViewModel;

public class Helper {
    public void showConfirmDeleteAlert(int title, String message, final Context context, final User user, final UsersPreviewViewModel viewModel) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View alertView = layoutInflater.inflate(R.layout.dialogue_yes_no, null);
        builder.setView(alertView);
        TextView alertTitle = alertView.findViewById(R.id.alertTitle);
        TextView alertMessage = alertView.findViewById(R.id.alertMessage);
        alertTitle.setText(title);
        alertMessage.setText(message);
        TextView okTextView = alertView.findViewById(R.id.ok_action);
        TextView cancelTextView = alertView.findViewById(R.id.cancel_action);
        final AlertDialog alertDialog = builder.create();
        cancelTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.cancel();
            }
        });
        okTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user == null) {
                    viewModel.deleteUsers();
                    Toast.makeText(context, R.string.usersDeleted, Toast.LENGTH_SHORT).show();
                } else {
                    viewModel.deleteUser(user);
                    Toast.makeText(context, R.string.userDeleted, Toast.LENGTH_SHORT).show();
                }
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }

    public void showOkDialogue(int title, int message, final Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View alertView = layoutInflater.inflate(R.layout.dialogue_ok, null);
        builder.setView(alertView);
        TextView alertTitle = alertView.findViewById(R.id.alertTitle);
        TextView alertMessage = alertView.findViewById(R.id.alertMessage);
        alertTitle.setText(title);
        alertMessage.setText(message);
        TextView okTextView = alertView.findViewById(R.id.ok_action);
        final AlertDialog alertDialog = builder.create();
        okTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }
}
