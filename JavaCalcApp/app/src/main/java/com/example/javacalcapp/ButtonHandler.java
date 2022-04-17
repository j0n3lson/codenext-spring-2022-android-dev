package com.example.javacalcapp;

import java.util.ArrayList;
import java.util.Arrays;

public class ButtonHandler {
    static final ArrayList<Integer> DIGIT_BUTTON_IDS = new ArrayList<>(
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

    /**
     * Handles button clicks returning the corresponding updated text if appropriate.
     *
     * @return The updated text with the new character appended (if allowed).
     */
    static String process(int buttonId, String buttonText, String currentText) {
        // Digit buttons are all the same
        if (DIGIT_BUTTON_IDS.contains(buttonId)) {
            // IMPORTANT: Don't allow divide by 0.
            if (buttonId == R.id.btnDigit00 && lastCharMatches(currentText, "/")) {
                return currentText;
            }
            return currentText + buttonText;
        }

        // These are special buttons that need different handling.
        switch (buttonId) {
            case R.id.btnClear:
                return "";
            case R.id.btnDelete:
                // Only clear the last character
                if (currentText.length() > 0) {
                    return currentText.substring(0, currentText.length() - 1);
                }
            case R.id.btnDot:
                // Cannot add a dot if there's already a dot in the string.
                if (textContains(currentText, '.')) {
                    return currentText;
                }
                return currentText + ".";
            case R.id.btnParens:
                // TODO: Implement btnParens
                return currentText;
            case R.id.btnDivide:
                if (textContains(currentText, '/')) {
                    return currentText;
                }
                return currentText + "/";
            case R.id.btnMultiply:
                if (lastCharMatches(currentText, "\\*")) {
                    break;
                }
                return currentText + "*";
            case R.id.btnAdd:
                if (lastCharMatches(currentText, "\\+")) {
                    return currentText;
                }
                return currentText + "+";
            case R.id.btnSubtract:
                if (lastCharMatches(currentText, "\\-")) {
                    return currentText;
                }
                return currentText + "-";
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
        return currentText;
    }

    private static boolean textContains(String text, char matchChar) {
        if (text.isEmpty() || matchChar == ' ') {
            return false;
        }
        return text.contains(Character.toString(matchChar));
    }

    private static char getLastChar(String text) {
        if (text.isEmpty()) {
            return ' ';
        }
        return text.charAt(text.length() - 1);
    }

    private static boolean lastCharMatches(String text, String regex) {
        return Character.toString(getLastChar(text)).matches(regex);
    }
}
