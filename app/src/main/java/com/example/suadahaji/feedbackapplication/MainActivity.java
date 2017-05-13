package com.example.suadahaji.feedbackapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

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

    String getText;

    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                View radioButton = radioGroup.findViewById(i);
                index = radioGroup.indexOfChild(radioButton);
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rbGood.isChecked()) {
                    getText = String.valueOf(index);
                } else if (rbAverage.isChecked()) {
                    getText = String.valueOf(index);
                } else if (rbBad.isChecked()) {
                    getText = String.valueOf(index);
                }

                Toast.makeText(getApplicationContext(), getText, Toast.LENGTH_SHORT).show(); // print the value of selected super star
            }
        });




    }
}
