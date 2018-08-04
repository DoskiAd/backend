package io.shifu.doskiad.services;

import io.shifu.doskiad.forms.UserForm;
import io.shifu.doskiad.model.Role;
import io.shifu.doskiad.model.State;
import io.shifu.doskiad.model.User;
import io.shifu.doskiad.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
    public void signUp(UserForm userForm) {
        String hashPassword = passwordEncoder.encode(userForm.getPassword());

        User user = new User();
        user.setFirstName(userForm.getFirstName());
        user.setLastName(userForm.getLastName());
        user.setHashPassword(hashPassword);
        user.setLogin(userForm.getLogin());
        user.setRole(Role.USER);
        user.setState(State.ACTIVE);

        usersRepository.save(user);
    }
}
