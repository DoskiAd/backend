package io.shifu.doskiad.services;

import io.shifu.doskiad.forms.PasswordForm;
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

    @Override
    public Optional<User> findByConfirmationToken(String token) {
        return usersRepository.findByConfirmationToken(token);
    }

    @Override
    public Optional<User> findByResetToken(String token) {
        return usersRepository.findByResetToken(token);
    }

    @Override
    public void activateUser(User user) {
        user.setConfirmationToken(null);
        user.setState(State.ACTIVE);

        usersRepository.save(user);
    }

    @Override
    public User reset(User user) {
        user.setResetToken(UUID.randomUUID().toString());

        usersRepository.save(user);

        return user;
    }

    @Override
    public void changePassword(User user, PasswordForm passwordForm) {
        String hashPassword = passwordEncoder.encode(passwordForm.getPassword());

        user.setResetToken(null);
        user.setState(State.ACTIVE);
        user.setHashPassword(hashPassword);

        usersRepository.save(user);
    }
}
