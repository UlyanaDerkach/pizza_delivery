package com.epam.pizza_delivery.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;
import java.util.ResourceBundle;

import static com.epam.pizza_delivery.util.constants.ParameterConstants.LANGUAGE;

public class ErrorIdentifier {
    public static String getErrorFromLanguageBundle(HttpServletRequest request, String error) {

        HttpSession session = request.getSession();
        String currentLanguage = (String) session.getAttribute(LANGUAGE);
        Locale sessionLocale = new Locale(currentLanguage);
        ResourceBundle languageBundle = ResourceBundle.getBundle("language", sessionLocale);

        return languageBundle.getString(error);
    }
}
