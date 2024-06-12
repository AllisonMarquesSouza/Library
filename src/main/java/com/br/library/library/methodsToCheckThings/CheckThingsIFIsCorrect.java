package com.br.library.library.methodsToCheckThings;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckThingsIFIsCorrect {

    public static void checkEmailIsOk(String email) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Email is not valid");
        }
    }
}
