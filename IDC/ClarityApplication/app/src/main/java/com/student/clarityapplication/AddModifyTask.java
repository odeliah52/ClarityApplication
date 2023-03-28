package com.student.clarityapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class AddModifyTask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_task);

        Button deleteButton = findViewById(R.id.btn_cancel);
        deleteButton.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(AddModifyTask.this);
            builder.setTitle("Delete Task")
                    .setMessage("Are you sure you want to delete this task?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        Toast.makeText(AddModifyTask.this, "Task has been deleted", Toast.LENGTH_SHORT).show();
                        finish();
                    })
                    .setNegativeButton("No", (dialog, which) -> {
                        // do nothing
                    })
                    .show();
        });

        TextView dateText = findViewById(R.id.dateText);
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT, Locale.getDefault());
        String currentDate = dateFormat.format(new Date());
        dateText.setText(currentDate);
    }

    public void saveTask(View view) {
        TextView taskTitleEditText = findViewById(R.id.taskTitle);

        Task newTask = new Task(taskTitleEditText.getText().toString(), false);
        Intent intent = new Intent();
        intent.putExtra("task", (Serializable) newTask);
        setResult(RESULT_OK, intent);
        finish();
    }
}

