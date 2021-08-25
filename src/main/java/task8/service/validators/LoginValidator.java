package task8.service.validators;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import task8.web.forms.LoginForm;

@Service
public class LoginValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass){
        return LoginForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors){
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "login.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.empty");
    }
}
