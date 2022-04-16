package com.example.javacalcapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
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
    @SuppressLint("SetTextI18n")
    public void doButtonClick(View view) {
        boolean isButtonView = view instanceof Button;
        if (!isButtonView) {
            return;
        }

        String currentText = this.txtCalcArea.getText().toString();
        Button viewButton = (Button) view;

        // Digit buttons are all the same
        if (DIGIT_BUTTON_IDS.contains(viewButton.getId())) {
            // TODO: Avoid divide by zero - Do not allow 0 if the previous char is a "/".
            txtCalcArea.setText(currentText + viewButton.getText().toString());
            return;
        }

        // These are special buttons that need different handling.
        switch (viewButton.getId()) {
            case R.id.btnClear:
                txtCalcArea.setText("");
                break;
            case R.id.btnDelete:
                // Only clear the last character
                if (currentText.length() > 0) {
                    txtCalcArea.setText(currentText.substring(0, currentText.length() - 1));
                }
                break;
            case R.id.btnDot:
                // Cannot add a dot if there's already a dot in the string.
                boolean hasDotAlready = currentText.matches(".*\\..*");
                if (hasDotAlready) {
                    break;
                }
                txtCalcArea.setText(currentText + ".");
                break;
            case R.id.btnParens:
                break;
            case R.id.btnDivide:
                if(currentText.isEmpty() || currentText.matches(".*/.*")){
                   break;
                }
                txtCalcArea.setText(currentText + "/");
                break;
            case R.id.btnMultiply:
                if (currentText.isEmpty() || lastCharMatches(currentText, '*')) {
                    break;
                }
                txtCalcArea.setText(currentText + "*");
                break;
            case R.id.btnAdd:
                if (currentText.isEmpty() || lastCharMatches(currentText, '+')) {
                    break;
                }
                txtCalcArea.setText(currentText + "+");
                break;
            case R.id.btnSubtract:
                if (lastCharMatches(currentText, '-')) {
                    break;
                }
                txtCalcArea.setText(currentText + "-");
                break;
            case R.id.btnEquals:
                break;
        }
    }

    private boolean lastCharMatches(String text, char matchChar) {
        if(text.isEmpty() || matchChar == ' '){
            return false;
        }
        return text.charAt(text.length() - 1) == matchChar;
    }
}