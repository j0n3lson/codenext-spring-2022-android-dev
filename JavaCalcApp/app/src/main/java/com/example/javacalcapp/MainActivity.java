package com.example.javacalcapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private static final ArrayList<Integer> DIGIT_BUTTON_IDS = new ArrayList<>(
            Arrays.asList(
                    R.id.btnDigit00,
                    R.id.btnDigit01,
                    R.id.btnDigit02,
                    R.id.btnDigit03,
                    R.id.btnDigit04,
                    R.id.btnDigit05,
                    R.id.btnDigit06,
                    R.id.btnDigit07,
                    R.id.btnDigit08,
                    R.id.btnDigit09
            ));
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
                v.setOnClickListener(this::doButtonClick);
            }
        }
        this.txtCalcArea = findViewById(R.id.txtCalcArea);
    }

    /**
     * Determines which button was clicked and executes the right action.
     *
     * @param view the {@link View} that was clicked.
     */
    public void doButtonClick(View view) {
        boolean isButtonView = view instanceof Button;
        if (!isButtonView) {
            return;
        }

        String currentText = this.txtCalcArea.getText().toString();

        // Use the button type to determine the action
        Button viewButton = (Button) view;
        if (DIGIT_BUTTON_IDS.contains(viewButton.getId())) {
            txtCalcArea.setText(currentText + viewButton.getText().toString());
            return;
        }

        switch (viewButton.getId()) {
            case R.id.btnDot:
            case R.id.btnClear:
            case R.id.btnDelete:
            case R.id.btnDivide:
            case R.id.btnMultiply:
            case R.id.btnAdd:
            case R.id.btnSubtract:
        }
    }
}