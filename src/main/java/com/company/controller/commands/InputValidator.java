package com.company.controller.commands;

public class InputValidator {
    public static final String EMAIL_REGEX = "^[a-zA-Z0-9_.-]{4,15}@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$";
    public static final String PASSWORD_REGEX = "^[a-zA-Z0-9_.-]{4,20}$";
    public static final String NICKNAME_REGEX = "^[a-zA-Z0-9_.-]{4,12}$";

    public static boolean strMatchesRegex(String str, String regex) {
        return str.matches(regex);
    }

    public static boolean strNotMatchesRegex(String str, String regex) {
        return !strMatchesRegex(str, regex);
    }
}
