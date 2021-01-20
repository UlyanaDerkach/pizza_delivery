package com.epam.pizza_delivery.validation;

public class UserDataValidation {
    private static final String EMAIL_REGEX = "^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$";
    private static final String PASSWORD_REGEX = "[a-zA-Z0-9~!@#$%^&*]{6,}";
    private static final String PHONE_REGEX = "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$";

    public static boolean validateEmail(String email) {
        return email.matches(EMAIL_REGEX);
    }

    public static boolean validatePassword(String password) {
        return password.matches(PASSWORD_REGEX);
    }

    public static boolean validatePhone(String phone) {
        return phone.matches(PHONE_REGEX);
    }

    public static boolean isCorrectPassword(String password, String truePassword) {
        return truePassword.equals(password);
    }
}
