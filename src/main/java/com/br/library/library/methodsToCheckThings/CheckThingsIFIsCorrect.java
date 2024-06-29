package com.br.library.library.methodsToCheckThings;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckThingsIFIsCorrect {

    public static void checkEmailIsOk(String email) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z]{2,})+$");
        Matcher matcher = pattern.matcher(email);
        if(!matcher.matches()) throw new IllegalArgumentException("Email not valid, check it");
    }

    public static void checkPasswordIsOk(String passwordToCheck, String passwordSaved) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        boolean checkingPassword = encoder.matches(passwordToCheck, passwordSaved);

        if(!checkingPassword) {
            throw new IllegalArgumentException("The password is incorrect, check it");
        }
    }

}
