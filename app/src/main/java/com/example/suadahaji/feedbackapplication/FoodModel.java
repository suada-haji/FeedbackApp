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
    String foodType = "lunch";
    String rating ;

    public FoodModel() {
    }

    public FoodModel(String id, boolean checked, String dateChecked, String rating, String foodType) {
        this.id = id;
        this.checked = checked;
        this.dateChecked = dateChecked;
        this.rating = rating;
        this.foodType = foodType;
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
        /*SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        SimpleDateFormat formatted = new SimpleDateFormat("yyyy");

        try {
            date = format.parse(dateChecked);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        dateChecked = formatted.format(date);*/
        return dateChecked;
    }

    public void setDateChecked(String dateChecked) {
        this.dateChecked = dateChecked;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }
}
