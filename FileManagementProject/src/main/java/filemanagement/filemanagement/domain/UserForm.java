package filemanagement.filemanagement.domain;

import filemanagement.filemanagement.validation.PasswordMatching;
import filemanagement.filemanagement.validation.UniqueEmail;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@RequiredArgsConstructor
@PasswordMatching
public class UserForm {
    @UniqueEmail
    @NotNull
    private String email;

    @NotNull(message = "Password is invalid")
    @Size(min = 8, max=20, message = "Password must be between 8 and 20 characters")
    private String password;

    @NotNull
    @Size(min = 8, max=20, message = "Password must be between 8 and 20 characters")
    private String confirmPassword;

    private UserRole role;
}
