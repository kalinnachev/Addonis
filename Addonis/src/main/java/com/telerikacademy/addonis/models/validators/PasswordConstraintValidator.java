package com.telerikacademy.addonis.models.validators;

import com.google.common.base.Joiner;
import com.telerikacademy.addonis.models.validators.ValidPassword;
import org.passay.*;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.Collections;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {


    @Override
    public void initialize(ValidPassword arg0) {
    }


    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        PasswordValidator validator = new PasswordValidator(Arrays.asList(
                new LengthRule(8, 30),
                new UppercaseCharacterRule(1),
                new DigitCharacterRule(1),
                new SpecialCharacterRule(1),
               // new NumericalSequenceRule(3, false),
               // new AlphabeticalSequenceRule(3, false),
               // new QwertySequenceRule(3, false),
                new WhitespaceRule()));

        RuleResult result = validator.validate(new PasswordData(password));
        if (result.isValid()) {
            return true;
        }
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(Joiner.on(",").join(Collections.singleton(("Password must be at least 8 symbols and should contain capital letter," +
                " digit and special symbol (+, -, *, &, ^, …)"))))
                .addConstraintViolation();
        return false;
    }
}
