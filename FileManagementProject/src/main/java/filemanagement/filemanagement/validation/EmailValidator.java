package filemanagement.filemanagement.validation;

import filemanagement.filemanagement.domain.User;
import filemanagement.filemanagement.service.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
public class EmailValidator implements ConstraintValidator<UniqueEmail,String> {
    @Autowired
    private UserService userService;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        User user = userService.getUserByEmail(email);
        return user == null;
    }
}
