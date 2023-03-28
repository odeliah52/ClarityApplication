package com.student.clarityapplication;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


public class TaskViewHolder extends RecyclerView.ViewHolder {

    public CardView cardView;
    public TextView name;

    public TaskViewHolder(@NonNull View itemView) {
        super(itemView);

        cardView = itemView.findViewById(R.id.cardView);
        name = itemView.findViewById(R.id.taskName);
    }
}

