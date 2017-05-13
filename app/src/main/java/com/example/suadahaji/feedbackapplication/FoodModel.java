package com.example.suadahaji.feedbackapplication;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by suadahaji
 */

public class FoodModel extends RealmObject {

    @PrimaryKey
    String id;
    boolean checked = false;
    String dateChecked;
    int count = 0;

    public FoodModel(String id, boolean checked, String dateChecked, int count) {
        this.id = id;
        this.checked = checked;
        this.dateChecked = dateChecked;
        this.count = count;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getDateChecked() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        SimpleDateFormat formatted = new SimpleDateFormat("yyyy");

        try {
            date = format.parse(dateChecked);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        dateChecked = formatted.format(date);
        return dateChecked;
    }

    public void setDateChecked(String dateChecked) {
        this.dateChecked = dateChecked;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
