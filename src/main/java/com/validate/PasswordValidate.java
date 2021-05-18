package com.validate;

import com.model.Account;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PasswordValidate implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Account.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Account account= (Account) o;
        String password = account.getPassword();

        if (!password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$")){
            errors.rejectValue("password", null,"Độ dài mật khẩu ít nhất 8 ký tự, ít nhất một chữ cái và một số");
        }
    }
}
