package com.epam.pizza_delivery.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;
import java.util.ResourceBundle;

public class ErrorIdentifier {
    public static String getErrorFromLanguageBundle(HttpServletRequest request, String error) {

        HttpSession session = request.getSession();
        String currentLanguage = (String) session.getAttribute("lang");
        Locale sessionLocale = new Locale(currentLanguage);
        ResourceBundle languageBundle = ResourceBundle.getBundle("language", sessionLocale);

        return languageBundle.getString(error);
    }
}
