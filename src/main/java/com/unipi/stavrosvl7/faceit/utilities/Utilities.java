package com.unipi.stavrosvl7.faceit.utilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utilities {

    private static boolean regexMatch(String s, String expression) {
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(s);
        return matcher.matches();
    }

    public static boolean isValidEmailAddress(String emailAddress) {
        String expression = "^[A-Za-z0-9.%+\\-\\_]+@[A-Za-z0-9.\\-]+\\.[A-Za-z]*$";
        return regexMatch(emailAddress, expression);
    }
}
