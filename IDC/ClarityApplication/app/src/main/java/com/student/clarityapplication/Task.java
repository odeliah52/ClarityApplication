package com.student.clarityapplication;

public class Task {
    public String name;
    private boolean isChecked;

    public Task(String name, boolean isChecked) {
        this.name = name;
        this.isChecked = isChecked;
    }

    public Task(String name) {
        this.name = name;
        this.isChecked = false;
    }

    public Task(CharSequence name) {
        this.name = name.toString();
        this.isChecked = false;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

}