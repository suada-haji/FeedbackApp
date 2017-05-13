package com.example.suadahaji.feedbackapplication.realm;

import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;

import com.example.suadahaji.feedbackapplication.FoodModel;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by suadahaji
 */

public class RealmController {

    private static RealmController instance;
    private final Realm realm;

    public RealmController(Application application) {
        realm = Realm.getDefaultInstance();
    }

    public static RealmController with(Fragment fragment) {
        if (instance == null) {
            instance = new RealmController(fragment.getActivity().getApplication());
        }
        return instance;
    }

    public static RealmController with(Activity activity) {
        if (instance == null) {
            instance = new RealmController(activity.getApplication());
        }

        return instance;
    }

    public static RealmController with(Application application) {
        if (instance == null) {
            instance = new RealmController(application);
        }
        return instance;
    }

    public static RealmController getInstance() {
        return instance;
    }

    public Realm getRealm() {
        return realm;
    }


    // find all objects in the class
    public RealmResults<FoodModel> getAllFeedback() {
        return realm.where(FoodModel.class).findAll();
    }


    //
    public RealmResults<FoodModel> getGoodFeedback() {
        return realm.where(FoodModel.class)
                .contains("rating", "good")
                .findAll();
    }

    public RealmResults<FoodModel> getAverageFeedback() {
        return realm.where(FoodModel.class)
                .contains("rating", "average")
                .findAll();
    }

    public RealmResults<FoodModel> getBadFeedback() {
        return realm.where(FoodModel.class)
                .contains("rating", "bad")
                .findAll();
    }

}
