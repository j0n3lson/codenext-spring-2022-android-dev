package com.example.counterapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView txtCounterValue;
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnIncrease = findViewById(R.id.btnIncrease);
        btnIncrease.setOnClickListener(this::handleIncrease);

        Button btnDecrease = findViewById(R.id.btnDecrease);
        btnDecrease.setOnClickListener(this::handleDecrease);

        txtCounterValue = findViewById(R.id.txtCounterValue);
    }


    private void handleIncrease(View view) {
        this.counter++;
        txtCounterValue.setText(String.valueOf(counter));
    }

    private void handleDecrease(View view) {
        this.counter--;
        txtCounterValue.setText(String.valueOf(this.counter));

    }

}