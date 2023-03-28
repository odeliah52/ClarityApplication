package com.student.clarityapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DataPersistencyHelper {

    public static void storeData(Context context, List<Task> tasks) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sp.edit();
        String json = new Gson().toJson(tasks);
        editor.putString("tasks", json);
        editor.apply();
    }
    public static List<Task> loadData(Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        String json = sp.getString("tasks", null);
        if (json != null) {
            Type type = new TypeToken<List<Task>>() {}.getType();
            return new Gson().fromJson(json, type);
        } else {
            List<Task> tasks = new ArrayList<>();
            tasks.add(new Task("Task 1"));
            tasks.add(new Task("Task 2"));
            tasks.add(new Task("Task 3"));
            tasks.add(new Task("Task 4"));
            tasks.add(new Task("Task 5"));
            return tasks;
        }
    }
}