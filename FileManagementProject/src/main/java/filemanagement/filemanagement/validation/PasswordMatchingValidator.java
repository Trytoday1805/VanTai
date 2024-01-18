package filemanagement.filemanagement.validation;

import filemanagement.filemanagement.domain.UserForm;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class PasswordMatchingValidator implements ConstraintValidator<PasswordMatching, UserForm> {
    @Override
    public boolean isValid(UserForm userForm, ConstraintValidatorContext context) {
        return userForm.getPassword().equals(userForm.getConfirmPassword());
    }
}
