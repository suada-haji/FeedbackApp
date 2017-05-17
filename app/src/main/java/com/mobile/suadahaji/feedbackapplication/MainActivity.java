package com.mobile.suadahaji.feedbackapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String FOODTYPE = "foodType";

    @BindView(com.mobile.suadahaji.feedbackapplication.R.id.btn_breakfast)
    Button btnBreakfast;
    @BindView(com.mobile.suadahaji.feedbackapplication.R.id.btn_lunch)
    Button btnLunch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.mobile.suadahaji.feedbackapplication.R.layout.activity_main);

        ButterKnife.bind(this);

        btnBreakfast.setOnClickListener(this);
        btnLunch.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case com.mobile.suadahaji.feedbackapplication.R.id.btn_breakfast:
                submitReview("breakfast");
                break;
            case com.mobile.suadahaji.feedbackapplication.R.id.btn_lunch:
                submitReview("lunch");
                break;
            default:
                break;
        }
    }

    public void submitReview(String foodType) {
        Intent intent = new Intent(this, RateActivity.class);
        intent.putExtra(FOODTYPE, foodType);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}











