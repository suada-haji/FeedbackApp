package com.mobile.suadahaji.feedbackapplication;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RateActivity extends AppCompatActivity {

    @BindView(com.mobile.suadahaji.feedbackapplication.R.id.radio_group)
    RadioGroup radioGroup;
   /* @BindView(com.mobile.suadahaji.feedbackapplication.R.id.good)
    RadioButton rbGood;
    @BindView(com.mobile.suadahaji.feedbackapplication.R.id.average)
    RadioButton rbAverage;
    @BindView(com.mobile.suadahaji.feedbackapplication.R.id.bad)
    RadioButton rbBad;*/
    /*@BindView(com.mobile.suadahaji.feedbackapplication.R.id.submitButton)
    Button btnSubmit;*/
    @BindView(com.mobile.suadahaji.feedbackapplication.R.id.tv_food_type)
    TextView tvFoodType;

    private String rating;
    private String foodType;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private String ratingId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.mobile.suadahaji.feedbackapplication.R.layout.activity_rating);

        ButterKnife.bind(this);

        foodType = getIntent().getStringExtra(MainActivity.FOODTYPE);


        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(foodType.toUpperCase() + " FEEDBACK");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        mFirebaseInstance = FirebaseDatabase.getInstance();

        // get reference to 'ratings' node
        mFirebaseDatabase = mFirebaseInstance.getReference("ratings");

        // store app title to 'app_title' node

        mFirebaseInstance.getReference("app_title").setValue("Feedback Application Database");


        tvFoodType.setText("How was " + foodType + "?");

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                View radioButton = radioGroup.findViewById(checkedId);

                int index = radioGroup.indexOfChild(radioButton);
                switch (index) {
                    case 0:
                        rating = "good";
                        break;
                    case 1:
                        rating = "average";
                        break;
                    case 2:
                        rating = "bad";
                        break;
                }

                if (TextUtils.isEmpty(ratingId)) {
                    insertFeedback();
                }
            }
        });
    }

    public void insertFeedback() {

        Date date = new Date();

        SimpleDateFormat postFormater = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        final String dateStr = postFormater.format(date);

        RatingModel ratingModel = new RatingModel(dateStr, rating, foodType);

        if (TextUtils.isEmpty(ratingId)) {
            ratingId = mFirebaseDatabase.push().getKey();
        }
        mFirebaseDatabase.child(ratingId).setValue(ratingModel);

        showDialog();
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
                startActivity(new Intent(RateActivity.this, MainActivity.class));
                finish();
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