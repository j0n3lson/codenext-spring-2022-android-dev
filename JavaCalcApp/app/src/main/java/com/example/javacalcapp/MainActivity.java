package com.example.javacalcapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

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
        // TODO: Refactor this into a ButtonHandler class and unit test it. We can use
        // parameterized testing for this. See https://stackoverflow.com/a/46553713
        boolean isButtonView = view instanceof Button;
        if (!isButtonView) {
            return;
        }

        String currentText = this.txtCalcArea.getText().toString();
        Button viewButton = (Button) view;

        // Digit buttons are all the same
        if (DIGIT_BUTTON_IDS.contains(viewButton.getId())) {
            if (viewButton.getId() == R.id.btnDigit00 && lastCharMatches(currentText, "/")) {
                return;
            }
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
                if (textContains(currentText, '.')) {
                    break;
                }
                txtCalcArea.setText(currentText + ".");
                break;
            case R.id.btnParens:
                // TODO: Implement btnParens
                break;
            case R.id.btnDivide:
                if (textContains(currentText, '/')) {
                    break;
                }
                txtCalcArea.setText(currentText + "/");
                break;
            case R.id.btnMultiply:
                if (lastCharMatches(currentText, "\\*")) {
                    break;
                }
                txtCalcArea.setText(currentText + "*");
                break;
            case R.id.btnAdd:
                if (lastCharMatches(currentText, "\\+")) {
                    break;
                }
                txtCalcArea.setText(currentText + "+");
                break;
            case R.id.btnSubtract:
                if (lastCharMatches(currentText, "\\-")) {
                    break;
                }
                txtCalcArea.setText(currentText + "-");
                break;
            case R.id.btnEquals:
                // TODO: Implement btnEquals using ANTLRv4 parsing.This is a good approach because
                // (1) It supports context free grammars and can support balanced parens check
                // (2) It's been a long long time since I messed with lexers/parsers
                // (3) It's possible (likely) that the grammar could replace all this manual
                //      code and let us have a more expressive rule set.
                // See https://stackoverflow.com/a/7597623 for ANTLRv4 on Java
                // See https://stackoverflow.com/a/9609118 for working example
                // See https://github.com/antlr/grammars-v4/tree/master/calculator for a grammar.
                break;
        }
    }


    private boolean textContains(String text, char matchChar) {
        if (text.isEmpty() || matchChar == ' ') {
            return false;
        }
        return text.contains(Character.toString(matchChar));
    }

    private char getLastChar(String text) {
        if (text.isEmpty()) {
            return ' ';
        }
        return text.charAt(text.length() - 1);
    }

    private boolean lastCharMatches(String text, String regex) {
        return Character.toString(getLastChar(text)).matches(regex);
    }
}