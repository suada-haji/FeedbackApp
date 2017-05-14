package com.example.suadahaji.feedbackapplication;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.suadahaji.feedbackapplication.realm.RealmController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

public class RateActivity extends AppCompatActivity {

    @BindView(R.id.radio_group)
    RadioGroup radioGroup;
    @BindView(R.id.good)
    RadioButton rbGood;
    @BindView(R.id.average)
    RadioButton rbAverage;
    @BindView(R.id.bad)
    RadioButton rbBad;
    @BindView(R.id.submitButton)
    Button btnSubmit;

    Realm realm;

    String rating;
    String foodType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        ButterKnife.bind(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        realm = RealmController.with(this).getRealm();

        foodType = getIntent().getStringExtra(MainActivity.FOODTYPE);


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rbGood.isChecked()) {
                    rating = "good";
                } else if (rbAverage.isChecked()) {
                    rating = "average";
                } else if (rbBad.isChecked()) {
                    rating = "bad";
                }

                insertFeedback();

            }
        });

    }

    public void insertFeedback() {

        final String id = UUID.randomUUID().toString();
        Date date = new Date();

        SimpleDateFormat postFormater = new SimpleDateFormat("MMMM dd, yyyy");
        final String dateStr = postFormater.format(date);

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                FoodModel model = realm.createObject(FoodModel.class, id);
                model.setDateChecked(dateStr);
                model.setRating(rating);
                model.setFoodType(foodType);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Log.d("Suada", "Model : " + RealmController.getInstance().getBadFeedback());
               showDialog();
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Toast.makeText(getApplicationContext(), "Error Occurred", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void showDialog() {
        LayoutInflater inflater = getLayoutInflater();
        View dialoglayout = inflater.inflate(R.layout.alert_view, null);
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(dialoglayout);

        Button btnOk = (Button) dialoglayout.findViewById(R.id.btn_ok);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}