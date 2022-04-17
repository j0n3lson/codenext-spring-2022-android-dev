package com.example.javacalcapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class MainActivity extends AppCompatActivity {
    TextView txtCalcArea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Get all views
        ConstraintLayout rootConstraintLayout = (ConstraintLayout) findViewById(R.id.rootConstraintLayout);
        int childViewCount = rootConstraintLayout.getChildCount();
        // 2. For Button Views, add the event listener.
        for (int i = 0; i < childViewCount; i++) {
            View v = rootConstraintLayout.getChildAt(i);
            if (v instanceof Button) {
                v.setOnClickListener(this::handleButtonClick);
            }
        }
        this.txtCalcArea = findViewById(R.id.txtCalcArea);
    }

    /**
     * Determines which button was clicked and executes the right action.
     *
     * @param view the {@link View} that was clicked.
     */
    @SuppressLint("SetTextI18n")
    public void handleButtonClick(View view) {
        // TODO: Refactor this into a ButtonHandler class and unit test it. We can use
        // parameterized testing for this. See https://stackoverflow.com/a/46553713
        boolean isButtonView = view instanceof Button;
        if (!isButtonView) {
            return;
        }

        Button viewButton = (Button) view;
        String updatedText = ButtonHandler.process(
                viewButton.getId(),
                viewButton.getText().toString(),
                txtCalcArea.getText().toString());
        txtCalcArea.setText(updatedText);
    }
}