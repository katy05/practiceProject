package task8.web.servlet;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;
@Service
public class Validator {
    public static boolean validEmail(String email) {
        final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        return EMAIL_PATTERN.matcher(email).matches();
    }

    public static boolean validLogin(String login) {
        login = login.replaceAll("\\s{2,}", " ").trim();
        return isFieldNotEmpty(login);
    }

    public static boolean isFieldNotEmpty(String field) {
        return !field.isEmpty();
    }

    public static boolean roleIsEmpty(List<String> field){
        return (field == null);
    }
}
