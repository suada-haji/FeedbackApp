package com.mobile.suadahaji.feedbackapplication;

/**
 * Created by suadahaji
 */

public class RatingModel {

    String dateChecked;
    String foodType;
    String rating ;

    public RatingModel() {
    }

    public RatingModel(String dateChecked, String rating, String foodType) {
        this.dateChecked = dateChecked;
        this.rating = rating;
        this.foodType = foodType;
    }

    public String getDateChecked() {
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
