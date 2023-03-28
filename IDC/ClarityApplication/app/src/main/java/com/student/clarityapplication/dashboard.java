package com.student.clarityapplication;

import static com.student.clarityapp.R.id.taskCard;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.cardview.widget.CardView;


public class dashboard extends Activity implements View.OnClickListener {

    public CardView cardTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);

        cardTask = (CardView) findViewById(taskCard);
        cardTask.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()) {
            case taskCard : i = new Intent(this,TaskActivity.class);startActivity(i);break;
            default:break;
        }

    }
}
