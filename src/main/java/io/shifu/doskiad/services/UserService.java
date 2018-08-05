package io.shifu.doskiad.services;

import io.shifu.doskiad.forms.UserForm;
import io.shifu.doskiad.model.User;

import java.util.Optional;

public interface UserService {

    Optional<User> findByEmail(String email);

    User signUp(UserForm userForm);

    Optional<User> findByConfirmationToken(String token);

    void activateUser(User user);
}
