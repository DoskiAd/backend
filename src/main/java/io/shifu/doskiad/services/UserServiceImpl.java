package io.shifu.doskiad.services;

import io.shifu.doskiad.forms.UserForm;
import io.shifu.doskiad.model.Role;
import io.shifu.doskiad.model.State;
import io.shifu.doskiad.model.User;
import io.shifu.doskiad.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UsersRepository usersRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return usersRepository.findByEmail(email);
    }

    @Override
    public User signUp(UserForm userForm) {
        String hashPassword = passwordEncoder.encode(userForm.getPassword());

        User user = new User();
        user.setFirstName(userForm.getFirstName());
        user.setLastName(userForm.getLastName());
        user.setHashPassword(hashPassword);
        user.setEmail(userForm.getEmail());
        user.setConfirmationToken(UUID.randomUUID().toString());
        user.setRole(Role.USER);
        user.setState(State.DEACTIVATED);

        usersRepository.save(user);

        return user;
    }
}
