package com.mobile.suadahaji.feedbackapplication;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.mobile.suadahaji.feedbackapplication.realm.RealmController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

public class RateActivity extends AppCompatActivity {

    @BindView(com.mobile.suadahaji.feedbackapplication.R.id.radio_group)
    RadioGroup radioGroup;
    @BindView(com.mobile.suadahaji.feedbackapplication.R.id.good)
    RadioButton rbGood;
    @BindView(com.mobile.suadahaji.feedbackapplication.R.id.average)
    RadioButton rbAverage;
    @BindView(com.mobile.suadahaji.feedbackapplication.R.id.bad)
    RadioButton rbBad;
    @BindView(com.mobile.suadahaji.feedbackapplication.R.id.submitButton)
    Button btnSubmit;
    @BindView(com.mobile.suadahaji.feedbackapplication.R.id.tv_food_type)
    TextView tvFoodType;

    Realm realm;

    String rating;
    String foodType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.mobile.suadahaji.feedbackapplication.R.layout.activity_rating);

        ButterKnife.bind(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        realm = RealmController.with(this).getRealm();

        foodType = getIntent().getStringExtra(MainActivity.FOODTYPE);

        getSupportActionBar().setTitle(foodType.toUpperCase() + " FEEDBACK");

        tvFoodType.setText("How was " + foodType + "?");

        btnSubmit.setEnabled(false);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == 0) {
                    btnSubmit.setEnabled(false);
                } else {
                    btnSubmit.setEnabled(true);
                }
            }
        });


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
        View dialoglayout = inflater.inflate(com.mobile.suadahaji.feedbackapplication.R.layout.alert_view, null);
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(dialoglayout);

        Button btnOk = (Button) dialoglayout.findViewById(com.mobile.suadahaji.feedbackapplication.R.id.btn_ok);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                btnSubmit.setEnabled(false);
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