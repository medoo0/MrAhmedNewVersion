package com.developer.mohamedraslan.hossamexams.Utils;

import android.content.Context;
import android.util.Patterns;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class ViewsEmpty {
    public static boolean isEmail(String value) {
        return Patterns.EMAIL_ADDRESS.matcher(value).matches();

    }

    public static boolean isPhone(String value) {
        return Patterns.PHONE.matcher(value).matches();
    }

    public static boolean isNumber(String value) {
        return value.matches("[0-9]+");
    }

    public static boolean isIntegerValueZero(int value) {
        return value == 0;
    }

    public static boolean isStringValueZero(String value) {
        return value.equals("0");
    }

    public static boolean isArabic(String value) {
        for (int i = 0; i < value.length() ; ) {
            int c = value.codePointAt(i);
            if (c >= 0x0600 && c <= 0x06E0) return true;
            i += Character.charCount(c);
        }
        return false;
    }



    public static boolean isEmpty(String text) {
        return text.trim().isEmpty();
    }

    public static boolean isEmpty(EditText editText) {
        return isEmpty(editText.getText().toString());
    }

    public static boolean isLengthCorrect(String text, int min, int max) {
        return text.length() >= min && text.length() <= max;
    }

    public static boolean isEmpty(EditText editText, String error) {
        if (isEmpty(editText)) {
            editText.setError(error);
            return true;
        } else {
            return false;
        }
    }

    public static boolean isValidEmail(EditText emailEditText, String error) {
        if (isEmail(emailEditText.getText().toString())) {
            return true;
        } else {
            emailEditText.setError(error);
            return false;
        }

    }

    public static boolean isValidEmail(EditText emailEditText, String errorIfEmpry, String errorIfNotValid) {
        final String emailString = emailEditText.getText().toString();
        if (isEmail(emailString)) {
            return true;
        } else if (isEmpty(emailString)) {
            emailEditText.setError(errorIfEmpry);
            return false;
        } else {
            emailEditText.setError(errorIfNotValid);
            return false;
        }

    }

    public static boolean isPasswordEqual(EditText passwordEditText, EditText repeatedPasswordEditText, String error) {
        String passowrd1 = passwordEditText.getText().toString();
        String password2 = repeatedPasswordEditText.getText().toString();
        if (passowrd1.equals(password2)) {
            return true;
        } else {
            passwordEditText.setError(error);
            repeatedPasswordEditText.setError(error);
            return false;
        }

    }

    public static boolean isSpinnerFirstItemSelected(Spinner spinner, String error, Context context) {
        if (spinner.getSelectedItemPosition() == 0) {
            Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
            return true;
        } else {
            return false;
        }

    }

    public static void hideErorr(EditText editText) {
        editText.setError(null);
    }

    public static void reset(EditText editText) {
        editText.setError(null);
        editText.setText("");
    }

    public static String value(EditText editText) {
        return editText.getText().toString();
    }
}
