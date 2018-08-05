package io.shifu.doskiad.services;

import io.shifu.doskiad.forms.PasswordForm;
import io.shifu.doskiad.forms.UserForm;
import io.shifu.doskiad.model.User;

import java.util.Optional;

public interface UserService {

    Optional<User> findByEmail(String email);

    User signUp(UserForm userForm);

    Optional<User> findByConfirmationToken(String token);

    Optional<User> findByResetToken(String token);

    void activateUser(User user);

    User reset(User user);

    void changePassword(User user, PasswordForm passwordForm);
}
