package web.servlet;

import java.util.regex.Pattern;

class Validator {
    static boolean validEmail(String email) {
        final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        return EMAIL_PATTERN.matcher(email).matches();
    }

    static boolean validLogin(String login) {
        login = login.replaceAll("\\s{2,}", " ").trim();
        return isFieldNotEmpty(login);
    }

    static boolean isFieldNotEmpty(String field) {
        return !field.isEmpty();
    }

    static boolean roleIsEmpty(String field){
        return (field == null);
    }
}
